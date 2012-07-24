package com.w3villa.main.authentication.userServiceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.w3villa.constants.ProjectConstant;
import com.w3villa.main.authentication.bean.ContactUsBean;
import com.w3villa.main.authentication.userService.CustomMailService;

@Component
public class CustomMailServiceImpl implements CustomMailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;


	@Override
	public void sendMailForContactUs(final ContactUsBean contactUsBean) {
		final Map<Object, Object> model = new HashMap<Object, Object>();
		model.put("contactUsBean", contactUsBean);
		MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
			String subject = ProjectConstant.MAIL_SUBJECT_CONTACT_US;
			String from = ProjectConstant.MAIL_FORM_CONTACT_US;

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper messageHelper = new MimeMessageHelper(
						mimeMessage);
				messageHelper.setTo(contactUsBean.getEmail());
				messageHelper.setFrom(from);
				messageHelper.setSubject(subject);

				String body = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, "velocity/contactUs.vm",
						model);
				System.out.println("body=" + body);
				messageHelper.setText(body, true);
			}
		};
		mailSender.send(mimeMessagePreparator);
		System.out.println("Sent e-mail to " + contactUsBean.getEmail());
	}

}
