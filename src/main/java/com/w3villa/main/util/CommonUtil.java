package com.w3villa.main.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.w3villa.main.authentication.bean.ImageDetailBean;
import com.w3villa.main.authentication.domain.ImageMapping;
import com.w3villa.main.authentication.domain.Users;
import com.w3villa.main.authentication.userService.AlbumChoiceService;
import com.w3villa.main.authentication.userService.ImageMappingService;
import com.w3villa.main.authentication.userService.RepositoryService;

@Component
public class CommonUtil {

	@Autowired
	private ImageMappingService imageMappingService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private AlbumChoiceService albumChoiceService;

	public void getImages(Model model, HttpSession session, String imageType) {
		Users users = (Users) session.getAttribute("users");
		List<ImageDetailBean> imageDetailBeans = new ArrayList<ImageDetailBean>();
		ImageDetailBean imageDetailBean = null;
		List<ImageMapping> imageMappings = imageMappingService.listRecord(users
				.getUserId());
		String url = "";
		for (ImageMapping imageMapping : imageMappings) {
			imageDetailBean = new ImageDetailBean();
			imageDetailBean.setId(imageMapping.getImageMappingId());
			imageDetailBean.setSequenceNo(imageMapping.getSequenceNo());
			url = repositoryService.getUrl("igild/" + users.getUserId() + "/"
					+ imageType + "/" + imageMapping.getImagePath());
			imageDetailBean.setPath(url);
			imageDetailBeans.add(imageDetailBean);
		}
		if (imageDetailBeans.size() == 0)
			imageDetailBeans = null;
		model.addAttribute("imageDetailBeans", imageDetailBeans);
	}

	public void getAlbumChoices(Model model, HttpSession session) {
		Users users = (Users) session.getAttribute("users");
		model.addAttribute("albumChoiceList",
				albumChoiceService.getAlbumChoiceList());

	}

}
