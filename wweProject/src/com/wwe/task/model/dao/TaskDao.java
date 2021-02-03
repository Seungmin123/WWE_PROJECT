package com.wwe.task.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.wwe.common.code.ErrorCode;
import com.wwe.common.exception.DataAccessException;
import com.wwe.common.jdbc.JDBCTemplate;
import com.wwe.task.model.vo.Task;

public class TaskDao {

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public int insertTask(Connection conn, Task task) {
		
		int res = 0;
		PreparedStatement pstm = null;
		
		try {
			String query = "insert into tb_task(t_idx,task_id,task_content,dead_line,user_id,project_id) "
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
	
	public List<String> selectAllTaskList(Connection conn, String projectId){
		
		return null;
		
	}

	
}
