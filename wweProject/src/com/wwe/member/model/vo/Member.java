package com.wwe.member.model.vo;

public class Member {
	private String userId;
	private String userPW;
	
	public Member() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPW() {
		return userPW;
	}

	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userPW=" + userPW + "]";
	}
	
	
}
