package com.w3villa.main.authentication.domain;

// Generated 9 Jul, 2012 2:47:13 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users", catalog = "w3villdb", uniqueConstraints = @UniqueConstraint(columnNames = "EMAIL_ID"))
public class Users implements java.io.Serializable {

	private Integer userId;
	private String userName;
	private String firstName;
	private String password;
	private String isActive;
	private String lastName;
	private String emailId;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;
	private String contactNo;
	private Date createdDt;
	private Date updateDt;
	private String zipCode;
	private Set<UserStylePreferncesMpg> userStylePreferncesMpgs = new HashSet<UserStylePreferncesMpg>(
			0);
	private Set<UserRoles> userRoleses = new HashSet<UserRoles>(0);
	private SortedSet<UserAlbumChoiceMpg> userAlbumChoiceMpgs = new TreeSet<UserAlbumChoiceMpg>();
	private Set<ImageMapping> imageMappings = new HashSet<ImageMapping>(0);

	public Users() {
	}

	public Users(String firstName, String userName, String password,
			String isActive, String lastName, String emailId) {
		this.firstName = firstName;
		this.password = password;
		this.isActive = isActive;
		this.lastName = lastName;
		this.emailId = emailId;
		this.userName = userName;
	}

	public Users(String firstName, String userName, String password,
			String isActive, String lastName, String emailId,
			String addressLine1, String addressLine2, String city,
			String state, String country, String contactNo, Date createdDt,
			Date updateDt, Set<UserStylePreferncesMpg> userStylePreferncesMpgs,
			Set<UserRoles> userRoleses,
			SortedSet<UserAlbumChoiceMpg> userAlbumChoiceMpgs) {
		this.firstName = firstName;
		this.password = password;
		this.isActive = isActive;
		this.lastName = lastName;
		this.emailId = emailId;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.contactNo = contactNo;
		this.createdDt = createdDt;
		this.updateDt = updateDt;
		this.userStylePreferncesMpgs = userStylePreferncesMpgs;
		this.userRoleses = userRoleses;
		this.userAlbumChoiceMpgs = userAlbumChoiceMpgs;
		this.userName = userName;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "USER_ID", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "FIRST_NAME", nullable = false, length = 100)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "USER_NAME", unique = true, nullable = false, length = 45)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "PASSWORD", nullable = false, length = 2000)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "IS_ACTIVE", nullable = false, length = 1)
	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Column(name = "LAST_NAME", nullable = false, length = 100)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "EMAIL_ID", unique = true, nullable = false, length = 200)
	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Column(name = "ADDRESS_LINE_1", length = 1000)
	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	@Column(name = "ADDRESS_LINE_2", length = 1000)
	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	@Column(name = "CITY", length = 100)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "STATE", length = 100)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "COUNTRY", length = 100)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "CONTACT_NO", length = 15)
	public String getContactNo() {
		return this.contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	@Column(name = "ZIP_CODE", length = 20)
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DT", length = 19)
	public Date getCreatedDt() {
		return this.createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DT", length = 19)
	public Date getUpdateDt() {
		return this.updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.MERGE })
	public Set<UserStylePreferncesMpg> getUserStylePreferncesMpgs() {
		return this.userStylePreferncesMpgs;
	}

	public void setUserStylePreferncesMpgs(
			Set<UserStylePreferncesMpg> userStylePreferncesMpgs) {
		this.userStylePreferncesMpgs = userStylePreferncesMpgs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.MERGE })
	public Set<UserRoles> getUserRoleses() {
		return this.userRoleses;
	}

	public void setUserRoleses(Set<UserRoles> userRoleses) {
		this.userRoleses = userRoleses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users", orphanRemoval = true)
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.MERGE })
	@Sort(type = SortType.NATURAL)
	public SortedSet<UserAlbumChoiceMpg> getUserAlbumChoiceMpgs() {
		return this.userAlbumChoiceMpgs;
	}

	public void setUserAlbumChoiceMpgs(
			SortedSet<UserAlbumChoiceMpg> userAlbumChoiceMpgs) {
		this.userAlbumChoiceMpgs = userAlbumChoiceMpgs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users", orphanRemoval = true)
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.MERGE })
	public Set<ImageMapping> getImageMappings() {
		return imageMappings;
	}

	public void setImageMappings(Set<ImageMapping> imageMappings) {
		this.imageMappings = imageMappings;
	}

}
