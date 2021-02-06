package com.wwe.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wwe.common.jdbc.JDBCTemplate;
import com.wwe.member.mail.MailSender;
import com.wwe.member.model.vo.Alarm;
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
	
	public Member memberAuthenticateWithEmail(Connection conn, String userEmail) {
		
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
	
	public Member getMemberProject(Connection conn, String userID) {
		
		Member member = null;
		ResultSet rset = null;
		PreparedStatement pstm = null;
		ArrayList<String> projectArr = null;
		
		try {
			
			String query = null;
			conn = jdt.getConnection();
			projectArr = new ArrayList<String>();
			projectArr.add("first Step");
			
			query = "select * from tb_project_user where user_id = ?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userID);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				projectArr.add(rset.getString("project_id"));
			}
			member = new Member();
			member.setUserProject(projectArr);
			
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
			e.printStackTrace();
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
	
	public int modifyMember(Connection conn, Member member) {		
		int res = 0;
		PreparedStatement pstm = null;
		
		System.out.println("dao" + member.getUserPW());
		try {
//			String query = "insert into tb_user(user_id, user_pw, user_email, user_name, user_add, user_tell, user_birth) "
//					+ "values(?,?,?,?,?,?,?)";
			String query = "update tb_user set user_pw = ?, user_email = ?, user_name = ?, user_add = ?, user_tell = ?, user_birth = ?"
					+ "where user_id = ?";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, member.getUserPW());
			pstm.setString(2, member.getUserEmail());
			pstm.setString(3, member.getUserName());
			pstm.setString(4, member.getUserAdd());
			pstm.setString(5, member.getUserTell());
			pstm.setString(6, member.getUserBirth());
			pstm.setString(7, member.getUserID());
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("회원정보 수정 중 문제 발생");
			e.printStackTrace();
		}finally {
			jdt.close(pstm);
		}

		return res;
	}
	
	public List<Alarm> selectAlarm(Connection conn, String userID, String projectID){
		List<Alarm> alarmList = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select type_alarm, add_date, writer from tb_user_issue where user_id = ? and project_id = ? and is_checked = '0'";
		
		try {
			
			alarmList = new ArrayList<Alarm>();
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userID);
			pstm.setString(2, projectID);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				
				Alarm alarmMember = new Alarm();
				alarmMember.setTypeOfAlarm(rset.getString("type_alarm"));
				alarmMember.setAddDate(rset.getDate("add_date"));
				alarmMember.setWriter(rset.getString("writer"));
				alarmList.add(alarmMember);
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return alarmList;
	}
	
	
}
