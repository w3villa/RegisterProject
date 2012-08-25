package com.w3villa.main.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.w3villa.constants.ProjectConstant;
import com.w3villa.main.authentication.bean.StylePreferenceBean;
import com.w3villa.main.authentication.bean.UserEntityBean;
import com.w3villa.main.authentication.userService.ContactUsService;
import com.w3villa.main.authentication.userService.StylePreferenceService;
import com.w3villa.main.authentication.userService.UsersService;

@Controller
public class CRUDController {

	@Autowired
	StylePreferenceService stylePreferenceService;

	@Autowired
	ContactUsService contactUsService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private PasswordEncoder encoder;

	@RequestMapping(value = "/crudStylePref", method = RequestMethod.GET)
	public String crudStylePrefNavigate(Locale locale, Model model,
			HttpServletRequest request) throws Exception {
		model.addAttribute(new StylePreferenceBean());
		model.addAttribute("objectList",
				stylePreferenceService.getAllStylePreference());
		return "crud/stylePreferencesCRUD";
	}

	@RequestMapping(value = "/crudStylePref", method = RequestMethod.POST)
	public String crudStyle(@Valid StylePreferenceBean stylePreferenceBean,
			BindingResult result, Model model, HttpServletRequest request)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("objectList",
					stylePreferenceService.getAllStylePreference());
			return "crud/stylePreferencesCRUD";
		} else {
			String operation = request.getParameter("operation");
			String Status = "";
			String message = "";
			if (ProjectConstant.CRUD_CRAETE.equals(operation)) {
				try {
					stylePreferenceService
							.saveStylePreference(stylePreferenceBean);
					Status = ProjectConstant.UPLOAD_STATUS_PASS;
					message = "Data saved successfully";
				} catch (Exception e) {
					e.printStackTrace();
					Status = ProjectConstant.UPLOAD_STATUS_FAIL;
					message = "Error saving data : " + e.getMessage();
				}
			} else if (ProjectConstant.CRUD_DELETE.equals(operation)) {
				try {
					stylePreferenceService.delete(stylePreferenceBean
							.getStylePreferenceId());
					Status = ProjectConstant.UPLOAD_STATUS_PASS;
					message = "Data deleted successfully";
				} catch (Exception e) {
					e.printStackTrace();
					Status = ProjectConstant.UPLOAD_STATUS_FAIL;
					message = "Error saving data : " + e.getMessage();
				}
			} else if (ProjectConstant.CRUD_UPDATE.equals(operation)) {
				try {
					stylePreferenceService.update(stylePreferenceBean);
					Status = ProjectConstant.UPLOAD_STATUS_PASS;
					message = "Data updated successfully";
				} catch (Exception e) {
					e.printStackTrace();
					Status = ProjectConstant.UPLOAD_STATUS_FAIL;
					message = "Error saving data : " + e.getMessage();
				}
			}
			model.addAttribute("objectList",
					stylePreferenceService.getAllStylePreference());
			model.addAttribute("status", Status);
			model.addAttribute("message", message);
			return "crud/stylePreferencesCRUD";
		}
	}

	@RequestMapping(value = "/contactUsView", method = RequestMethod.GET)
	public String contactUsViewNavigate(Locale locale, Model model,
			HttpServletRequest request) throws Exception {
		model.addAttribute("objectList", contactUsService.getAllContactUs());
		return "crud/contactUsView";
	}

	@RequestMapping(value = "/manageUsers", method = RequestMethod.GET)
	public String crudUsersNavigate(Locale locale, Model model,
			HttpServletRequest request) throws Exception {
		model.addAttribute(new UserEntityBean());
		model.addAttribute("objectList", usersService.getAllUserEntityBean());
		return "crud/usersCRUD";
	}

	@RequestMapping(value = "/crudUsers", method = RequestMethod.POST)
	public String crudUsers(@Valid UserEntityBean userEntityBean,
			BindingResult result, Model model, HttpServletRequest request)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("objectList",
					usersService.getAllUserEntityBean());
			return "crud/usersCRUD";
		} else {
			String operation = request.getParameter("operation");
			String Status = "";
			String message = "";
			if (ProjectConstant.CRUD_CRAETE.equals(operation)) {
				try {
					usersService.saveUser(userEntityBean, null);
					Status = ProjectConstant.UPLOAD_STATUS_PASS;
					message = "Data saved successfully";
				} catch (Exception e) {
					e.printStackTrace();
					Status = ProjectConstant.UPLOAD_STATUS_FAIL;
					message = "Error saving data : " + e.getMessage();
				}
			} else if (ProjectConstant.CRUD_DELETE.equals(operation)) {
				try {
					usersService
							.delete(Integer.parseInt(userEntityBean.getId()));
					Status = ProjectConstant.UPLOAD_STATUS_PASS;
					message = "Data deleted successfully";
				} catch (Exception e) {
					e.printStackTrace();
					Status = ProjectConstant.UPLOAD_STATUS_FAIL;
					message = "Error saving data : " + e.getMessage();
				}
			} else if (ProjectConstant.CRUD_UPDATE.equals(operation)) {
				try {
					// stylePreferenceService.update(stylePreferenceBean);
					String newPass = request.getParameter("newPass");
					if (!"".equals(newPass)) {
						String encodedPass = encoder.encode(newPass);
						userEntityBean.setPassword(encodedPass);
					}
					usersService.update(userEntityBean);
					Status = ProjectConstant.UPLOAD_STATUS_PASS;
					message = "Data updated successfully";
				} catch (Exception e) {
					e.printStackTrace();
					Status = ProjectConstant.UPLOAD_STATUS_FAIL;
					message = "Error saving data : " + e.getMessage();
				}
			}
			model.addAttribute("objectList",
					usersService.getAllUserEntityBean());
			model.addAttribute("status", Status);
			model.addAttribute("message", message);
			return "crud/usersCRUD";
		}
	}

}
