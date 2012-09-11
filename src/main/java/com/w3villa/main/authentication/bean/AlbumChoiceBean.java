package com.w3villa.main.authentication.bean;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AlbumChoiceBean {
	private String albumChoiceId;
	@Size(min = 1, max = 200, message = "Album Choice Name size lies between 1 to 200 character only.")
	private String name;
	@Size(min = 1, max = 2000, message = "Album CHoice description size lies between 1 to 2000 character only.")
	private String description;

	@Size(min = 1, max = 3, message = "No. of Pages size lies between 1 to 999 digits only.")
	@Pattern(regexp = "^[0-9]+$", message = "Only integer value allowed in No. of Pages.")
	private String noOfPages;

	public String getAlbumChoiceId() {
		return albumChoiceId;
	}

	public void setAlbumChoiceId(String albumChoiceId) {
		this.albumChoiceId = albumChoiceId;
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

	public String getNoOfPages() {
		return noOfPages;
	}

	public void setNoOfPages(String noOfPages) {
		this.noOfPages = noOfPages;
	}

}
