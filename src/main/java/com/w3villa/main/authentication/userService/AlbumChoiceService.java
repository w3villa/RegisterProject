package com.w3villa.main.authentication.userService;

import java.util.List;

import com.w3villa.main.authentication.bean.AlbumChoiceBean;
import com.w3villa.main.authentication.domain.AlbumChoice;

public interface AlbumChoiceService {

	public abstract List<AlbumChoice> getAlbumChoiceList();

	public abstract List<AlbumChoiceBean> getAllAlbumChoice();

	public abstract void saveAlbumChoice(AlbumChoiceBean albumChoiceBean);

	public abstract void update(AlbumChoiceBean albumChoiceBean);

	public abstract void delete(String id);

}