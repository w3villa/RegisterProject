package com.w3villa.main.authentication.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.w3villa.main.authentication.dao.ContactUsDAO;
import com.w3villa.main.authentication.dao.CustomHibernateDAOSupport;
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

	@Override
	public List<ContactUs> getAllContactUs() {
		Criteria criteria = getSession().createCriteria(ContactUs.class);
		criteria.addOrder(Order.desc("createdDt"));
		return criteria.list();
	}

	@Override
	public ContactUs getById(int id) {
		return getHibernateTemplate().get(ContactUs.class, id);
	}

}
