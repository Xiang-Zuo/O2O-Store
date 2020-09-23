package com.TomorrowLand.o2o.entity;

import java.util.Date;

public class PersonInfo {
	private Long userId;
	private String name;
	private String profileImg;
	private String email;
	private String gender;
	private Integer enableStatus;
	private Integer userTyep;
	private Date createTiem;
	private Date lastEditTiem;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}
	public Integer getUserTyep() {
		return userTyep;
	}
	public void setUserTyep(Integer userTyep) {
		this.userTyep = userTyep;
	}
	public Date getCreateTiem() {
		return createTiem;
	}
	public void setCreateTiem(Date createTiem) {
		this.createTiem = createTiem;
	}
	public Date getLastEditTiem() {
		return lastEditTiem;
	}
	public void setLastEditTiem(Date lastEditTiem) {
		this.lastEditTiem = lastEditTiem;
	}
	
}
