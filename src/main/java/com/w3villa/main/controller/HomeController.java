package com.w3villa.main.controller;

import java.util.Locale;

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

import com.w3villa.main.authentication.bean.UserEntityBean;
import com.w3villa.main.authentication.domain.Users;
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

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String forward(Locale locale, Model model) {
		return "welcomePage";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		HttpSession session = request.getSession(false);
		Users users = usersService.findByEmailId(user.getUsername(), true);
		session.setAttribute("users", users);
		session.setAttribute("pp", "pp");
		return "home";
	}

	@RequestMapping(value = "/invalidSession.htm", method = RequestMethod.GET)
	public String invalidSession() {

		return "login";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, ModelMap model) {
		String errorSession = request.getParameter("error");
		if (errorSession != null) {
			model.addAttribute("errorSession", errorSession);
		}
		return "login";

	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {

		model.addAttribute("error", "true");
		return "login";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {

		return "login";

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
		return "registerMe";
	}

	@RequestMapping(value = "/RegisterMe", method = RequestMethod.POST)
	public String RegisterMe(@Valid UserEntityBean userEntityBean,
			BindingResult result, Model model, HttpServletRequest request) {
		logger.info("RegisterMe() entry.");
		if (result.hasErrors()) {
			model.addAttribute("loginFail", "fail");
			logger.info("RegisterMe() exit.");
			return "registerMe";
		} else {
			HttpSession session = request.getSession();
			try {
				String[] stylePreferences = request
						.getParameterValues("stylePreferences");
				usersService.saveUser(userEntityBean, stylePreferences);
				session.setAttribute("emailId", userEntityBean.getEmailId());
				model.addAttribute("registerSuccess", "true");
				logger.info("RegisterMe() exit.");
				return "login";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("error", e.getMessage());
				logger.error("Error in Controller");
				return "registerMe";
			}

		}
	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDenied(ModelMap model) {

		return "accessDenied";

	}
}
