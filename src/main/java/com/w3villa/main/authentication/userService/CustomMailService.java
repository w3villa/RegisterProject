package com.w3villa.main.authentication.userService;

import com.w3villa.main.authentication.bean.ContactUsBean;

public interface CustomMailService {
	public void sendMailForContactUs(ContactUsBean contactUsBean);
}
