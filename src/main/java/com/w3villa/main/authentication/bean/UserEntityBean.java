package com.w3villa.main.authentication.bean;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class UserEntityBean {
	@Size(min = 1, max = 10, message = "User Id size lies between 1 to 10 character only.")
	@Pattern(regexp = "^[0-9]+$", message = "only integer value allowed in user id.")
	private String id;
	@Pattern(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Please enter email in the format xxx@xx.com")
	private String emailId;
	@Size(min = 1, max = 20, message = "First Name size lies between 1 to 20 character only.")
	private String firstName;
	@Size(min = 1, max = 20, message = "Last Name size lies between 1 to 20 character only.")
	private String lastName;
	@Size(min = 1, max = 20, message = "Password size lies between 1 to 20 character only.")
	private String password;
	@Size(min = 1, max = 20, message = "Password size lies between 1 to 20 character only.")
	private String ReTypePassword;
	private String isActive;
	@Size(min = 1, max = 20, message = "Address Line 1 size lies between 1 to 20 character only.")
	private String addressLine1;
	private String addressLine2;
	@Size(min = 1, max = 20, message = "City size lies between 1 to 20 character only.")
	private String city;
	private String state;
	private String country;
	@Pattern(regexp = "^[0-9]+$", message = "Only integer value allowed in Contact No.")
	@Size(min = 1, max = 20, message = "Contact No. size lies between 1 to 20 character only.")
	private String contactNo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getReTypePassword() {
		return ReTypePassword;
	}
	public void setReTypePassword(String reTypePassword) {
		ReTypePassword = reTypePassword;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	
}