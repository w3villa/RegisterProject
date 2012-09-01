package com.w3villa.main.authentication.dao;

import java.util.List;

import com.w3villa.main.authentication.domain.AlbumChoice;

public interface AlbumChoiceDAO {

	public abstract List<AlbumChoice> getAlbumChoiceList();

	public abstract AlbumChoice getById(int id);

	public abstract void saveAlbumChoice(AlbumChoice albumChoice);

	public abstract List<AlbumChoice> getAllAlbumChoice();

	public abstract void update(AlbumChoice albumChoice);

	public abstract void delete(int id);

}