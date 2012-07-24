package com.w3villa.main.authentication.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "contact_us", catalog = "w3villdb")
public class ContactUs {

	private Integer contactUsId;
	private String name;
	private String email;
	private String comments;
	private Date createdDt;
	private String contactNo;

	public ContactUs() {
	}

	public ContactUs(String name, String email, String comments,
			Date createdDt, String contactNo) {
		super();
		this.name = name;
		this.email = email;
		this.comments = comments;
		this.createdDt = createdDt;
		this.contactNo = contactNo;
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CONTACT_US_ID", unique = true, nullable = false)
	public Integer getContactUsId() {
		return contactUsId;
	}

	public void setContactUsId(Integer contactUsId) {
		this.contactUsId = contactUsId;
	}

	@Column(name = "NAME", nullable = false, length = 45)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "EMAIL", nullable = false, length = 45)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "COMMENTS", nullable = false, length = 2000)
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DT", length = 19)
	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	@Column(name = "CONTACT_NO", length = 15)
	public String getContactNo() {
		return this.contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

}
