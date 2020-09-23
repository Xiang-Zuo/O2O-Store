package com.TomorrowLand.o2o.entity;

import java.util.Date;

public class HeadLine {
	private Long lineId;
	private String lineName;
	private String linkLink;
	private String lineLmg;
	private Integer priority;
	private Integer enableStatus;
	private Date createTiem;
	private Date lastEditTiem;
	
	public Long getLineId() {
		return lineId;
	}
	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getLinkLink() {
		return linkLink;
	}
	public void setLinkLink(String linkLink) {
		this.linkLink = linkLink;
	}
	public String getLineLmg() {
		return lineLmg;
	}
	public void setLineLmg(String lineLmg) {
		this.lineLmg = lineLmg;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
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
