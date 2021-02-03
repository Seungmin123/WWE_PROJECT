package com.wwe.views.service;

import com.wwe.common.jdbc.JDBCTemplate;
import com.wwe.leader.model.service.LeaderService;

public class ViewsService {
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	// 해당 프로젝트의 인원을 받아온다.
	LeaderService leaderService = new LeaderService();
	
	
	
	
}
