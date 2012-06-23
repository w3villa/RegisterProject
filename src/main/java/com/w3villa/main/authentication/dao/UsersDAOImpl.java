package com.w3villa.main.authentication.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.w3villa.main.authentication.domain.UserRoles;
import com.w3villa.main.authentication.domain.Users;

@Component
public class UsersDAOImpl extends CustomHibernateDAOSupport implements UsersDAO {

	@Autowired
	public UsersDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public List<Users> getUsersList() {
		Query query = getSession().createQuery("from Users");
		List<Users> list = query.list();
		return list;
	}

	public Users findByEmailId(String emailId) {
		UserRoles userRoles = null;
		Users users = new Users();
		users.setEmailId(emailId);
		List<Users> usersList = getHibernateTemplate().findByExample(users);
		if (usersList.size() > 0) {
			users = usersList.get(0);
		} else
			users = null;
		return users;
	}

	public void saveUser(Users users) {
		getHibernateTemplate().save(users);

	}

}
