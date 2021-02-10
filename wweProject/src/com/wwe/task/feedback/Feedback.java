package com.wwe.task.feedback;

public class Feedback {
	
	private String taskId;
	private int privateComment;
	private String feedbackComment;
	private String userId;
	
	public String getTaskId() {
		return taskId;
	}
	
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	public int getPrivateComment() {
		return privateComment;
	}
	
	public void setPrivateComment(int privateComment) {
		this.privateComment = privateComment;
	}
	
	public String getFeedbackComment() {
		return feedbackComment;
	}
	
	public void setFeedbackComment(String feedbackComment) {
		this.feedbackComment = feedbackComment;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
