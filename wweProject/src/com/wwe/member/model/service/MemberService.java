package com.wwe.member.model.service;

import java.sql.Connection;

import com.wwe.common.jdbc.JDBCTemplate;
import com.wwe.member.model.dao.MemberDao;
import com.wwe.member.model.vo.Member;

public class MemberService {

	public MemberService() {}

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	MemberDao memberDao = new MemberDao();
	
	public Member memberAuthenticate(String userID, String userPW) {
		
		Connection conn = jdt.getConnection();
		
		Member res = memberDao.memberAuthenticate(conn, userID, userPW);
		
		jdt.close(conn);
		
		return res;
	}
	
	public Member getMemberProject(String userID) {
		
		Connection conn = jdt.getConnection();
		
		Member res = memberDao.getMemberProject(conn, userID);
		
		jdt.close(conn);
		
		return res;
	}
	
	public int insertMember(Member member) {
		
		Connection conn = jdt.getConnection();
		int res = 0;
		
		try {
			
			res = memberDao.insertMember(conn,member);
			jdt.commit(conn);
			
		} finally {
			jdt.close(conn);
		}
		
		return res;
		
		
		
	}
	
	public Member findMemberID(String userEmail) {
		
		Connection conn = jdt.getConnection();
		Member res = memberDao.findMemberID(conn, userEmail);
		
		jdt.close(conn);
		
		return res;
	}
	
	public Member findMemberPW(String userID, String userEmail) {
		
		Connection conn = jdt.getConnection();
		
		Member res = memberDao.findMemberPW(conn, userID, userEmail);
		
		jdt.close(conn);
		
		return res;
	}
	
	public void mailSender(String user, String title, String content) {
		
		Connection conn = jdt.getConnection();
		
		MemberDao memberDao = new MemberDao();
		try {
			memberDao.mailSender(conn, user, title, content);
			jdt.commit(conn);
		}finally {
			jdt.close(conn);
		}
	}
	
	public int modifyMember(Member member) {
		
		Connection conn = jdt.getConnection();
		int res = 0;
		
		try {
			
			res = memberDao.modifyMember(conn,member);
			jdt.commit(conn);
			
		} finally {
			jdt.close(conn);
		}
		
		return res;
		
		
		
	}	
	
	
	
}
