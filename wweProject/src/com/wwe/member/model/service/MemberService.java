package com.wwe.member.model.service;

import java.sql.Connection;

import com.wwe.common.jdbc.JDBCTemplate;
import com.wwe.member.model.dao.MemberDao;
import com.wwe.member.model.vo.Member;

public class MemberService {

	public MemberService() {}

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public Member memberAuthenticate(String userID, String userPW) {
		
		Connection conn = jdt.getConnection();
		
		Member res = new MemberDao().memberAuthenticate(conn, userID, userPW);
		
		jdt.close(conn);
		
		return res;
	}
	
	
	
	
}
