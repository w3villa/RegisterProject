package com.w3villa.main.authentication.userServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w3villa.main.authentication.dao.ImageMappingDAO;
import com.w3villa.main.authentication.domain.ImageMapping;
import com.w3villa.main.authentication.domain.Users;
import com.w3villa.main.authentication.userService.ImageMappingService;

@Service
public class ImageMappingServiceImpl implements ImageMappingService {

	@Autowired
	private ImageMappingDAO imageMappingDAO;

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.userServiceImpl.ImageMappingService#getNewSequenceNo()
	 */
	@Override
	public int getNewSequenceNo(int userId) {
		return imageMappingDAO.getNewSequenceNo(userId);
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.userServiceImpl.ImageMappingService#getById(int)
	 */
	@Override
	public ImageMapping getById(int id) {
		return imageMappingDAO.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.userServiceImpl.ImageMappingService#saveRecord(int, int, java.lang.String)
	 */
	@Override
	public void saveRecord(int userId, int sequenceNo, String imagePath) {
		Users users = new Users();
		users.setUserId(userId);
		ImageMapping imageMapping = new ImageMapping(users, sequenceNo,
				imagePath);
		imageMappingDAO.saveImageMapping(imageMapping);
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.userServiceImpl.ImageMappingService#listRecord(int)
	 */
	@Override
	public List<ImageMapping> listRecord(int userId) {
		List<ImageMapping> imageMappings = null;
		imageMappings = imageMappingDAO.getImageMappingListFrUserId(userId);
		return imageMappings;
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.userServiceImpl.ImageMappingService#listRecordsFromTo(int, int, int)
	 */
	@Override
	public List<ImageMapping> listRecordsFromTo(int userId, int fromSeqNo,
			int toSeqNo) {
		List<ImageMapping> imageMappings = null;
		imageMappings = imageMappingDAO.listRecordsFromTo(userId, fromSeqNo,
				toSeqNo);
		return imageMappings;
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.userServiceImpl.ImageMappingService#listRecordsAccToSeq(int, int)
	 */
	@Override
	public ImageMapping recordsAccToSeq(int userId, int seqNo) {
		ImageMapping imageMapping = null;
		imageMapping = imageMappingDAO.recordsAccToSeq(userId, seqNo);
		return imageMapping;
	}

	/* (non-Javadoc)
	 * @see com.w3villa.main.authentication.userServiceImpl.ImageMappingService#updateRecord(com.w3villa.main.authentication.domain.ImageMapping)
	 */
	@Override
	public void updateRecord(ImageMapping imageMapping) {
		imageMappingDAO.updateImageMapping(imageMapping);
	}

	@Override
	public void deleteRecordsByUserId(int userId) {
		imageMappingDAO.deleteRecordsByUserId(userId);

	}

	@Override
	public void deleteRecordsById(int id) {
		imageMappingDAO.deleteRecordsById(id);

	}

}
