package com.wwe.leader.model.vo;

public class Leader {

	private String userId;
	private String authority;
	private String projectId;
	
	
	public Leader(String userId, String authority, String projectId) {
		super();
		this.userId = userId;
		this.authority = authority;
		this.projectId = projectId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	
}
