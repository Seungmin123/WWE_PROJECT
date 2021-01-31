package com.wwe.project;

import java.sql.Date;

public class Project {

	private String projectId;
	private Date dueDate;
	private int progress;
	private String leaderId;
	
	public Project() {
		// TODO Auto-generated constructor stub
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
	
	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", dueDate=" + dueDate + ", progress=" + progress + ", leaderId="
				+ leaderId + "]";
	}
}
