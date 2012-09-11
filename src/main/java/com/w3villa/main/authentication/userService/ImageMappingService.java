package com.w3villa.main.authentication.userService;

import java.util.List;

import com.w3villa.main.authentication.domain.ImageMapping;

public interface ImageMappingService {

	// public abstract int getNewSequenceNo(int userId);

	public abstract ImageMapping getById(int id);

	public abstract void saveRecord(int userId, String imagePath);

	public abstract List<ImageMapping> listRecord(int userId);

	// public abstract List<ImageMapping> listRecordsFromTo(int userId,
	// int fromSeqNo, int toSeqNo);

	// public abstract ImageMapping recordsAccToSeq(int userId, int seqNo);

	public abstract void updateRecord(ImageMapping imageMapping);

	void deleteRecordsByUserId(int userId);

	void deleteRecordsById(int id);

	// boolean updateSeqNo(int id, int seqNo);

}