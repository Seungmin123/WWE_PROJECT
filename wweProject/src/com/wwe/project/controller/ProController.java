package com.wwe.project.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
			case "newproimpl": newProImpl(request, response);
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
		
		request.getRequestDispatcher("/WEB-INF/view/project/newProject.jsp")
		.forward(request, response);
	}
	
	//새 프로젝트 생성 수행 메서드
	private void newProImpl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userId = (String) request.getSession().getAttribute("user"); //세션에 있는 유저아이디 가져오기
		String title = request.getParameter("title"); //프로젝트 제목
		String addMember = request.getParameter("addMember"); //추가한 팀원

		//진행기간
		//프로젝트 시작날짜
		SimpleDateFormat inStart = new SimpleDateFormat("yyyy-MM-dd");
		String parameter = request.getParameter("startDate");
		Date startDate = null;
		try {
			startDate = (Date) inStart.parse(parameter);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//시작날짜 출력
		//SimpleDateFormat fmtStart = new SimpleDateFormat("yyyy-MM-dd");
		//out.println(fmt.format(date));
		
		//프로젝트 마감날짜
		SimpleDateFormat inEnd = new SimpleDateFormat("yyyy-MM-dd");
		String parameter2 = request.getParameter("endDate");
		Date endDate = null;
		try {
			endDate = (Date) inEnd.parse(parameter2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//마감날짜 출력
		//SimpleDateFormat fmtEnd = new SimpleDateFormat("yyyy-MM-dd");
		//out.println(fmt.format(date));
		
		Project project = new Project();
		project.setUserId(userId);
		project.setTitle(title);
		project.setAddMember(addMember);
		project.setStartDate(startDate);
		project.setEndDate(endDate);
		
		int res = proService.createProject(project);
	
		if(res > 0) {
			//프로젝트 메인화면으로 이동
			request.getRequestDispatcher("/WEB-INF/view/task/main.jsp")
			.forward(request, response);
			response.getWriter().print("success"); 
		}else {
			response.getWriter().print("fail");
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
