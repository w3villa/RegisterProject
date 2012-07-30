package com.w3villa.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.w3villa.constants.ProjectConstant;
import com.w3villa.main.authentication.bean.AmazonStructure;
import com.w3villa.main.authentication.bean.ContactUsBean;
import com.w3villa.main.authentication.bean.UserEntityBean;
import com.w3villa.main.authentication.domain.Users;
import com.w3villa.main.authentication.userService.ContactUsService;
import com.w3villa.main.authentication.userService.RepositoryService;
import com.w3villa.main.authentication.userService.StylePreferenceService;
import com.w3villa.main.authentication.userService.UsersService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private UsersService usersService;

	@Autowired
	private StylePreferenceService stylePreferenceService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private ContactUsService contactUsService;

	@RequestMapping(value = "/welcomeUser", method = RequestMethod.GET)
	public String homeUser(Locale locale, Model model,
			HttpServletRequest request) throws Exception {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		HttpSession session = request.getSession(false);
		Users users = usersService.findByUserName(user.getUsername(), true);
		session.setAttribute("users", users);
		session.setAttribute("role", ProjectConstant.ROLE_USER);
		List<AmazonStructure> assetList = getAssestList(users, null, session);
		System.out.println(assetList);
		session.setAttribute("assetList", assetList);
		return "homeUser";
	}

	@RequestMapping(value = "/welcomeAdmin", method = RequestMethod.GET)
	public String homeAdmin(Locale locale, Model model,
			HttpServletRequest request)
			throws Exception {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		HttpSession session = request.getSession(false);
		Users users = usersService.findByUserName(user.getUsername(), true);
		session.setAttribute("users", users);
		session.setAttribute("role", ProjectConstant.ROLE_ADMIN);
		List<AmazonStructure> assetList = getAssestList(users, null, session);
		System.out.println(assetList);
		session.setAttribute("assetList", assetList);
		return "homeAdmin";
	}

	@RequestMapping(value = "/RegisterMe", method = RequestMethod.GET)
	public String RegisterMeNavigate(Model model, HttpServletRequest request) {
		logger.info("RegisterMeNavigate() entry.");
		HttpSession session = request.getSession();
		session.invalidate();
		model.addAttribute(new UserEntityBean());
		model.addAttribute("stylePreferenceList",
				stylePreferenceService.getStylePreferenceList());
		logger.info("RegisterMeNavigate() exit.");
		return "sessionFree/registerMe";
	}

	@RequestMapping(value = "/RegisterMe", method = RequestMethod.POST)
	public String RegisterMe(@Valid UserEntityBean userEntityBean,
			BindingResult result, Model model, HttpServletRequest request) {
		logger.info("RegisterMe() entry.");
		if (result.hasErrors()) {
			model.addAttribute("loginFail", "fail");
			model.addAttribute("stylePreferenceList",
					stylePreferenceService.getStylePreferenceList());
			logger.info("RegisterMe() exit.");
			return "sessionFree/registerMe";
		} else {
			HttpSession session = request.getSession();
			try {
				String[] stylePreferences = request
						.getParameterValues("stylePreferences");
				usersService.saveUser(userEntityBean, stylePreferences);
				session.setAttribute("emailId", userEntityBean.getEmailId());
				model.addAttribute("registerSuccess", "true");
				logger.info("RegisterMe() exit.");
				return "albumChoice";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("error", e.getMessage());
				logger.error("Error in Controller");
				return "sessionFree/registerMe";
			}

		}
	}

	@RequestMapping(value = "/contactus", method = RequestMethod.GET)
	public String contactUsNavigate(ModelMap model) {
		model.addAttribute(new ContactUsBean());
		return "sessionFree/contactUs";

	}

	@RequestMapping(value = "/contactus", method = RequestMethod.POST)
	public String ContactUs(@Valid ContactUsBean contactUsBean,
			BindingResult result, Model model, HttpServletRequest request) {
		logger.info("ContactUs() entry.");
		if (result.hasErrors()) {
			logger.info("ContactUs() exit.");
			return "sessionFree/contactUs";
		} else {
			HttpSession session = request.getSession();
			try {
				contactUsService.saveContactUs(contactUsBean);
				session.setAttribute("message",
						"Thank you for your comments.<br>We will contact you soon.");
				logger.info("ContactUs() exit.");
				return "sessionFree/contactUsSuccess";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("error", e.getMessage());
				logger.error("Error in Controller");
				return "sessionFree/contactUs";
			}

		}
	}

	@RequestMapping(value = "/getAsset", method = RequestMethod.POST)
	public @ResponseBody
	List<AmazonStructure> getStatus(HttpSession session,
			HttpServletRequest request) throws Exception {
		request.getParameter("asset");
		Users users = (Users) session.getAttribute("users");
		String folderName = request.getParameter("folderName");
		List<AmazonStructure> assetList = getAssestList(users,
				"/" + folderName, session);
		System.out.println(assetList);
		return assetList;
	}

	private List<AmazonStructure> getAssestList(Users users, String path,
			HttpSession session) throws Exception {
		String[] split = null;
		List<AmazonStructure> leftFolder = new ArrayList<AmazonStructure>();
		ServletContext context = session.getServletContext();
		String realContextPath = context.getRealPath("") + "/resources/";
		session.setAttribute("realContextPath", realContextPath);
		if (path == null)
			path = users.getUserId() + "";
		else
			path = users.getUserId() + "" + path;
		List<String> assetList = repositoryService.getAssetList(path);
		AmazonStructure tempAmazonStructure = null;
		int no_of_File_folder_inside = 0;
		AmazonStructure amazonStructure = null;
		for (String asset : assetList) {
			amazonStructure = new AmazonStructure();
			amazonStructure.setNo_of_File_folder_inside(1);
			split = asset.split("/");
			amazonStructure.setName(split[1]);
			for (int i = 0; i < split.length; i++) {
				amazonStructure.setUserId(users.getUserId() + "");
				if (i == 1) {
					if (!leftFolder.contains(amazonStructure)) {
						if (i == split.length - 1) {
							amazonStructure.setFullPath(path + asset);
							amazonStructure.setImageLocation(asset);
							repositoryService.getAssetByName(null,
									amazonStructure.getFullPath(), session);
						} else
							amazonStructure
									.setImageLocation(ProjectConstant.PICTURE_FOLDER);
						leftFolder.add(amazonStructure);
					} else {
						int index = leftFolder.indexOf(amazonStructure);
						tempAmazonStructure = leftFolder.get(index);
						no_of_File_folder_inside = tempAmazonStructure
								.getNo_of_File_folder_inside();
						tempAmazonStructure
								.setNo_of_File_folder_inside(no_of_File_folder_inside + 1);
						// increment no_of_File_folder_inside by 1;
					}
				}
			}

		}
		return leftFolder;
	}
}
