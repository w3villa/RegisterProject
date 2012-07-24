package com.w3villa.main.authentication.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.w3villa.main.authentication.domain.ContactUs;

@Component
public class ContactUsDAOImpl extends CustomHibernateDAOSupport implements
		ContactUsDAO {

	@Autowired
	public ContactUsDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void saveContactUs(ContactUs contactUs) {
		getHibernateTemplate().saveOrUpdate(contactUs);
	}

}
