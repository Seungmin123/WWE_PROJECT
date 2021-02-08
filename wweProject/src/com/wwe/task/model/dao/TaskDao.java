package com.wwe.task.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wwe.common.code.ErrorCode;
import com.wwe.common.exception.DataAccessException;
import com.wwe.common.jdbc.JDBCTemplate;
import com.wwe.task.feedback.Feedback;
import com.wwe.task.model.vo.Task;

public class TaskDao {

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	//업무추가
	public int insertTask(Connection conn, Task task) {
		
		int res = 0;
		PreparedStatement pstm = null;
		
		try {
			String query = "insert into tb_task(t_idx,task_id,task_content,dead_line,user_id,project_id)"
					+ "values(sc_t_idx.nextval,?,?,?,?,?)";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, task.getTaskId());
			pstm.setString(2, task.getTaskContent());
			pstm.setString(3, task.getDeadLine());
			pstm.setString(4, task.getUserId());
			pstm.setString(5, task.getProjectId());
			
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.TK02, e);
		}finally {
			jdt.close(pstm);
		}

		return res;
	}
	
	//프로젝트 별 업무리스트 조회
	public ArrayList<Task> selectAllTaskList(Connection conn, String projectId){
		
		ArrayList<Task> taskList = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try{
			String query = "SELECT * FROM TB_TASK WHERE PROJECT_ID = ?";
			
			//3. 쿼리문 실행용 객체를 생성
			pstm = conn.prepareStatement(query);
			//4. PreparedStatement의 쿼리문을 완성
			pstm.setString(1, projectId);
			//5. 쿼리문 실행하고 결과(resultSet)를 받음
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Task task = new Task();
				task.setProjectId(projectId);
				task.setTaskId(rset.getString("TASK_ID"));
				task.setDeadLine(rset.getString("DEAD_LINE"));
				task.setTaskContent(rset.getString("TASK_CONTENT"));
				task.setTaskPriority(rset.getString("TASK_PRIORITY"));
				task.setTaskState(rset.getString("TASK_STATE"));
				task.setUserId(rset.getString("user_id"));
				
				taskList.add(task);
			}

		} catch (SQLException e) {;
			throw new DataAccessException(ErrorCode.TK01,e);
		}finally {
			jdt.close(rset,pstm);
		}
		
		return taskList;
	}
	
	//업무 별 상세내용 조회
	public ArrayList<Task> detailTask(Connection conn, String taskId){
		
		ArrayList<Task> detailList = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try{
			String query = "SELECT * FROM TB_TASK WHERE TASK_ID = ?";
			
			//3. 쿼리문 실행용 객체를 생성
			pstm = conn.prepareStatement(query);
			//4. PreparedStatement의 쿼리문을 완성
			pstm.setString(1, taskId);
			//5. 쿼리문 실행하고 결과(resultSet)를 받음
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Task task = new Task();
				task.setTaskId(taskId);
				task.setTaskContent(rset.getString("task_content"));
				task.setDeadLine(rset.getString("dead_line"));
				task.setTaskPriority(rset.getString("task_priority"));
				task.setTaskState(rset.getString("task_state"));
				task.setUserId(rset.getString("user_id"));
				
				detailList.add(task);
			}

		} catch (SQLException e) {;
			throw new DataAccessException(ErrorCode.TK01,e);
		}finally {
			jdt.close(rset,pstm);
		}
		
		return detailList;
	}
	
	public int insertFeedback(Connection conn, Feedback feedback) {
		
		int res = 0;
		PreparedStatement pstm = null;
		
		try {
			String query = "insert into tb_feedback(TASK_ID,FEEDBACK_COMMENT,PRIVATE_COMMENT) "
					+ "values(?,?,?)";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, feedback.getTaskId());
			pstm.setString(2, feedback.getFeedbackComment());
			pstm.setInt(3, feedback.getPrivateComment());
			
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.FK01, e);
		}finally {
			jdt.close(pstm);
		}

		return res;
	}
	
	//내 업무리스트 불러오기
	public ArrayList<Task> selectMyList(Connection conn, String userId,String projectId){
		
		ArrayList<Task> myList = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try{
			String query = "SELECT * FROM TB_TASK WHERE USER_ID = ? AND PROJECT_ID = ?";
			
			//3. 쿼리문 실행용 객체를 생성
			pstm = conn.prepareStatement(query);
			//4. PreparedStatement의 쿼리문을 완성
			pstm.setString(1, userId);
			pstm.setString(2, projectId);
			//5. 쿼리문 실행하고 결과(resultSet)를 받음
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Task task = new Task();
				task.setUserId(userId);
				task.setTaskId(rset.getString("task_id"));
				task.setTaskPriority(rset.getString("task_priority"));
				task.setTaskState(rset.getString("task_state"));
				task.setDeadLine(rset.getString("dead_line"));
				task.setStartDate(rset.getString("start_date"));
				task.setProjectId(rset.getString("project_id"));
				
				myList.add(task);
			}

		} catch (SQLException e) {;
			throw new DataAccessException(ErrorCode.TK01,e);
		}finally {
			jdt.close(rset,pstm);
		}
		
		return myList;
	}
	
	//프로젝트 멤버이름 불러오기
	public ArrayList<String> selectName(Connection conn, String projectId){
		
		ArrayList<String> memberList = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try{
			String query = "SELECT USER_ID FROM TB_PROJECT_USER WHERE PROJECT_ID = ?";

			pstm = conn.prepareStatement(query);
			pstm.setString(1, projectId);
			
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Task task = new Task();
				task.setUserId(rset.getString("user_id"));
				
				memberList.add(task.getUserId());
			}

		} catch (SQLException e) {;
			throw new DataAccessException(ErrorCode.TK01,e);
		}finally {
			jdt.close(rset,pstm);
		}
		
		return memberList;
		
	}
	
	public ArrayList<Task> selectTaskbyMem(Connection conn, String projectId, String userId, String leaderId){
		
		ArrayList<Task> taskByMember = new ArrayList<>();
			PreparedStatement pstm = null;
			ResultSet rset = null;
			
			try{
				String query = "SELECT * FROM TB_TASK WHERE PROJECT_ID = ? AND USER_ID != ? AND USER_ID = ?";
				
				//3. 쿼리문 실행용 객체를 생성
				pstm = conn.prepareStatement(query);
				//4. PreparedStatement의 쿼리문을 완성
				pstm.setString(1, projectId);
				pstm.setString(2, userId);
				pstm.setString(3, leaderId);
				//5. 쿼리문 실행하고 결과(resultSet)를 받음
				rset = pstm.executeQuery();
				
				while(rset.next()) {
					Task task = new Task();
					task.setProjectId(projectId);
					task.setTaskId(rset.getString("TASK_ID"));
					task.setDeadLine(rset.getString("DEAD_LINE"));
					task.setTaskContent(rset.getString("TASK_CONTENT"));
					task.setTaskPriority(rset.getString("TASK_PRIORITY"));
					task.setTaskState(rset.getString("TASK_STATE"));
					task.setUserId(rset.getString("user_id"));
					
					taskByMember.add(task);
				}

			} catch (SQLException e) {;
				throw new DataAccessException(ErrorCode.TK01,e);
			}finally {
				jdt.close(rset,pstm);
			}
			
			return taskByMember;
	}

	//알람기능 넣는 메서드
	public int insertTaskIssue(Connection conn, String userId,String projectId,String typeAlarm) {
		
		int res = 0;
		PreparedStatement pstm = null;
		
		try {
			String query = "INSERT INTO TB_USER_ISSUE(USER_ID, PROJECT_ID, TYPE_ALARM, WRITER) VALUES((SELECT USER_ID FROM TB_USER_ID WHERE USER_ID != ?), ?, ?, ?)";

			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			pstm.setString(2, projectId);
			pstm.setString(3, typeAlarm);
			pstm.setString(4, userId);
			
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.TK02, e);
		}finally {
			jdt.close(pstm);
		}

		return res;
		
	}
	
	public int deleteTask(Connection conn,String projectId) {
		
		int res = 0;
		PreparedStatement pstm = null;
		
		try {
			String query = "DELETE FROM TB_TASK WHERE PROJECT_ID = ? AND DEAD_LINE < SYSDATE ";
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, projectId);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.DT01,e);
		}finally {
			jdt.close(pstm);
		}
		return res;
	}
	
	//업무상태 변경 메서드
	public int updateState(Connection conn, String taskState) {
		
		int res = 0;
		PreparedStatement pstm = null;
		
		try {
			String query = "DELETE FROM TB_TASK WHERE PROJECT_ID = ? AND DEAD_LINE < SYSDATE ";
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, taskState);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.DT01,e);
		}finally {
			jdt.close(pstm);
		}
		return res;
	}
	
	
}
