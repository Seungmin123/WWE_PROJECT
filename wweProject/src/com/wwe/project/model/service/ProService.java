package com.wwe.project.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.wwe.common.exception.DataAccessException;
import com.wwe.common.exception.ToAlertException;
import com.wwe.common.jdbc.JDBCTemplate;
import com.wwe.leader.model.vo.ProjUser;
import com.wwe.member.model.vo.Alarm;
import com.wwe.member.model.vo.Member;
import com.wwe.project.model.dao.ProDao;
import com.wwe.project.model.vo.Project;
import com.wwe.project.model.vo.ProjectMaster;

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
			//dao에서 받은걸
			member = proDao.selectMember(conn, userId, userName);
		}finally {
			jdt.close(conn);
		}
		//반환
		return member;
	}
	
	
	//최근 프로젝트
	public ArrayList<ProjectMaster> selectRecentProject(String userId){	
		Connection conn = jdt.getConnection();
		ArrayList<ProjectMaster> projectList = null;
		
		try {
			//dao 호출해서 실행한 값을 되돌려받아 projectList에 저장
			projectList = proDao.selectRecentProject(conn, userId);
			
		}finally {
			jdt.close(conn);
		}
		
		return projectList;
	}
	
	
	//초대된 프로젝트 
	public ArrayList<ProjUser> selectInvitedProject(String userId){	
		Connection conn = jdt.getConnection();
		ArrayList<ProjUser> projectList = null;
		
		try {
			//dao에 conn과 유저아이디와 수락여부 넘기기
			projectList = proDao.selectInvitedProject(conn, userId);
		}finally {
			jdt.close(conn);
		}
		
		return projectList;
	}
}