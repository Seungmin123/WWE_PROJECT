package com.wwe.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wwe.common.jdbc.JDBCTemplate;
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
			
			System.out.println(member.getUserID());
			System.out.println(member.getUserPW());
			System.out.println("11111111111111111111111111111111111111111111");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdt.close(rset);
			jdt.close(pstm);
		}
		return member;
	}
}
