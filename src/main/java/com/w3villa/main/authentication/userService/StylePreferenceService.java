package com.w3villa.main.authentication.userService;

import java.util.List;

import com.w3villa.main.authentication.bean.StylePreferenceBean;
import com.w3villa.main.authentication.domain.StylePreference;

public interface StylePreferenceService {
	List<StylePreference> getStylePreferenceList();

	List<StylePreferenceBean> getAllStylePreference();

	void saveStylePreference(StylePreferenceBean stylePreferenceBean)
			throws Exception;

	void update(StylePreferenceBean stylePreferenceBean) throws Exception;

	void delete(String id) throws Exception;
}
