package com.w3villa.main.authentication.dao;

import java.util.List;

import com.w3villa.main.authentication.domain.ImageMapping;

public interface ImageMappingDAO {

	public abstract List<ImageMapping> getImageMappingListFrUserId(int userId);

	public abstract void saveImageMapping(ImageMapping imageMapping);

	public abstract void updateImageMapping(ImageMapping imageMapping);

	public abstract ImageMapping getById(int id);

	public abstract int getNewSequenceNo(int userId);

	public abstract List<ImageMapping> listRecordsFromTo(int userId,
			int fromSeqNo, int toSeqNo);

	public abstract ImageMapping recordsAccToSeq(int userId, int seqNo);

	void deleteRecordsByUserId(int userId);

	void deleteRecordsById(int id);

	boolean updateSeqNo(int id, int seqNo);

}