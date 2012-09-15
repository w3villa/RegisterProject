package com.w3villa.main.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
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
import com.w3villa.main.authentication.domain.AlbumChoice;
import com.w3villa.main.authentication.domain.UserAlbumChoiceMpg;
import com.w3villa.main.authentication.domain.Users;
import com.w3villa.main.authentication.userService.AlbumChoiceService;
import com.w3villa.main.authentication.userService.ContactUsService;
import com.w3villa.main.authentication.userService.RepositoryService;
import com.w3villa.main.authentication.userService.StylePreferenceService;
import com.w3villa.main.authentication.userService.UsersService;
import com.w3villa.main.util.CommonUtil;
import com.w3villa.voBean.UploadBean;

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
	private AlbumChoiceService albumChoiceService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private ContactUsService contactUsService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CommonUtil commonUtil;

	@Autowired
	private PasswordEncoder encoder;

	@RequestMapping(value = "/welcomeUser", method = RequestMethod.GET)
	public String homeUser(Locale locale, Model model,
			HttpServletRequest request, HttpSession session) throws Exception {
		commonUtil.updateUsersInSession(session);
		session.setAttribute("role", ProjectConstant.ROLE_USER);
		commonUtil.getImages(model, session, ProjectConstant.IMAGE_TYPE_LOGO);
		model.addAttribute("uploadBean", new UploadBean());
		return "homeUser";
	}

	@RequestMapping(value = "/welcomeAdmin", method = RequestMethod.GET)
	public String homeAdmin(Locale locale, Model model,
			HttpServletRequest request, HttpSession session) throws Exception {
		commonUtil.updateUsersInSession(session);
		session.setAttribute("role", ProjectConstant.ROLE_ADMIN);
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
			HttpSession session = null;
			try {
				String[] stylePreferences = request
						.getParameterValues("stylePreferences");
				usersService.saveUser(userEntityBean, stylePreferences);
				model.addAttribute("registerSuccess", "true");
				boolean successFlag = autoLogin(userEntityBean.getUserName(),
						request);
				if (successFlag) {
					session = request.getSession();
					session.setAttribute("emailId", userEntityBean.getEmailId());
					Users users = usersService.findByUserName(
							userEntityBean.getUserName(), true);
					session.setAttribute("users", users);
					session.setAttribute("role", ProjectConstant.ROLE_USER);
					commonUtil.getAlbumChoices(model, session);
				}
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

	@RequestMapping(value = "/albumChoiceUpdate", method = RequestMethod.GET)
	public String albumChoiceUpdate(Model model, HttpSession session) {
		commonUtil.updateUsersInSession(session);
		commonUtil.getAlbumChoices(model, session);
		return "albumChoice";

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

	public boolean autoLogin(String userName, HttpServletRequest request) {
		try {

			// Provides User information. Its a Spring interface :
			// org.springframework.security.core.userdetails.UserDetails
			UserDetails userDetails = userDetailsService
					.loadUserByUsername(userName);
			// Create a token using spring provided class :
			// org.springframework.security.authentication.UsernamePasswordAuthenticationToken
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					userName, userDetails.getPassword(),
					userDetails.getAuthorities());

			// generate session if one doesn't exist
			request.getSession();

			// save details as WebAuthenticationDetails records the remote
			// address and will also set the session Id if a session already
			// exists (it won't create one).
			token.setDetails(new WebAuthenticationDetails(request));

			// authenticationManager injected as spring bean, you can use custom
			// or spring provided authentication manager
			Authentication authentication = authenticationManager
					.authenticate(token);

			// Need to set this as thread locale as available throughout
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);

			// Set SPRING_SECURITY_CONTEXT attribute in session as Spring
			// identifies context through this attribute
			request.getSession()
					.setAttribute(
							HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
							SecurityContextHolder.getContext());

		} catch (Exception e) {
			SecurityContextHolder.getContext().setAuthentication(null);
			logger.error("Exception", e);
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/saveAlbumChoice", method = RequestMethod.POST)
	public String saveAlbumChoice(Model model, HttpSession session,
			HttpServletRequest request) throws Exception {
		UserAlbumChoiceMpg userAlbumChoiceMpg = null;
		AlbumChoice albumChoice = null;
		Users usersSession = (Users) session.getAttribute("users");
		Users users = usersService.findByUserName(usersSession.getUserName(),
				true);
		String[] albumChoices = request.getParameterValues("album_choice");
		SortedSet<UserAlbumChoiceMpg> userAlbumChoiceMpgs = users
				.getUserAlbumChoiceMpgs();
		// userAlbumChoiceMpgs.clear();// empty set
		SortedSet<UserAlbumChoiceMpg> alreadyPresent = new TreeSet<UserAlbumChoiceMpg>();
		alreadyPresent.addAll(userAlbumChoiceMpgs);
		SortedSet<UserAlbumChoiceMpg> toBePresent = new TreeSet<UserAlbumChoiceMpg>();
		if (albumChoices != null && albumChoices.length != 0) {
			for (String albumChoiceId : albumChoices) {
				albumChoice = albumChoiceService.get(albumChoiceId);
				userAlbumChoiceMpg = new UserAlbumChoiceMpg();
				userAlbumChoiceMpg.setAlbumChoice(albumChoice);
				userAlbumChoiceMpg.setUsers(users);
				toBePresent.add(userAlbumChoiceMpg);
				if (!userAlbumChoiceMpgs.contains(userAlbumChoiceMpg)) {
					userAlbumChoiceMpgs.add(userAlbumChoiceMpg);
				}
			}
		}
		Iterator<UserAlbumChoiceMpg> iterator = alreadyPresent.iterator();
		while (iterator.hasNext()) {
			userAlbumChoiceMpg = iterator.next();
			if (!toBePresent.contains(userAlbumChoiceMpg)) {
				userAlbumChoiceMpgs.remove(userAlbumChoiceMpg);
			}
		}
		usersService.updateUsers(users);
		commonUtil.getAlbumChoices(model, session);
		commonUtil.updateUsersInSession(session);
		model.addAttribute("message", "Album choices saved Successfully.");
		return "albumChoice";
	}

	@RequestMapping(value = "/manageProfile", method = RequestMethod.GET)
	public String manageProfileNavigate(Model model, HttpSession session,
			HttpServletRequest request) throws Exception {
		Users usersSession = (Users) session.getAttribute("users");
		Users users = usersService.findByUserName(usersSession.getUserName(),
				true);
		UserEntityBean userEntityBean = new UserEntityBean();
		BeanUtils.copyProperties(userEntityBean, users);
		userEntityBean.setId(users.getUserId() + "");
		model.addAttribute(userEntityBean);
		session.setAttribute("users", users);
		return "manageProfile";
	}

	@RequestMapping(value = "/manageProfile", method = RequestMethod.POST)
	public String manageProfileSave(@Valid UserEntityBean userEntityBean,
			BindingResult result, Model model, HttpSession session,
			HttpServletRequest request) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("message",
					"Please correct the provided informations.");
			return "manageProfile";
		} else {

			String newPass = request.getParameter("newPass");
			if (newPass != null && !"".equals(newPass)) {
				String encodedPass = encoder.encode(newPass);
				userEntityBean.setPassword(encodedPass);
			}
			usersService.update(userEntityBean);
			userEntityBean = new UserEntityBean();
			Users usersSession = (Users) session.getAttribute("users");
			Users users = usersService.findByUserName(
					usersSession.getUserName(), true);
			BeanUtils.copyProperties(userEntityBean, users);
			model.addAttribute(userEntityBean);
			session.setAttribute("users", users);
			model.addAttribute("message", "Profile updated Successfully.");
			return "manageProfile";
		}
	}
}
