package com.wwe.project.model.vo;

import java.sql.Date;

public class Project {
	private String projectId;
	private Date dueDate;
	private int progress;
	private String leaderId;
	private String userId;
	private Date workTime;
	private String authority;
	private int isAllowed;
	
	public Project() {
		// TODO Auto-generated constructor stub
	}
	
	public Project(String projectId, Date dueDate, int progress, String leaderId, String userId, Date workTime, String authority, int isAllowed) {
		super();
		this.projectId = projectId;
		this.dueDate = dueDate;
		this.progress = progress;
		this.leaderId = leaderId;
		this.userId = userId;
		this.workTime = workTime;
		this.authority = authority;
		this.isAllowed = isAllowed;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public int getIsAllowed() {
		return isAllowed;
	}

	public void setIsAllowed(int isAllowed) {
		this.isAllowed = isAllowed;
	}
	
}
