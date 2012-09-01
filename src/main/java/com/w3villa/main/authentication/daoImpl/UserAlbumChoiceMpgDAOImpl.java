package com.w3villa.main.authentication.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.w3villa.main.authentication.dao.CustomHibernateDAOSupport;
import com.w3villa.main.authentication.dao.UserAlbumChoiceMpgDAO;
import com.w3villa.main.authentication.domain.UserAlbumChoiceMpg;

@Component
public class UserAlbumChoiceMpgDAOImpl extends CustomHibernateDAOSupport implements UserAlbumChoiceMpgDAO {

	@Autowired
	public UserAlbumChoiceMpgDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.daoImpl.UserAlbumChoiceMpgDAO#saveUserAlbumChoiceMpg(com.w3villa.main.authentication.domain.UserAlbumChoiceMpg)
	 */
	@Override
	public void saveUserAlbumChoiceMpg(UserAlbumChoiceMpg userAlbumChoiceMpg) {
		getHibernateTemplate().saveOrUpdate(userAlbumChoiceMpg);

	}

}
