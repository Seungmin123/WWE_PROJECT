package com.wwe.views.model.vo;

public class UserByTaskVo {
	/*
	 * private String userId; private String taskId; private String deadLine;
	 * private String startDate;
	 */
	private String title;
	private String start;
	private String end;
	private String color;
	
	public UserByTaskVo(String title, String start, String end,String color) {
		super();
		this.title = title;
		this.start = "20"+start.replace("/", "-");
		this.end = end;
		this.color = color;
	}

}
