package com.wwe.member.model.vo;

import java.sql.Date;

public class Alarm {
	
	private String userID;
	private String typeOfAlarm;
	private String addDate;
	private String writer;
	
	



	public Alarm() {
		super();
	}


	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getTypeOfAlarm() {
		return typeOfAlarm;
	}


	public void setTypeOfAlarm(String typeOfAlarm) {
		this.typeOfAlarm = typeOfAlarm;
	}


	public String getAddDate() {
		return addDate;
	}


	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	
	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
	
}
