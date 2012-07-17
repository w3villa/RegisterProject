package com.w3villa.main.authentication.userServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w3villa.main.authentication.dao.StylePreferenceDAO;
import com.w3villa.main.authentication.domain.StylePreference;
import com.w3villa.main.authentication.userService.StylePreferenceService;

@Service
public class StylePreferenceServiceImpl implements StylePreferenceService {

	@Autowired
	private StylePreferenceDAO stylePreferenceDao;

	public List<StylePreference> getStylePreferenceList() {
		return stylePreferenceDao.getStylePreferenceList();
	}

}
