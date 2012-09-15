package com.w3villa.main.authentication.domain;

// Generated 1 Sep, 2012 6:48:24 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * UserAlbumChoiceMpg generated by hbm2java
 */
@Entity
@Table(name = "user_album_choice_mpg", catalog = "w3villdb")
public class UserAlbumChoiceMpg implements java.io.Serializable {

	private Integer userAlbumChoiceMpgId;
	private AlbumChoice albumChoice;
	private Users users;
	private List<ImageAlbumChoiceMapping> imageAlbumChoiceMappings = new ArrayList<ImageAlbumChoiceMapping>();

	public UserAlbumChoiceMpg() {
	}

	public UserAlbumChoiceMpg(AlbumChoice albumChoice, Users users,
			List<ImageAlbumChoiceMapping> imageAlbumChoiceMappings) {
		this.albumChoice = albumChoice;
		this.users = users;
		this.imageAlbumChoiceMappings = imageAlbumChoiceMappings;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "USER_ALBUM_CHOICE_MPG_ID", unique = true, nullable = false)
	public Integer getUserAlbumChoiceMpgId() {
		return this.userAlbumChoiceMpgId;
	}

	public void setUserAlbumChoiceMpgId(Integer userAlbumChoiceMpgId) {
		this.userAlbumChoiceMpgId = userAlbumChoiceMpgId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ALBUM_CHOICE_ID", nullable = false)
	public AlbumChoice getAlbumChoice() {
		return this.albumChoice;
	}

	public void setAlbumChoice(AlbumChoice albumChoice) {
		this.albumChoice = albumChoice;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAlbumChoiceMpg", orphanRemoval = true)
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.MERGE })
	public List<ImageAlbumChoiceMapping> getImageAlbumChoiceMappings() {
		return imageAlbumChoiceMappings;
	}

	public void setImageAlbumChoiceMappings(
			List<ImageAlbumChoiceMapping> imageAlbumChoiceMappings) {
		this.imageAlbumChoiceMappings = imageAlbumChoiceMappings;
	}

}
