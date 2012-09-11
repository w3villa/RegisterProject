package com.w3villa.main.authentication.userServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w3villa.main.authentication.bean.AlbumChoiceBean;
import com.w3villa.main.authentication.dao.AlbumChoiceDAO;
import com.w3villa.main.authentication.domain.AlbumChoice;
import com.w3villa.main.authentication.userService.AlbumChoiceService;

@Service
public class AlbumChoiceServiceImpl implements AlbumChoiceService {
	@Autowired
	private AlbumChoiceDAO albumChoiceDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.w3villa.main.authentication.userServiceImpl.AlbumChoiceService#
	 * getAlbumChoiceList()
	 */
	@Override
	public List<AlbumChoice> getAlbumChoiceList() {
		return albumChoiceDAO.getAlbumChoiceList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.w3villa.main.authentication.userServiceImpl.AlbumChoiceService#
	 * getAllAlbumChoice()
	 */
	@Override
	public List<AlbumChoiceBean> getAllAlbumChoice() {
		List<AlbumChoiceBean> albumChoiceBeanList = null;
		List<AlbumChoice> albumChoiceList = albumChoiceDAO.getAllAlbumChoice();
		if (!albumChoiceList.isEmpty())
			albumChoiceBeanList = new ArrayList<AlbumChoiceBean>();
		for (AlbumChoice albumChoice : albumChoiceList) {
			albumChoiceBeanList.add(getAlbumChoiceBean(albumChoice));
		}
		return albumChoiceBeanList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.w3villa.main.authentication.userServiceImpl.AlbumChoiceService#
	 * saveAlbumChoice(com.w3villa.main.authentication.bean.AlbumChoiceBean)
	 */
	@Override
	public void saveAlbumChoice(AlbumChoiceBean albumChoiceBean) {
		AlbumChoice albumChoice = null;
		albumChoice = getAlbumChoice(albumChoiceBean);
		albumChoiceDAO.saveAlbumChoice(albumChoice);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.w3villa.main.authentication.userServiceImpl.AlbumChoiceService#update
	 * (com.w3villa.main.authentication.bean.AlbumChoiceBean)
	 */
	@Override
	public void update(AlbumChoiceBean albumChoiceBean) {
		AlbumChoice albumChoice = null;
		albumChoice = getAlbumChoice(albumChoiceBean);
		System.out.println(albumChoice);
		albumChoiceDAO.update(albumChoice);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.w3villa.main.authentication.userServiceImpl.AlbumChoiceService#delete
	 * (java.lang.String)
	 */
	@Override
	public void delete(String id) {
		albumChoiceDAO.delete(Integer.parseInt(id));
	}

	private AlbumChoiceBean getAlbumChoiceBean(AlbumChoice albumChoice) {
		AlbumChoiceBean albumChoiceBean = new AlbumChoiceBean();
		BeanUtils.copyProperties(albumChoice, albumChoiceBean, new String[] {
				"albumChoiceId", "noOfPages" });
		if (albumChoice.getAlbumChoiceId() != null)
			albumChoiceBean.setAlbumChoiceId(albumChoice.getAlbumChoiceId()
					+ "");
		albumChoiceBean.setNoOfPages(albumChoice.getNoOfPages() + "");
		return albumChoiceBean;
	}

	private AlbumChoice getAlbumChoice(AlbumChoiceBean albumChoiceBean) {
		AlbumChoice albumChoice = null;
		if (albumChoiceBean.getAlbumChoiceId() != null
				&& !albumChoiceBean.getAlbumChoiceId().equals(""))
			albumChoice = albumChoiceDAO.getById(Integer
					.parseInt(albumChoiceBean.getAlbumChoiceId()));
		else {
			albumChoice = new AlbumChoice();
		}
		BeanUtils.copyProperties(albumChoiceBean, albumChoice, new String[] {
				"albumChoiceId", "noOfPages" });
		if (albumChoiceBean.getNoOfPages() != null
				&& !"".equals(albumChoiceBean.getNoOfPages()))
			albumChoice.setNoOfPages(Integer.parseInt(albumChoiceBean
					.getNoOfPages()));
		return albumChoice;
	}

	@Override
	public AlbumChoice get(String id) {
		return albumChoiceDAO.getById(Integer.parseInt(id));

	}
}
