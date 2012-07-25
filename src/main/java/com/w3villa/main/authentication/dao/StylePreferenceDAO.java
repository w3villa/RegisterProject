package com.w3villa.main.authentication.dao;

import java.util.List;

import com.w3villa.main.authentication.domain.StylePreference;

public interface StylePreferenceDAO {
	List<StylePreference> getStylePreferenceList();

	StylePreference getById(int id);

	void saveStylePreference(StylePreference stylePreference);

	List<StylePreference> getAllStylePreference();

	void update(StylePreference stylePreference);

	void delete(int id);
}
