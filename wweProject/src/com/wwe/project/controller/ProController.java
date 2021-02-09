package com.wwe.project.controller;

import java.io.IOException;
import java.util.ArrayList;

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
			case "newpro": writeNewPro(request, response);
					break;
			case "newproimpl": newProImpl(request, response);
					break;
			case "recentpro": recentPro(request, response);
					break;
			case "invitedpro": invitedPro(request, response);
					break;
			default: throw new ToAlertException(ErrorCode.CD_404);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

	
	//새 프로젝트 생성
	private void writeNewPro(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/project/newProject2.jsp")
		.forward(request, response);
	}
	
	//새 프로젝트 생성 수행 메서드
	private void newProImpl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Member leaderId = (Member) request.getSession().getAttribute("user"); //세션에 있는 유저아이디 가져오기
		//페이지에 입력된 제목과 마감기한값 파라미터로 받아오기
		String title = request.getParameter("title"); 
		String deadline = request.getParameter("deadline"); 

		Project project = new Project();
		project.setLeaderId(leaderId.getUserID()); //프로젝트를 만드는 사람 = leader
		project.setTitle(title);
		project.setDeadLine(deadline);

		//service에 넣고 (dao를 거쳐) 되돌아온 값을 res에 넣는다.
		int res = proService.createProject(project);
	
		//성공 시 (1 반환)
		if(res > 0) {
			//현재 세션에 project 키값으로 project 객체 담기
			request.getSession().setAttribute("project", project);
			
			//메인화면으로 request를 보내고 돌아온 값을 재지정
			//getRequestDispatcher() 이후 코드는 실행되지 않고 무시된다.
			request.getRequestDispatcher("/WEB-INF/view/task/main.jsp")
			.forward(request, response);
			
			response.getWriter().print("success"); 
		}else {
			response.getWriter().print("fail");
		}
	}
	
	//새 프로젝트 참여자 추가
	private void addMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("user");
	
		proService.addMember(member.getUserID(), member.getUserName());
	
		//경고창 설정
		request.setAttribute("alertMsg", "게시글 등록이 완료되었습니다.");
		//url 설정
		request.setAttribute("url", "/index.do");
		//result화면으로 request를 보내고 돌아온 값을 재지정
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
		.forward(request, response);
	}
	
	//최근 프로젝트
	private void recentPro(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//세션에서
		HttpSession session = request.getSession();
		//session의 user객체의 모든 속성을 다 받아온다.
		Member member = (Member)session.getAttribute("user");
		
		//그 중에 userId 사용
		//service에서 ArrayList로 날아오는 것을 받음
		ArrayList<Project> projectList = proService.selectRecentProject(member.getUserID());
		
		//service로 받은 projectList를 키값쌍으로 session에 저장
		request.getSession().setAttribute("projectList", projectList);
		
		request.getRequestDispatcher("/WEB-INF/view/task/main.jsp")
		.forward(request, response);
	}
	
	
	//초대된 프로젝트
	private void invitedPro(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//세션에서
		HttpSession session = request.getSession();
		//유저 속성을 전부 받아오고
		Member member = (Member)session.getAttribute("user");
		//프로젝트 초대 수락 여부 받아오기
		Project project = (Project)session.getAttribute("project");
		
		//service단에 유저의 아이디와 초대수락여부 넘기기
		proService.selectInvitedProject(member.getUserID(), project.getIsInvited());
		
		//초대프로젝트 페이지로 이동
		request.getRequestDispatcher("/WEB-INF/view/task/main.jsp")
		.forward(request, response);
	}
}
