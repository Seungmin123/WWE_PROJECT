//package com.wwe.project.model.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Properties;
//
//import com.wwe.common.code.ErrorCode;
//import com.wwe.common.exception.DataAccessException;
//import com.wwe.common.jdbc.JDBCTemplate;
//import com.wwe.project.model.vo.Project;
//
//import oracle.jdbc.OracleConnection;
//import oracle.jdbc.pool.OracleDataSource;
//
//public class ProDao {
//
//	private static JDBCTemplate instance;
//	
//	public static JDBCTemplate getInstance() {
//		if(instance == null) {
//			instance = new JDBCTemplate();
//		}
//		
//		return instance;
//	}
//	
//	public Connection getConnection() {
//		String url = "jdbc:oracle:thin:@WweDB_high?TNS_ADMIN=C:/wallet/";
//		String user = "admin";
//		String password = "Weworkeasy93!";
//
//		Properties info = new Properties();
//		info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, user);
//		info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, password);
//		info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");
//
//		Connection conn = null;
//
//		try {
//			OracleDataSource ods = new OracleDataSource();
//			ods.setURL(url);
//			ods.setConnectionProperties(info);
//			conn = ods.getConnection();
//			conn.setAutoCommit(false);
//		} catch (SQLException throwables) {
//			throwables.printStackTrace();
//		}
//
//		return conn;
//	}
//	
//	//새 프로젝트 생성 (view 단에서 받아오기)
//	public int insertNewProject(Connection conn, Project project) {
//	      int res = 0;
//	      
//	      String sql = "insert into tb_project "
//	    		  	+ "(user_id,due_date,progress,leader_id) "
//	    		  	+ "values('p' || sc_project_idx.nextval, ?, ?, ?, ?)"; //시퀀스 생성 확인
//	     
//	      PreparedStatement pstm = null;
//	      
//	      try {
//	         pstm = conn.prepareStatement(sql);
//	         
//	         pstm.setString(1, project.getProjectId());
//	         pstm.setDate(2, project.getDueDate());
//	         pstm.setInt(3, project.getProgress());
//	         pstm.setString(4, project.getLeaderId());
//	         
//	         res = pstm.executeUpdate();
//	         
//	      } catch (SQLException e) {
//	         throw new DataAccessException(ErrorCode.IB01, e); 
//	
//	      }finally {
//	    	  instance.close(pstm);
//	      }
//	      return res;
//	   }
//
//	//최근 프로젝트 받아오기
//	public Project selectRecentProject(Connection conn, String userId){
//		Project project = null;
//		PreparedStatement pstm = null;
//		ResultSet rset = null;
//		
//		try{
//			String query = "select * from tb_project_master where user_id = ?";
//			
//			pstm = conn.prepareStatement(query);
//			pstm.setString(1, userId);
//			rset = pstm.executeQuery();
//			
//			if(rset.next()) {
//				project = new Project();
//				project.setUserId(rset.getString("user_id"));
//				project.setProjectId(rset.getString("grade"));
//				project.setWorkTime(rset.getDate("email"));
//			}
//		} catch (SQLException e) {
//			throw new DataAccessException(ErrorCode.SM01, e);
//		}finally {
//			instance.close(rset,pstm);
//		}
//		
//		return project;
//	}
//	
//	//초대된 프로젝트
//	public Project selectInvitedProject(Connection conn, String userId) {
//		Project project = new Project();
//		PreparedStatement pstm = null;
//		ResultSet rset = null;
//		
//		try{
//			String query = "select * from tb_project_master where user_id = ?";
//			
//			pstm = conn.prepareStatement(query);
//			pstm.setString(1, userId);
//			rset = pstm.executeQuery();
//			
//			if(rset.next()) {
//				
//				project.setUserId(rset.getString("user_id"));
//				project.setProjectId(rset.getString("grade"));
//				project.setWorkTime(rset.getDate("email"));
//			}
//		} catch (SQLException e) {
//			throw new DataAccessException(ErrorCode.SM01, e);
//		}finally {
//			instance.close(rset,pstm);
//		}
//		
//		return project;
//	}
//	
//	
//	
//	
//	
//	public void commit(Connection conn) {
//		try {
//			conn.commit();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void rollback(Connection conn) {
//		try {
//			conn.rollback();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void close(ResultSet rset) {
//		try {
//			if(rset != null && !rset.isClosed()) {
//				rset.close();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void close(Statement stmt) {
//		try {
//			if(stmt != null && !stmt.isClosed()) {
//				stmt.close();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void close(Connection conn) {
//		try {
//			if(conn != null && !conn.isClosed()) {
//				conn.close();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void close(ResultSet rset, Statement stmt) {
//		try {
//			if(rset != null && !rset.isClosed()) {
//				rset.close();
//			}
//			
//			if(stmt != null && !stmt.isClosed()) {
//				stmt.close();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void close(Statement stmt, Connection conn) {
//		try {
//			if(stmt != null && !stmt.isClosed()) {
//				stmt.close();
//			}
//			
//			if(conn != null && !conn.isClosed()) {
//				conn.close();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void close(ResultSet rset, Statement stmt, Connection conn) {
//		
//		try {
//			if(rset != null && !rset.isClosed()) {
//				rset.close();
//			}
//			
//			if(stmt != null && !stmt.isClosed()) {
//				stmt.close();
//			}
//			
//			if(conn != null && !conn.isClosed()) {
//				conn.close();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
