package com.wwe.task.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wwe.common.jdbc.JDBCTemplate;
import com.wwe.task.feedback.Feedback;
import com.wwe.task.model.dao.TaskDao;
import com.wwe.task.model.vo.Task;

public class TaskService {
	
	TaskDao taskDao = new TaskDao();
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	//서비스단의 업무추가
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
	
	//서비스단의 프로젝트별 업무리스트조회
	public ArrayList<Task> selectAllTaskList(String projectId){
		
		Connection conn = jdt.getConnection();
		ArrayList<Task> taskList = null;
		
		try {
			
			taskList = taskDao.selectAllTaskList(conn, projectId);
			
		} finally {
			jdt.close(conn);
		}
		
		return taskList;
		
	}
	
	//업무 별 상세내용 조회
	public ArrayList<Task> detailTask(String taskId){
		

		Connection conn = jdt.getConnection();
		ArrayList<Task> detailList = null;
		
		try {
			
			detailList = taskDao.detailTask(conn, taskId);
			
		} finally {
			jdt.close(conn);
		}
		
		return detailList;
	}

	//피드백 추가
	public int insertFeedback(Feedback feedback) {
		
		Connection conn = jdt.getConnection();
		int res = 0;
		
		try {
			
			res = taskDao.insertFeedback(conn, feedback);
			jdt.commit(conn);
			
		} finally {
			jdt.close(conn);
		}
		
		return res;
	}
	
	//내 업무리스트 불러오기
	public ArrayList<Task> selectMyList(String userId,String projectId){
		
		Connection conn = jdt.getConnection();
		ArrayList<Task> myList = null;
		
		try {
			
			myList = taskDao.selectMyList(conn, userId, projectId);
			
		} finally {
			jdt.close(conn);
		}
		
		return myList;
	}
	
	//프로젝트 별 이름 불러오기
	public ArrayList<String> selectName(String projectId){
		
		Connection conn = jdt.getConnection();
		ArrayList<String> memberList = null;
		
		try {
			
			memberList = taskDao.selectName(conn, projectId);
			
		} finally {
			jdt.close(conn);
		}
		
		return memberList;
		
	}
	
	public Map<String, List<String>> selectTaskbyMem(String projectId){

		Connection conn = jdt.getConnection();
		Map<String,List<String>> taskByMember = null;
		
		try {
			
			taskByMember = taskDao.selectTaskbyMem(conn, projectId);
			
		} finally {
			jdt.close(conn);
		}
		
		return taskByMember;
	}

	//알람 추가하기
	public int insertTaskIssue(String userId,String projectId,String typeAlarm) {
		
		Connection conn = jdt.getConnection();
		int res = 0;
		
		try {
			
			res = taskDao.insertTaskIssue(conn,userId,projectId,typeAlarm);
			jdt.commit(conn);
			
		} finally {
			jdt.close(conn);
		}
		
		return res;
	}

	public int deleteTask(String projectId) {
		return 0;
	}
	

}
