package com.w3villa.main.authentication.daoImpl;

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
import com.w3villa.main.authentication.dao.ImageMappingDAO;
import com.w3villa.main.authentication.domain.ImageMapping;

@Component
public class ImageMappingDAOImpl extends CustomHibernateDAOSupport implements
		ImageMappingDAO {
	@Autowired
	public ImageMappingDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.w3villa.main.authentication.dao.ImageMappingDAO#
	 * getImageMappingListFrUserId(long)
	 */
	@Override
	public List<ImageMapping> getImageMappingListFrUserId(int userId) {
		Criteria criteria = getSession().createCriteria(ImageMapping.class);
		criteria.add(Restrictions.eq("users.userId", userId));
		criteria.addOrder(Order.asc("sequenceNo"));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.w3villa.main.authentication.dao.ImageMappingDAO#saveImageMapping(
	 * com.w3villa.main.authentication.domain.ImageMapping)
	 */
	@Override
	public void saveImageMapping(ImageMapping imageMapping) {
		getHibernateTemplate().save(imageMapping);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.w3villa.main.authentication.dao.ImageMappingDAO#updateImageMapping
	 * (com.w3villa.main.authentication.domain.ImageMapping)
	 */
	@Override
	public void updateImageMapping(ImageMapping imageMapping) {
		getHibernateTemplate().update(imageMapping);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.w3villa.main.authentication.dao.ImageMappingDAO#getById(long)
	 */
	@Override
	public ImageMapping getById(int id) {
		return getHibernateTemplate().get(ImageMapping.class, id);
	}

	@Override
	public int getNewSequenceNo(int userId) {
		int seqNo = 0;
		Criteria criteria = getSession().createCriteria(ImageMapping.class);
		criteria.add(Restrictions.eq("users.userId", userId));
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

	@Override
	public List<ImageMapping> listRecordsFromTo(int userId, int fromSeqNo,
			int toSeqNo) {
		List<ImageMapping> imageMappings = null;
		Criteria criteria = getSession().createCriteria(ImageMapping.class);
		criteria.add(Restrictions.eq("users.userId", userId));
		criteria.add(Restrictions.between("sequenceNo", fromSeqNo, toSeqNo));
		imageMappings = criteria.list();
		return imageMappings;
	}

	@Override
	public ImageMapping recordsAccToSeq(int userId, int seqNo) {
		ImageMapping imageMapping = null;
		Criteria criteria = getSession().createCriteria(ImageMapping.class);
		criteria.add(Restrictions.eq("users.userId", userId));
		criteria.add(Restrictions.eq("sequenceNo", seqNo));
		List<ImageMapping> imageMappings = criteria.list();
		if (imageMappings != null && imageMappings.size() > 0) {
			for (ImageMapping imageMappingInner : imageMappings) {
				imageMapping = imageMappingInner;
			}
		}
		return imageMapping;
	}

	@Override
	public void deleteRecordsByUserId(int userId) {

		Query query = getSession().createQuery(
				"delete from ImageMapping imageMapping where users.userId = "
						+ userId);
		query.executeUpdate();

	}

	@Override
	public void deleteRecordsById(int id) {

		Query query = getSession().createQuery(
				"delete from ImageMapping imageMapping where imageMappingId = "
						+ id);
		query.executeUpdate();

	}

	@Override
	public boolean updateSeqNo(int id, int seqNo) {
		Query query = getSession().createQuery(
				"update ImageMapping imageMapping set sequenceNo = " + seqNo
						+ "  where imageMappingId = " + id);
		query.executeUpdate();
		return true;
	}

}
