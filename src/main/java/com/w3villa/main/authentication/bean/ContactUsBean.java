package com.w3villa.main.authentication.bean;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class ContactUsBean {
	private String contactUsId;
	@Size(min = 1, max = 45, message = "Name size lies between 1 to 45 character only.")
	private String name;
	@Size(min = 1, max = 45, message = "Email Id size lies between 1 to 45 character only.")
	@Pattern(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Please enter email in the format xxx@xx.com")
	private String email;
	@Size(min = 1, max = 2000, message = "Comments size lies between 1 to 2000 character only.")
	private String comments;
	private String createdDt;
	@Size(min = 1, max = 20, message = "Contact No. size lies between 1 to 20 character only.")
	private String contactNo;

	public String getContactUsId() {
		return contactUsId;
	}

	public void setContactUsId(String contactUsId) {
		this.contactUsId = contactUsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(String createdDt) {
		this.createdDt = createdDt;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

}
