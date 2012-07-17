package com.w3villa.main.authentication.bean;


public class AmazonStructure {
	private String fullPath;
	private String imageLocation;
	private String name;
	private String userId;
	private int no_of_File_folder_inside = 0;

	public int getNo_of_File_folder_inside() {
		return no_of_File_folder_inside;
	}

	public void setNo_of_File_folder_inside(int no_of_File_folder_inside) {
		this.no_of_File_folder_inside = no_of_File_folder_inside;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AmazonStructure other = (AmazonStructure) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AmazonStructure [fullPath=" + fullPath + ", imageLocation="
				+ imageLocation + ", name=" + name + ", userId=" + userId + "]";
	}

}
