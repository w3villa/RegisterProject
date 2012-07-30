package com.w3villa.main.authentication.userService;

import java.util.List;

import com.w3villa.main.authentication.bean.ContactUsBean;

public interface ContactUsService {
	public void saveContactUs(ContactUsBean contactUsBean);

	List<ContactUsBean> getAllContactUs();
}
