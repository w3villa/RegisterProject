package com.w3villa.main.authentication.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.w3villa.main.authentication.domain.StylePreference;

@Component
public class StylePreferenceDAOImpl extends CustomHibernateDAOSupport implements
		StylePreferenceDAO {
	@Autowired
	public StylePreferenceDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public List<StylePreference> getStylePreferenceList() {
		Query query = getSession().createQuery("from StylePreference");
		List<StylePreference> list = query.list();
		return list;
	}

	@Override
	public StylePreference getById(int id) {

		return getHibernateTemplate().get(StylePreference.class, id);
	}

	@Override
	public void saveStylePreference(StylePreference stylePreference) {
		getHibernateTemplate().saveOrUpdate(stylePreference);
	}

	@Override
	public List<StylePreference> getAllStylePreference() {
		Criteria criteria = getSession().createCriteria(StylePreference.class);
		return criteria.list();
	}

	@Override
	public void update(StylePreference stylePreference) {
		getHibernateTemplate().merge(stylePreference);
	}

	@Override
	public void delete(int id) {
		StylePreference sp = getById(id);
		getHibernateTemplate().delete(sp);

	}
}
