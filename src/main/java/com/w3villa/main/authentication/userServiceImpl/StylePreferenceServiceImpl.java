package com.w3villa.main.authentication.userServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w3villa.main.authentication.bean.StylePreferenceBean;
import com.w3villa.main.authentication.dao.StylePreferenceDAO;
import com.w3villa.main.authentication.domain.StylePreference;
import com.w3villa.main.authentication.userService.StylePreferenceService;

@Service
public class StylePreferenceServiceImpl implements StylePreferenceService {

	@Autowired
	private StylePreferenceDAO stylePreferenceDao;

	@Override
	public List<StylePreference> getStylePreferenceList() {
		return stylePreferenceDao.getStylePreferenceList();
	}

	@Override
	public List<StylePreferenceBean> getAllStylePreference() {
		List<StylePreferenceBean> stylePreferenceBeanList = null;
		List<StylePreference> stylePreferenceList = stylePreferenceDao
				.getAllStylePreference();
		if (!stylePreferenceList.isEmpty())
			stylePreferenceBeanList = new ArrayList<StylePreferenceBean>();
		for (StylePreference stylePreference : stylePreferenceList) {
			stylePreferenceBeanList
					.add(getStylePreferenceBean(stylePreference));
		}
		return stylePreferenceBeanList;
	}

	@Override
	public void saveStylePreference(StylePreferenceBean stylePreferenceBean) {
		StylePreference stylePreference = null;
		stylePreference = getStylePreference(stylePreferenceBean);
		stylePreferenceDao.saveStylePreference(stylePreference);
	}

	@Override
	public void update(StylePreferenceBean stylePreferenceBean) {
		StylePreference stylePreference = null;
		stylePreference = getStylePreference(stylePreferenceBean);
		System.out.println(stylePreference);
		stylePreferenceDao.update(stylePreference);

	}

	@Override
	public void delete(String id) {
		stylePreferenceDao.delete(Integer.parseInt(id));
	}

	private StylePreferenceBean getStylePreferenceBean(
			StylePreference stylePreference) {
		StylePreferenceBean stylePreferenceBean = new StylePreferenceBean();
		BeanUtils.copyProperties(stylePreference, stylePreferenceBean,
				new String[] { "stylePreferenceId" });
		if (stylePreference.getStylePreferenceId() != null)
			stylePreferenceBean.setStylePreferenceId(stylePreference
					.getStylePreferenceId() + "");
		return stylePreferenceBean;
	}

	private StylePreference getStylePreference(
			StylePreferenceBean stylePreferenceBean) {
		StylePreference stylePreference = null;
		if (stylePreferenceBean.getStylePreferenceId() != null
				&& !stylePreferenceBean.getStylePreferenceId().equals(""))
			stylePreference = stylePreferenceDao.getById(Integer
					.parseInt(stylePreferenceBean.getStylePreferenceId()));
		else {
			stylePreference = new StylePreference();
		}
		BeanUtils.copyProperties(stylePreferenceBean, stylePreference,
				new String[] { "stylePreferenceId" });
		return stylePreference;
	}

}
