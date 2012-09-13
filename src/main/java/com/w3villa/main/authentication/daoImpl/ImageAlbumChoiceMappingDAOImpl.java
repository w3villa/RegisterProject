package com.w3villa.main.authentication.daoImpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.w3villa.main.authentication.dao.CustomHibernateDAOSupport;
import com.w3villa.main.authentication.dao.ImageAlbumChoiceMappingDAO;
import com.w3villa.main.authentication.domain.ImageAlbumChoiceMapping;

@Component
public class ImageAlbumChoiceMappingDAOImpl extends CustomHibernateDAOSupport implements ImageAlbumChoiceMappingDAO {

	@Autowired
	public ImageAlbumChoiceMappingDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.daoImpl.ImageAlbumChoiceMappingDAO#getImageAlbumChoiceMapping(int)
	 */
	@Override
	public List<ImageAlbumChoiceMapping> getImageAlbumChoiceMapping(
			int userAlbumChoiceMpgId) {
		Criteria criteria = getSession().createCriteria(
				ImageAlbumChoiceMapping.class);
		criteria.add(Restrictions.eq("userAlbumChoiceMpg.userAlbumChoiceMpgId",
				userAlbumChoiceMpgId));
		criteria.addOrder(Order.asc("sequenceNo"));
		List<ImageAlbumChoiceMapping> imageAlbumChoiceMappings = criteria
				.list();
		return imageAlbumChoiceMappings;

	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.daoImpl.ImageAlbumChoiceMappingDAO#saveImageAlbumChoiceMapping(com.w3villa.main.authentication.domain.ImageAlbumChoiceMapping)
	 */
	@Override
	public Serializable saveImageAlbumChoiceMapping(
			ImageAlbumChoiceMapping imageAlbumChoiceMapping) {
		return getHibernateTemplate().save(imageAlbumChoiceMapping);
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.daoImpl.ImageAlbumChoiceMappingDAO#updateImageAlbumChoiceMapping(com.w3villa.main.authentication.domain.ImageAlbumChoiceMapping)
	 */
	@Override
	public boolean updateImageAlbumChoiceMapping(
			ImageAlbumChoiceMapping imageAlbumChoiceMapping) {
		getHibernateTemplate().update(imageAlbumChoiceMapping);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.daoImpl.ImageAlbumChoiceMappingDAO#getById(int)
	 */
	@Override
	public ImageAlbumChoiceMapping getById(int id) {
		return getHibernateTemplate().get(ImageAlbumChoiceMapping.class, id);
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.daoImpl.ImageAlbumChoiceMappingDAO#getNewSequenceNo(int)
	 */
	@Override
	public int getNewSequenceNo(int userAlbumChoiceMpgId) {
		int seqNo = 0;
		Criteria criteria = getSession().createCriteria(
				ImageAlbumChoiceMapping.class);
		criteria.add(Restrictions.eq("userAlbumChoiceMpg.userAlbumChoiceMpgId",
				userAlbumChoiceMpgId));
		criteria.setProjection(Projections.max("sequenceNo"));
		List<Integer> sequenceNos = criteria.list();
		if (sequenceNos != null && sequenceNos.size() > 0) {
			for (Integer sequenceNo : sequenceNos) {
				if (sequenceNo != null)
					seqNo = sequenceNo;
			}
		}
		return (seqNo + 1);
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.daoImpl.ImageAlbumChoiceMappingDAO#listRecordsFromTo(int, int, int)
	 */
	@Override
	public List<ImageAlbumChoiceMapping> listRecordsFromTo(
			int userAlbumChoiceMpgId, int fromSeqNo, int toSeqNo) {
		List<ImageAlbumChoiceMapping> imageAlbumChoiceMappings = null;
		Criteria criteria = getSession().createCriteria(
				ImageAlbumChoiceMapping.class);
		criteria.add(Restrictions.eq("userAlbumChoiceMpg.userAlbumChoiceMpgId",
				userAlbumChoiceMpgId));
		criteria.add(Restrictions.between("sequenceNo", fromSeqNo, toSeqNo));
		imageAlbumChoiceMappings = criteria.list();
		return imageAlbumChoiceMappings;
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.daoImpl.ImageAlbumChoiceMappingDAO#recordsAccToSeq(int, int)
	 */
	@Override
	public ImageAlbumChoiceMapping recordsAccToSeq(int userAlbumChoiceMpgId,
			int seqNo) {
		ImageAlbumChoiceMapping imageAlbumChoiceMapping = null;
		Criteria criteria = getSession().createCriteria(
				ImageAlbumChoiceMapping.class);
		criteria.add(Restrictions.eq("userAlbumChoiceMpg.userAlbumChoiceMpgId",
				userAlbumChoiceMpgId));
		criteria.add(Restrictions.eq("sequenceNo", seqNo));
		List<ImageAlbumChoiceMapping> imageAlbumChoiceMappings = criteria
				.list();
		if (imageAlbumChoiceMappings != null
				&& imageAlbumChoiceMappings.size() > 0) {
			for (ImageAlbumChoiceMapping imageAlbumChoiceMappingInner : imageAlbumChoiceMappings) {
				imageAlbumChoiceMapping = imageAlbumChoiceMappingInner;
			}
		}
		return imageAlbumChoiceMapping;
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.daoImpl.ImageAlbumChoiceMappingDAO#deleteRecordsByUserAlbumChoiceMpgId(int)
	 */
	@Override
	public void deleteRecordsByUserAlbumChoiceMpgId(int userAlbumChoiceMpgId) {

		Query query = getSession()
				.createQuery(
						"delete from ImageAlbumChoiceMapping imageAlbumChoiceMapping where userAlbumChoiceMpg.userAlbumChoiceMpgId = "
								+ userAlbumChoiceMpgId);
		query.executeUpdate();

	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.daoImpl.ImageAlbumChoiceMappingDAO#deleteRecordsById(int)
	 */
	@Override
	public void deleteRecordsById(int imageAlbumChoiceMappingId) {

		Query query = getSession()
				.createQuery(
						"delete from ImageAlbumChoiceMapping imageAlbumChoiceMapping where imageAlbumChoiceMappingId = "
								+ imageAlbumChoiceMappingId);
		query.executeUpdate();

	}

}
