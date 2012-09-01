package com.w3villa.main.authentication.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.w3villa.main.authentication.dao.CustomHibernateDAOSupport;
import com.w3villa.main.authentication.dao.UserRolesDAO;
import com.w3villa.main.authentication.domain.UserRoles;

@Component
public class UserRolesDAOImpl extends CustomHibernateDAOSupport implements UserRolesDAO {
	@Autowired
	public UserRolesDAOImpl(SessionFactory sessionFactory){
		setSessionFactory(sessionFactory);
	}

	public void saveRole(UserRoles userRoles) {
		getHibernateTemplate().save(userRoles);
	}
}
