package com.wwe.common.code;

public enum AddAlarmCode {
	
	IT01("업무"), //insert Task
	IU01("인원"),	 //insert user
	IU02("초대");
	
	//result.jsp를 사용해 띄울 안내문구
	private String alarmCode;

	//index로 이동시킬 경우
	AddAlarmCode(String alarmCode) {
		this.alarmCode = alarmCode;
	}
	
	//alarmCode getter
	public String alarmCode() {
		return alarmCode;
	}
	
	
}
