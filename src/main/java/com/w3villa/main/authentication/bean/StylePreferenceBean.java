package com.w3villa.main.authentication.bean;

import javax.validation.constraints.Size;

public class StylePreferenceBean {
	private String stylePreferenceId;
	@Size(min = 1, max = 200, message = "Style Preference Name size lies between 1 to 200 character only.")
	private String name;
	@Size(min = 1, max = 2000, message = "Style Preference description size lies between 1 to 2000 character only.")
	private String description;

	public String getStylePreferenceId() {
		return stylePreferenceId;
	}

	public void setStylePreferenceId(String stylePreferenceId) {
		this.stylePreferenceId = stylePreferenceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
