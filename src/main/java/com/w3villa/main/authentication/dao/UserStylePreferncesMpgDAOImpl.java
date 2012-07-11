package com.w3villa.main.authentication.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.w3villa.main.authentication.domain.UserStylePreferncesMpg;

@Component
public class UserStylePreferncesMpgDAOImpl extends CustomHibernateDAOSupport
		implements UserStylePreferncesMpgDAO {

	@Autowired
	public UserStylePreferncesMpgDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public void saveUserStylePreferncesMpg(
			UserStylePreferncesMpg userStylePreferncesMpg) {
		getHibernateTemplate().saveOrUpdate(userStylePreferncesMpg);

	}

}
