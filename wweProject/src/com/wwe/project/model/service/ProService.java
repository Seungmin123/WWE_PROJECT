//package com.wwe.project.model.service;
//
//import java.sql.Connection;
//
//import com.wwe.common.exception.DataAccessException;
//import com.wwe.common.exception.ToAlertException;
//import com.wwe.common.jdbc.JDBCTemplate;
//import com.wwe.project.model.dao.ProDao;
//import com.wwe.project.model.vo.Project;
//
//public class ProService {
//
//	JDBCTemplate jdt = JDBCTemplate.getInstance();
//	ProDao proDao = new ProDao();
//	Project pro = new Project();
//	
//	//새 프로젝트 생성
//	public int createProject(Project project){
//		Connection conn = jdt.getConnection();
//		int res = 0;
//		
//		try {
//			res = proDao.insertNewProject(conn,project);
//			jdt.commit(conn);
//			
//		} catch (DataAccessException e) {
//			jdt.rollback(conn);
//			throw new ToAlertException(e.error);
//		} finally {
//			jdt.close(conn);
//		}
//		
//		return res;
//	}
//	
//	//최근 프로젝트
//	public Project selectRecentProject(String userId){	
//		Connection conn = jdt.getConnection();
//		Project project = proDao.selectRecentProject(conn,userId);
//		jdt.close(conn);
//		return project;
//	}
//	
//	//초대된 프로젝트 
//	public Project selectInvitedProject(String userId){	
//		Connection conn = jdt.getConnection();
//		Project project = proDao.selectInvitedProject(conn,userId);
//		jdt.close(conn);
//		return project;
//	}
//
//	
//}