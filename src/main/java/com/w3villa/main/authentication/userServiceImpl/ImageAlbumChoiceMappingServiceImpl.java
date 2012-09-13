package com.w3villa.main.authentication.userServiceImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w3villa.main.authentication.dao.ImageAlbumChoiceMappingDAO;
import com.w3villa.main.authentication.domain.ImageAlbumChoiceMapping;
import com.w3villa.main.authentication.userService.ImageAlbumChoiceMappingService;

@Service
public class ImageAlbumChoiceMappingServiceImpl implements ImageAlbumChoiceMappingService {

	@Autowired
	private ImageAlbumChoiceMappingDAO imageAlbumChoiceMappingDAO;

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.userServiceImpl.ImageAlbumChoiceMappingService#getImageAlbumChoiceMapping(int)
	 */
	@Override
	public List<ImageAlbumChoiceMapping> getImageAlbumChoiceMapping(
			int userAlbumChoiceMpgId) {
		return imageAlbumChoiceMappingDAO
				.getImageAlbumChoiceMapping(userAlbumChoiceMpgId);
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.userServiceImpl.ImageAlbumChoiceMappingService#saveImageAlbumChoiceMapping(com.w3villa.main.authentication.domain.ImageAlbumChoiceMapping)
	 */
	@Override
	public Serializable saveImageAlbumChoiceMapping(
			ImageAlbumChoiceMapping imageAlbumChoiceMapping) {
		return imageAlbumChoiceMappingDAO
				.saveImageAlbumChoiceMapping(imageAlbumChoiceMapping);
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.userServiceImpl.ImageAlbumChoiceMappingService#updateImageAlbumChoiceMapping(com.w3villa.main.authentication.domain.ImageAlbumChoiceMapping)
	 */
	@Override
	public boolean updateImageAlbumChoiceMapping(
			ImageAlbumChoiceMapping imageAlbumChoiceMapping) {
		return imageAlbumChoiceMappingDAO
				.updateImageAlbumChoiceMapping(imageAlbumChoiceMapping);
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.userServiceImpl.ImageAlbumChoiceMappingService#getById(int)
	 */
	@Override
	public ImageAlbumChoiceMapping getById(int id) {
		return imageAlbumChoiceMappingDAO.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.userServiceImpl.ImageAlbumChoiceMappingService#getNewSequenceNo(int)
	 */
	@Override
	public int getNewSequenceNo(int userAlbumChoiceMpgId) {
		return imageAlbumChoiceMappingDAO
				.getNewSequenceNo(userAlbumChoiceMpgId);
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.userServiceImpl.ImageAlbumChoiceMappingService#listRecordsFromTo(int, int, int)
	 */
	@Override
	public List<ImageAlbumChoiceMapping> listRecordsFromTo(
			int userAlbumChoiceMpgId, int fromSeqNo, int toSeqNo) {
		return imageAlbumChoiceMappingDAO.listRecordsFromTo(
				userAlbumChoiceMpgId, fromSeqNo, toSeqNo);
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.userServiceImpl.ImageAlbumChoiceMappingService#recordsAccToSeq(int, int)
	 */
	@Override
	public ImageAlbumChoiceMapping recordsAccToSeq(int userAlbumChoiceMpgId,
			int seqNo) {
		return imageAlbumChoiceMappingDAO.recordsAccToSeq(userAlbumChoiceMpgId,
				seqNo);
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.userServiceImpl.ImageAlbumChoiceMappingService#deleteRecordsByUserAlbumChoiceMpgId(int)
	 */
	@Override
	public void deleteRecordsByUserAlbumChoiceMpgId(int userAlbumChoiceMpgId) {
		imageAlbumChoiceMappingDAO
				.deleteRecordsByUserAlbumChoiceMpgId(userAlbumChoiceMpgId);
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.userServiceImpl.ImageAlbumChoiceMappingService#deleteRecordsById(int)
	 */
	@Override
	public void deleteRecordsById(int imageAlbumChoiceMappingId) {
		imageAlbumChoiceMappingDAO.deleteRecordsById(imageAlbumChoiceMappingId);
	}

}
