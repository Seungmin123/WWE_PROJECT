package com.wwe.project.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wwe.common.code.ErrorCode;
import com.wwe.common.exception.ToAlertException;
import com.wwe.member.model.vo.Member;
import com.wwe.project.model.service.ProService;
import com.wwe.project.model.vo.Project;

@WebServlet("/project/*")
public class ProController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProService proService = new ProService();
       
    public ProController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");

		switch(uriArr[uriArr.length - 1]) {
			case "newpro": newPro(request, response);
					  break;
					  
			case "recentpro": recentPro(request, response);
					  break;
			case "invitedpro": invitedPro(request,response);
					  break;
			default: throw new ToAlertException(ErrorCode.CD_404);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//새 프로젝트 생성
	private void newPro(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Project project = new Project();
		proService.createProject(project);
		
		request.getRequestDispatcher("/WEB-INF/view/project/newProject.jsp")
		.forward(request, response);
	}
	

	
//	//새 프로젝트 생성 시 참여자 추가
//	private void newProMember(HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException {
//		
//		HttpSession session = request.getSession();
//		Member member = (Member)session.getAttribute("userName");
//		member = (Member)session.getAttribute("isLogin");
//		
//		Project project = new Project();
//		proService.createProject(project);
//		
//		request.getRequestDispatcher("/WEB-INF/view/project/newProject.jsp")
//		.forward(request, response);
//	}
	
	//프로젝트 값 넣기
	private void projectAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String projectId = request.getParameter("projectId");
		String dueDate = request.getParameter("dueDate");
		String progress = request.getParameter("progress");
		String leaderId = request.getParameter("leaderId");
		
		Project project = null;
		project = proService.createProject(project);
		
		if(project != null) {
		
		request.getSession().getAttribute("user");
		}
		
		
		
	}
	
	//최근 프로젝트
	private void recentPro(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("userName");
		Project project = new Project();
		proService.selectRecentProject(project.getWorkTime(), member.getUserID());
		
		request.getRequestDispatcher("/WEB-INF/view/task/main.jsp")
		.forward(request, response);
	}
	
	//초대된 프로젝트
	private void invitedPro(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("user");
		Project project = (Project)session.getAttribute("isAllowed");
		
		proService.selectInvitedProject(member.getUserID(), project.getIsAllowed());
		
		request.getRequestDispatcher("/WEB-INF/view/project/invitedProject.jsp")
		.forward(request, response);
	}
}
