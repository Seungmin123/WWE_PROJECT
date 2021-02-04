package com.wwe.task.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			String query = "insert into tb_task(t_idx,task_id,task_content,dead_line,user_id,project_id,user_id) "
					+ "values(sc_t_idx.nextval,?,?,?,?,?)";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, task.getTaskId());
			pstm.setString(2, task.getTaskContent());
			pstm.setString(3, task.getDeadLine());
			pstm.setString(4, task.getUserId());
			pstm.setString(5, task.getProjectId());
			pstm.setString(6, task.getUserId());
			
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
					+ "values(?,?)";
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
	public ArrayList<Task> selectMyList(Connection conn, String userId){
		
		ArrayList<Task> myList = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try{
			String query = "SELECT * FROM TB_TASK WHERE USER_ID = ?";
			
			//3. 쿼리문 실행용 객체를 생성
			pstm = conn.prepareStatement(query);
			//4. PreparedStatement의 쿼리문을 완성
			pstm.setString(1, userId);
			//5. 쿼리문 실행하고 결과(resultSet)를 받음
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Task task = new Task();
				task.setUserId(userId);
				task.setTaskId(rset.getString("task_id"));
				task.setTaskPriority(rset.getString("task_priority"));
				task.setTaskState(rset.getString("task_state"));
				
				myList.add(task);
			}

		} catch (SQLException e) {;
			throw new DataAccessException(ErrorCode.TK01,e);
		}finally {
			jdt.close(rset,pstm);
		}
		
		return myList;
	}

	
}
