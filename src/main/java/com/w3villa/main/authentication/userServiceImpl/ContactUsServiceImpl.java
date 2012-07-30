package com.w3villa.main.authentication.userServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	@Override
	public List<ContactUsBean> getAllContactUs() {
		List<ContactUsBean> contactUsBeanList = null;
		List<ContactUs> contactUsList = contactUsDAO.getAllContactUs();
		if (!contactUsList.isEmpty()) {
			contactUsBeanList = new ArrayList<ContactUsBean>();
		}
		for (ContactUs contactUs : contactUsList) {
			contactUsBeanList.add(getContactUsBean(contactUs));
		}
		return contactUsBeanList;
	}

	private ContactUsBean getContactUsBean(ContactUs contactUs) {
		ContactUsBean ContactUsBean = new ContactUsBean();
		BeanUtils.copyProperties(contactUs, ContactUsBean,
 new String[] {
				"contactUsId", "createdDt" });
		if (contactUs.getContactUsId() != null)
			ContactUsBean.setContactUsId(contactUs.getContactUsId() + "");
		ContactUsBean.setCreatedDt(contactUs.getCreatedDt().toGMTString());
		return ContactUsBean;
	}

	private ContactUs getContactUs(ContactUsBean ContactUsBean) {
		ContactUs contactUs = null;
		if (ContactUsBean.getContactUsId() != null
				&& !ContactUsBean.getContactUsId().equals(""))
			contactUs = contactUsDAO.getById(Integer.parseInt(ContactUsBean
					.getContactUsId()));
		else {
			contactUs = new ContactUs();
		}
		BeanUtils.copyProperties(ContactUsBean, contactUs,
 new String[] {
				"contactUsId", "createdDt" });
		contactUs.setCreatedDt(new Date(ContactUsBean.getCreatedDt()));
		return contactUs;
	}

}
