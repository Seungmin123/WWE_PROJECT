package com.wwe.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wwe.common.jdbc.JDBCTemplate;
import com.wwe.member.mail.MailSender;
import com.wwe.member.model.vo.Member;

public class MemberDao {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public Member memberAuthenticate(Connection conn, String userID, String userPW) {
		
		Member member = null;
		ResultSet rset = null;
		PreparedStatement pstm = null;
		
		
		
		try {
			
			String query = null;
			conn = jdt.getConnection();
			
			query = "select * from tb_user where user_id = ? and user_pw = ?";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, userID);
			pstm.setString(2, userPW);
			
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				
				member = new Member();
				member.setUserID(rset.getString("user_id"));
				member.setUserPW(rset.getString("user_pw"));
				member.setUserEmail(rset.getString("user_email"));
				member.setUserName(rset.getString("user_name"));	
				member.setUserAdd(rset.getString("user_add"));	
				member.setUserTell(rset.getString("user_tell"));
				member.setUserBirth(rset.getString("user_birth"));
				if(rset.getString("user_profile") != null) {
					member.setUserProfile(rset.getString("user_profile"));
				}
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdt.close(rset);
			jdt.close(pstm);
		}
		return member;
	}
	
	public int insertMember(Connection conn, Member member) {
		
		int res = 0;
		PreparedStatement pstm = null;
		
		System.out.println("dao" + member.getUserPW());
		try {
			String query = "insert into tb_user(user_id, user_pw, user_email, user_name, user_add, user_tell, user_birth) "
					+ "values(?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getUserID());
			pstm.setString(2, member.getUserPW());
			pstm.setString(3, member.getUserEmail());
			pstm.setString(4, member.getUserName());
			pstm.setString(5, member.getUserAdd());
			pstm.setString(6, member.getUserTell());
			pstm.setString(7, member.getUserBirth());
			res = pstm.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("회원가입 중 다오 문제 발생");
		}finally {
			jdt.close(pstm);
		}

		return res;
	}
	
	public Member findMemberID(Connection conn, String userEmail) {
		
		Member member = null;
		ResultSet rset = null;
		PreparedStatement pstm = null;
		
		
		
		try {
			
			String query = null;
			conn = jdt.getConnection();
			
			query = "select * from tb_user where user_email = ?";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, userEmail);
			
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				
				member = new Member();
				member.setUserID(rset.getString("user_id"));
				
				if(rset.getString("user_profile") != null) {
					member.setUserProfile(rset.getString("user_profile"));
				}
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdt.close(rset);
			jdt.close(pstm);
		}
		
		return member;
	}
	
	public Member findMemberPW(Connection conn, String userID, String userEmail) {
		
		Member member = null;
		ResultSet rset = null;
		PreparedStatement pstm = null;
		
		
		
		try {
			
			String query = null;
			conn = jdt.getConnection();
			
			query = "select * from tb_user where user_id = ? and user_email = ?";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, userID);
			pstm.setString(2, userEmail);
			
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				
				member = new Member();
				member.setUserID(rset.getString("user_id"));
				member.setUserPW(rset.getString("user_pw"));
				
				if(rset.getString("user_profile") != null) {
					member.setUserProfile(rset.getString("user_profile"));
				}
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdt.close(rset);
			jdt.close(pstm);
		}
		
		return member;
	}
	
	public void mailSender(Connection conn, String user, String title, String content) {
		MailSender mail = new MailSender();
		mail.GmailSet(user, title, content);
	}
}
