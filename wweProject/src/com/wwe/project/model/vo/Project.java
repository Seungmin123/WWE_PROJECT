package com.wwe.project.model.vo;

import java.sql.Date;
import java.util.List;

public class Project {
	private String projectId;
	private Date dueDate;
	private int progress;
	private String leaderId;
	private String userId;
	private String workTime;
	private String authority;
	private String isInvited; //초대여부
	private String title;
	private String deadline;
	private Date startDate;
	private Date endDate;
	

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


	public String getWorkTime() {
		return workTime;
	}


	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}


	public String getAuthority() {
		return authority;
	}


	public void setAuthority(String authority) {
		this.authority = authority;
	}


	public String getIsInvited() {
		return isInvited;
	}


	public void setIsInvited(String isInvited) {
		this.isInvited = isInvited;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDeadline() {
		return deadline;
	}


	public void setDeadLine(String deadline) {
		this.deadline = deadline;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", dueDate=" + dueDate + ", progress=" + progress + ", leaderId="
				+ leaderId + ", userId=" + userId + ", workTime=" + workTime + ", authority=" + authority
				+ ", isInvited=" + isInvited + ", title=" + title + ", deadline=" + deadline + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
	
}
