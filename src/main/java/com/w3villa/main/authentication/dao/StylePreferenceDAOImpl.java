package com.w3villa.main.authentication.dao;

import java.util.List;

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

	public List<StylePreference> getStylePreferenceList() {
		Query query = getSession().createQuery("from StylePreference");
		List<StylePreference> list = query.list();
		return list;
	}

	public StylePreference getById(int id) {

		return getHibernateTemplate().get(StylePreference.class, id);
	}

	public void saveStylePreference(StylePreference stylePreference) {
		getHibernateTemplate().saveOrUpdate(stylePreference);
	}
}
