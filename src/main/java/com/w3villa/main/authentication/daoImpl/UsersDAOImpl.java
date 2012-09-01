package com.w3villa.main.authentication.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.w3villa.main.authentication.dao.CustomHibernateDAOSupport;
import com.w3villa.main.authentication.dao.UsersDAO;
import com.w3villa.main.authentication.domain.Users;

@Component
public class UsersDAOImpl extends CustomHibernateDAOSupport implements UsersDAO {

	@Autowired
	public UsersDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public List<Users> getUsersList() {
		Query query = getSession().createQuery("from Users");
		List<Users> list = query.list();
		return list;
	}

	@Override
	public Users findByEmailId(String emailId, boolean disableLazy) {
		Users users = null;
		Criteria ctr = getSession().createCriteria(Users.class);
		ctr.add(Restrictions.eq("emailId", users.getEmailId()).ignoreCase());
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

	@Override
	public void saveUser(Users users) {
		getHibernateTemplate().saveOrUpdate(users);

	}

	@Override
	public Users findByUserName(String userName, boolean disableLazy) {
		Users users = null;
		Criteria ctr = getSession().createCriteria(Users.class);
		ctr.add(Restrictions.eq("userName", userName).ignoreCase());
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

	@Override
	public void update(Users users) {
		getHibernateTemplate().merge(users);
	}

	@Override
	public Users findById(int id, boolean disableLazy) {
		Users users = null;
		Criteria ctr = getSession().createCriteria(Users.class);
		ctr.add(Restrictions.eq("userId", id));
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

	@Override
	public void delete(int id) {
		Users users = getHibernateTemplate().get(Users.class, id);
		getHibernateTemplate().delete(users);
	}

}
