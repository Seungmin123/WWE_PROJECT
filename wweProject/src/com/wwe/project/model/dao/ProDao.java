package com.wwe.project.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wwe.common.code.ErrorCode;
import com.wwe.common.exception.DataAccessException;
import com.wwe.common.jdbc.JDBCTemplate;
import com.wwe.member.model.vo.Member;
import com.wwe.project.model.vo.Project;

public class ProDao {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	//새 프로젝트 생성해서 DB에 추가
	public int insertNewProject(Connection conn, Project project) {
	      int res = 0;
	      PreparedStatement pstm = null;
	      ResultSet rset = null;
	      
	      String sql = "insert into tb_project "
	    		  	+ "(user_id,due_date,progress,leader_id) "
	    		  	+ "values(?, ?, ?, ?)"; 
	      
	      try {
	         pstm = conn.prepareStatement(sql);
	         pstm.setString(1, project.getProjectId());
	         pstm.setDate(2, project.getDueDate());
	         pstm.setInt(3, project.getProgress());
	         pstm.setString(4, project.getLeaderId());
	         rset = pstm.executeQuery();
	         
	      } catch (SQLException e) {
	         throw new DataAccessException(ErrorCode.IB01, e); 
	
	      }finally {
	    	  jdt.close(rset,pstm);
	      }
	      
	      return res;
	   }
	
	//새 프로젝트의 참여자 - DB에서 아이디로 식별해 가져오고, 보여주는 건 이름
	public Member selectMember(Connection conn, String userId, String userName) {
		Member member = new Member();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try{
			String query = "select * from tb_user "
							+ "where user_id = ? and user_name = ?";
			
			//주소창처럼 => 아이디로 입력을 받고, 이 아이디로 검색해서 추가
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getUserID());
			pstm.setString(2, member.getUserName());
			
			rset = pstm.executeQuery();
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SM01, e);
		}finally {
			jdt.close(rset,pstm);
		}
		
		return member;
	}

	//최근 프로젝트 받아오기 (최근 작업시간 순으로)
	public List<Project> selectRecentProject(Connection conn, Date workTime, String userId){
		List<Project> res = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select * "
						+ "from tb_project_master p"
						+ "where p.work_time = (select max(work_time)"
												+ "from tb_user u "
												+ "where u.user_id = p.user_id) "
												+ "order by work_time desc";
		
		res = new ArrayList<Project>();
		
		try{
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				Project project = new Project();
				
				project = new Project();
				project.setUserId(rset.getString("user_id"));
				project.setProjectId(rset.getString("project_id"));
				project.setWorkTime(rset.getDate("work_time"));
				project.setDueDate(rset.getDate("due_date"));
				project.setProgress(rset.getInt("progress"));
				project.setLeaderId(rset.getString("leader_id"));
				
				res.add(project);
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SM01, e);
			
		}finally {
			jdt.close(rset,pstm);
		}
		
		return res;
	}
	
	//초대된 프로젝트 받아오기
	public Project selectInvitedProject(Connection conn, String userId, int isAllowed) {
		Project project = new Project();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try{
			String query = "select * from tb_user_issue "
							+ "where user_id = ? and is_allowed = 0";
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				project.setUserId(rset.getString("user_id"));
				project.setProjectId(rset.getString("project_id"));
				project.setDueDate(rset.getDate("due_date"));
				project.setProgress(rset.getInt("progress"));
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SM01, e);
		}finally {
			jdt.close(rset,pstm);
		}
		
		return project;
	}
	
	
	public void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(ResultSet rset, Statement stmt) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
			
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(Statement stmt, Connection conn) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
			
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(ResultSet rset, Statement stmt, Connection conn) {
		
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
			
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
			
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
