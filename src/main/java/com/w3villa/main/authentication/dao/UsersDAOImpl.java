package com.w3villa.main.authentication.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

	public Users findByEmailId(String emailId, boolean disableLazy) {
		UserRoles userRoles = null;
		Users users = new Users();
		users.setEmailId(emailId);
		Criteria ctr = getSession().createCriteria(Users.class);
		ctr.add(Restrictions.eq("emailId", users.getEmailId()));
		if (disableLazy)
			ctr.setFetchMode("userStylePreferncesMpgs", FetchMode.JOIN);
		List<Users> usersList = ctr.list();
		// List<Users> usersList = getHibernateTemplate().findByExample(users);
		if (usersList.size() > 0) {
			users = usersList.get(0);
		} else
			users = null;
		return users;
	}

	public void saveUser(Users users) {
		getHibernateTemplate().saveOrUpdate(users);

	}

}
