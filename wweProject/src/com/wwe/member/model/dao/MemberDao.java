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
	
	//멤버 로그인을 위한 메소드
	public Member memberAuthenticate(Connection conn, String userID, String userPW) {
		
		Member member = null;
		ResultSet rset = null;
		PreparedStatement pstm = null;
		String query = null;
		
		try {
			
			//쿼리 작성
			query = "select * from tb_user where user_id = ? and user_pw = ?";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, userID);
			pstm.setString(2, userPW);
			
			rset = pstm.executeQuery();
			
			//select로 해당되는 값이 있다면 member 객체에 모든 값 담음
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
	
	//이메일을 통한 로그인을 위한 메소드 카카오톡 로그인 시 운용
	public Member memberAuthenticateWithEmail(Connection conn, String userEmail) {
		
		Member member = null;
		ResultSet rset = null;
		PreparedStatement pstm = null;
		String query =null;
		
		
		try {
			
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
	
	//user가 가입한 프로젝트를 받아오기 위한 메소드
	public Member getMemberProject(Connection conn, String userID) {
		
		Member member = null;
		ResultSet rset = null;
		PreparedStatement pstm = null;
		ArrayList<String> projectArr = null;
		String query = null;
		
		try {
			
			projectArr = new ArrayList<String>();
			//가입한 프로젝트가 없을 경우를 대비해 초기 값 설정
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
	
	//회원가입을 위한 메소드
	public int insertMember(Connection conn, Member member) {
		
		int res = 0;
		PreparedStatement pstm = null;
		
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
			
			if(res > 0) {
				jdt.commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("회원가입 중 다오 문제 발생");
			e.printStackTrace();
			jdt.rollback(conn);
		}finally {
			jdt.close(pstm);
		}

		return res;
	}
	
	//ID찾기 메소드
	public Member findMemberID(Connection conn, String userEmail) {
		
		Member member = null;
		ResultSet rset = null;
		PreparedStatement pstm = null;
		String query = null;
		
		try {
			
			query = "select * from tb_user where user_email = ?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userEmail);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				
				member = new Member();
				member.setUserID(rset.getString("user_id"));
				
				//user_profile null 이면 제거
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
	
	//PW찾기 메소드
	public Member findMemberPW(Connection conn, String userID, String userEmail) {
		
		Member member = null;
		ResultSet rset = null;
		PreparedStatement pstm = null;
		String query = null;
		
		try {
			
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
	
	//메일전송을 위한 메소드
	public void mailSender(Connection conn, String user, String title, String content) {
		MailSender mail = new MailSender();
		//user 이메일, 이메일 제목, 이메일 내용이 들어감.
		mail.GmailSet(user, title, content);
	}
	
	//회원정보 수정 메소드
	public int modifyMember(Connection conn, Member member) {		
		int res = 0;
		String query = null;
		PreparedStatement pstm = null;
		
		try {
			
			query = "update tb_user set user_pw = ?, user_email = ?, user_name = ?, user_add = ?, user_tell = ?, user_birth = ?"
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
			
			if(res>0) {
				jdt.commit(conn);
			}
		} catch (SQLException e) {
			System.out.println("회원정보 수정 중 문제 발생");
			e.printStackTrace();
			jdt.rollback(conn);
		}finally {
			jdt.close(pstm);
		}

		return res;
	}
	
	//alarm history 출력을 위한 메소드
	public List<Alarm> selectAlarm(Connection conn, String userID, String projectID){
		
		List<Alarm> alarmList = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = null;
		
		try {
			
			alarmList = new ArrayList<Alarm>();
			query = "select type_alarm, add_date, writer from tb_user_issue where user_id = ? and project_id = ? and is_checked = '0' order by add_date desc";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userID);
			pstm.setString(2, projectID);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				
				Alarm alarmMember = new Alarm();
				alarmMember.setTypeOfAlarm(rset.getString("type_alarm"));
				alarmMember.setAddDate(rset.getString("add_date"));
				alarmMember.setWriter(rset.getString("writer"));
				alarmList.add(alarmMember);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			jdt.close(rset);
			jdt.close(pstm);
		}
		
		return alarmList;
	}
	
	//업무추가, 팀장업무, 파일클라우드를 위한 tb_user_issue 등록 메소드
	public int addAlarm(Connection conn, String userID, String projectID, String typeAlarm){
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = null;
		List<Member> memberList = null;
		int res = 0;
		
		try {
			
			memberList = new ArrayList<Member>();
			query = "select user_id from tb_project_user where user_id != ?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userID);
			rset = pstm.executeQuery();
			
			//현 프로젝트에 가입한 인원 중 글 작성자를 제외한 모든 사람을 memberList에 저장
			while(rset.next()) {
				Member member = new Member();
				member.setUserID(rset.getString("user_id"));
				memberList.add(member);
			}
			
			//위에서 받아온 사람들에게 알림을 넣어줌
			for(int i = 0; i < memberList.size(); i++) {
				query = "insert into tb_user_issue(user_id, project_id, type_alarm, writer) values(?,?,?,?)";
				pstm = conn.prepareStatement(query);
				pstm.setString(1, memberList.get(i).getUserID());
				pstm.setString(2, projectID);
				pstm.setString(3, typeAlarm);
				pstm.setString(4, userID);
				
				res += pstm.executeUpdate();
			}
			
			if(res > 0) {
				jdt.commit(conn);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			jdt.rollback(conn);
		}finally {
			jdt.close(rset);
			jdt.close(pstm);
		}
		
		return res;
	}
	
}
