package com.w3villa.main.authentication.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRolesDAOImpl extends CustomHibernateDAOSupport implements UserRolesDAO {
	@Autowired
	public UserRolesDAOImpl(SessionFactory sessionFactory){
		setSessionFactory(sessionFactory);
	}
}
