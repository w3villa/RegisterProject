package com.w3villa.main.authentication.userServiceImpl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.w3villa.main.authentication.bean.ContactUsBean;
import com.w3villa.main.authentication.dao.ContactUsDAO;
import com.w3villa.main.authentication.domain.ContactUs;
import com.w3villa.main.authentication.userService.ContactUsService;
import com.w3villa.main.authentication.userService.CustomMailService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ContactUsServiceImpl implements ContactUsService {

	@Autowired
	private ContactUsDAO contactUsDAO;

	@Autowired
	private CustomMailService mailService;

	@Override
	public void saveContactUs(ContactUsBean contactUsBean) {
		ContactUs contactUs = new ContactUs();
		BeanUtils.copyProperties(contactUsBean, contactUs);

		mailService.sendMailForContactUs(contactUsBean);

		contactUs.setCreatedDt(new Date());
		contactUsDAO.saveContactUs(contactUs);

	}

}
