package com.wwe.project.model.service;

import java.sql.Connection;
import java.sql.Date;

import com.wwe.common.exception.DataAccessException;
import com.wwe.common.exception.ToAlertException;
import com.wwe.common.jdbc.JDBCTemplate;
import com.wwe.member.model.vo.Member;
import com.wwe.project.model.dao.ProDao;
import com.wwe.project.model.vo.Project;

public class ProService {
	ProDao proDao = new ProDao();
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	//새 프로젝트 생성
	public int createProject(Project project){
		Connection conn = jdt.getConnection();
		int res = 0;
		
		try {
			res = proDao.insertNewProject(conn,project);
			jdt.commit(conn);
			
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		} finally {
			jdt.close(conn);
		}
		
		return res;
	}
	
	//새 프로젝트 참여자 추가
	public Member addMember(String userId, String userName){
		Connection conn = jdt.getConnection();
		Member member = null;
		
		try {
			member = proDao.selectMember(conn, userId, userName);
		}finally {
			jdt.close(conn);
		}
		
		return member;
	}
	
	//최근 프로젝트
	public Project selectRecentProject(Date workTime, String userId){	
		Connection conn = jdt.getConnection();
		Project project = null;
		
		try {
			proDao.selectRecentProject(conn, workTime, userId);
		}finally {
			jdt.close(conn);
		}
		
		return project;
	}
	
	//초대된 프로젝트 
	public Project selectInvitedProject(String userId, int isAllowed){	
		Connection conn = jdt.getConnection();
		Project project = null;
		
		try {
			proDao.selectInvitedProject(conn,userId, 0);
		}finally {
			jdt.close(conn);
		}
		
		return project;
	}
}