package com.wwe.task.model.vo;

public class Task {
	
	private String taskId;
	private String taskContent;
	private String taskPriority;
	private String deadLine;
	private String taskState;
	private String userId;
	
	public Task(String taskId, String taskContent, String taskPriority, String deadLine, String taskState,
			String userId) {
		super();
		this.taskId = taskId;
		this.taskContent = taskContent;
		this.taskPriority = taskPriority;
		this.deadLine = deadLine;
		this.taskState = taskState;
		this.userId = userId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	public String getTaskPriority() {
		return taskPriority;
	}

	public void setTaskPriority(String taskPriority) {
		this.taskPriority = taskPriority;
	}

	public String getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskContent=" + taskContent + ", taskPriority=" + taskPriority
				+ ", deadLine=" + deadLine + ", taskState=" + taskState + ", userId=" + userId + "]";
	}
	
}
