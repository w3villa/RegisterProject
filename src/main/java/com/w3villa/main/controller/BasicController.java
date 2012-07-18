package com.w3villa.main.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BasicController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String forward(Locale locale, Model model) {
		return "sessionFree/welcomePage";
	}

	@RequestMapping(value = "/invalidSession.htm", method = RequestMethod.GET)
	public String invalidSession() {

		return "sessionFree/login";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, ModelMap model) {
		String errorSession = request.getParameter("error");
		if (errorSession != null) {
			model.addAttribute("errorSession", errorSession);
		}
		return "sessionFree/login";

	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {

		model.addAttribute("error", "true");
		return "sessionFree/login";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {

		return "sessionFree/login";

	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDenied(ModelMap model) {

		return "sessionFree/accessDenied";

	}

	@RequestMapping(value = "/faq", method = RequestMethod.GET)
	public String faq(ModelMap model) {

		return "sessionFree/faq";

	}

	@RequestMapping(value = "/contactus", method = RequestMethod.GET)
	public String contactUs(ModelMap model) {

		return "sessionFree/contactUs";

	}

}