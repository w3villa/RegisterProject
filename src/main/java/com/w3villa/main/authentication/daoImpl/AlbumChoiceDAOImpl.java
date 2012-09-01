package com.w3villa.main.authentication.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.w3villa.main.authentication.dao.AlbumChoiceDAO;
import com.w3villa.main.authentication.dao.CustomHibernateDAOSupport;
import com.w3villa.main.authentication.domain.AlbumChoice;

@Component
public class AlbumChoiceDAOImpl extends CustomHibernateDAOSupport implements AlbumChoiceDAO {
	@Autowired
	public AlbumChoiceDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.dao.AlbumChoiceDAO#getAlbumChoiceList()
	 */
	@Override
	public List<AlbumChoice> getAlbumChoiceList() {
		Query query = getSession().createQuery("from AlbumChoice");
		List<AlbumChoice> list = query.list();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.dao.AlbumChoiceDAO#getById(int)
	 */
	@Override
	public AlbumChoice getById(int id) {

		return getHibernateTemplate().get(AlbumChoice.class, id);
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.dao.AlbumChoiceDAO#saveAlbumChoice(com.w3villa.main.authentication.domain.AlbumChoice)
	 */
	@Override
	public void saveAlbumChoice(AlbumChoice albumChoice) {
		getHibernateTemplate().saveOrUpdate(albumChoice);
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.dao.AlbumChoiceDAO#getAllAlbumChoice()
	 */
	@Override
	public List<AlbumChoice> getAllAlbumChoice() {
		Criteria criteria = getSession().createCriteria(AlbumChoice.class);
		return criteria.list();
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.dao.AlbumChoiceDAO#update(com.w3villa.main.authentication.domain.AlbumChoice)
	 */
	@Override
	public void update(AlbumChoice albumChoice) {
		getHibernateTemplate().merge(albumChoice);
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.dao.AlbumChoiceDAO#delete(int)
	 */
	@Override
	public void delete(int id) {
		AlbumChoice ac = getById(id);
		getHibernateTemplate().delete(ac);

	}
}
