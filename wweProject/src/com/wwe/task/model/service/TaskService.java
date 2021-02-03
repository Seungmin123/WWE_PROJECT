package com.wwe.task.model.service;

import java.sql.Connection;
import java.util.List;

import com.wwe.common.jdbc.JDBCTemplate;
import com.wwe.member.model.vo.Member;
import com.wwe.task.model.dao.TaskDao;
import com.wwe.task.model.vo.Task;

public class TaskService {
	
	TaskDao taskDao = new TaskDao();
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public int insertTask(Task task) {
			
			Connection conn = jdt.getConnection();
			int res = 0;
			
			try {
				
				res = taskDao.insertTask(conn, task);
				jdt.commit(conn);
				
			} finally {
				jdt.close(conn);
			}
			
			return res;
	}
	
	public List<String> selectAllTaskList(String projectId){
		
		Connection conn = jdt.getConnection();
		List<String> taskList = null;
		
		try {
			
			taskList = taskDao.selectAllTaskList(conn, projectId);
			jdt.commit(conn);
			
		} finally {
			jdt.close(conn);
		}
		
		return taskList;
		
	}
	
	

}
