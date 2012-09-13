package com.w3villa.main.authentication.dao;

import java.io.Serializable;
import java.util.List;

import com.w3villa.main.authentication.domain.ImageAlbumChoiceMapping;

public interface ImageAlbumChoiceMappingDAO {

	public abstract List<ImageAlbumChoiceMapping> getImageAlbumChoiceMapping(
			int userAlbumChoiceMpgId);

	public abstract Serializable saveImageAlbumChoiceMapping(
			ImageAlbumChoiceMapping imageAlbumChoiceMapping);

	public abstract boolean updateImageAlbumChoiceMapping(
			ImageAlbumChoiceMapping imageAlbumChoiceMapping);

	public abstract ImageAlbumChoiceMapping getById(int id);

	public abstract int getNewSequenceNo(int userAlbumChoiceMpgId);

	public abstract List<ImageAlbumChoiceMapping> listRecordsFromTo(
			int userAlbumChoiceMpgId, int fromSeqNo, int toSeqNo);

	public abstract ImageAlbumChoiceMapping recordsAccToSeq(
			int userAlbumChoiceMpgId, int seqNo);

	public abstract void deleteRecordsByUserAlbumChoiceMpgId(
			int userAlbumChoiceMpgId);

	public abstract void deleteRecordsById(int imageAlbumChoiceMappingId);

}