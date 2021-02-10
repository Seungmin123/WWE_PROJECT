package com.wwe.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.wwe.common.code.ErrorCode;
import com.wwe.common.exception.ToAlertException;
import com.wwe.leader.model.service.LeaderService;
import com.wwe.leader.model.vo.ProjUser;
import com.wwe.member.model.vo.Alarm;
import com.wwe.member.model.vo.Member;
import com.wwe.project.model.service.ProService;
import com.wwe.project.model.vo.Project;
import com.wwe.project.model.vo.ProjectMaster;

@WebServlet("/project/*")
public class ProController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProService proService = new ProService();
	private LeaderService leaderService = new LeaderService();
       
    public ProController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");

		switch(uriArr[uriArr.length - 1]) {
			case "loadpro": loadProject(request, response);
					break;
			case "newproimpl": newProImpl(request, response);
					break;
			case "recentpro": recentPro(request, response);
					break;
			case "selectpro": selectPro(request, response);
					break;
			case "invitedpro": invitedPro(request, response);
					break;
			default: throw new ToAlertException(ErrorCode.CD_404);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void loadProject(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Member userId = (Member) request.getSession().getAttribute("user"); //세션에 있는 유저아이디 가져오기
		
		ArrayList<ProjectMaster> recentproList = proService.selectRecentProject(userId.getUserID());
		ArrayList<ProjUser> invitedProList = proService.selectInvitedProject(userId.getUserID()); 
		
		request.setAttribute("recentproList", recentproList);
		request.setAttribute("invitedProList", invitedProList);		
		request.getRequestDispatcher("/WEB-INF/view/project/newProject2.jsp")
		.forward(request, response);
		
	}
	
	//새 프로젝트 생성 수행 메서드
	private void newProImpl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 Member leaderId = (Member) request.getSession().getAttribute("user"); 
		
		 Gson gson = new Gson();
		 String data = request.getParameter("data");
		 Map parsedData = gson.fromJson(data, Map.class);
		 
		 
		 //세션에 있는 유저아이디 가져오기 
		 //페이지에 입력된 제목과 마감기한값 파라미터로 받아오기 
		 String title = (String) parsedData.get("title");
		 String deadline = (String) parsedData.get("deadline");
		  
		 System.out.println(title);
		 System.out.println(deadline);
		 
		 Project project = new Project(); 
		 //프로젝트를 만드는 사람 = leader 
		 project.setLeaderId(leaderId.getUserID());
		 project.setDueDate(deadline);
		 project.setProjectId(title);
		 		  
		 //service에 넣고 (dao를 거쳐) 되돌아온 값을 res에 넣는다. 
		 int res = proService.createProject(project);
		  
		 //성공 시 (1 반환) if(res > 0) { 
		 //현재 세션에 project 키값으로 project 객체 담기
		 request.getSession().setAttribute("project", project);
		 
		 if(res > 0) {
			 System.out.println(res);
				res = leaderService.inviteUser(leaderId.getUserID(), "팀장", title); 
			 if(res > 0) {
				 response.getWriter().print("success");
			 }else {
				 response.getWriter().print("failed");
			 }
		 }else {
			 response.getWriter().print("failed");
		 }
	}
	
	//최근 프로젝트에서 프로젝트 클릭시 task메인페이지로 이동하는 메소드
	private void selectPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//getParameter: fetch 값 받아오기
		String project = request.getParameter("data");
		
		//GSON: JSON을 다른 클래스로 변환해주는 라이브러리
		Gson gson = new Gson();   //자바스크립트의 객체 = JSON
		Map data = gson.fromJson(project, Map.class); //getparameter로 받은 값(fetch에서 json형태로 보내준거) - json을 Map클래스로 바꿔준다 
		
		String projectId = (String) data.get("projectId");
		String userId = (String) data.get("userId");
		String workTime = (String) data.get("workTime");
		String leaderId ="";
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("user");
		
		ArrayList<ProjectMaster> projectList = proService.selectRecentProject(member.getUserID());
		
		for(int i = 0 ; i < projectList.size(); i ++) {
			if(projectList.get(i).getProjectId().equals(projectId)) {
				leaderId = projectList.get(i).getLeaderId();
				break;
			}
		}
		ProjUser projUser = new ProjUser();
		projUser.setProjectId(projectId);
		projUser.setLeaderId(leaderId);
		
		
		System.out.println(leaderId);
		request.getSession().setAttribute("selectProject", projUser);
		
		
		
		ProjUser pUser = new ProjUser();
		pUser.setUserId(member.getUserID());  //세션에 있는 유저의 아이디를 유저객체에 저장
		pUser.setProjectId(projectId);  // 선택한 프로젝트의 프로젝트 아이디를 유저객체에 저장
		
		ProjUser user = leaderService.chkAuthority(pUser); //유저의 권한을 포함한 유저정보를 얻는 코드				
		request.getSession().setAttribute("projUserInfo",user); //얻은 유저정보를 세션에 저장
		
	
		if(projectId != null && userId != null && workTime != null) {
			request.getSession().setAttribute("pUser", pUser);
			response.getWriter().print("success");
		}else {
			response.getWriter().print("fail");
		}

		/*
		 * request.getRequestDispatcher("/WEB-INF/view/task/main.jsp").forward(request,
		 * response);
		 */
	}
	
	
	//최근 프로젝트 (선택한 프로젝트만 불러오는 메서드)
	private void recentPro(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//세션에서
		HttpSession session = request.getSession();
		//session의 user객체의 모든 속성을 다 받아온다.
		Member member = (Member)session.getAttribute("user");
		
		//그 중에 userId 사용
		//service에서 ArrayList로 날아오는 것을 받음
		ArrayList<ProjectMaster> projectList = proService.selectRecentProject(member.getUserID());
		
		if(projectList != null) { //값이 제대로 들어왔는지 확인
			//request 영역에 저장
			request.setAttribute("projectList", projectList);
			request.getRequestDispatcher("/WEB-INF/view/task/main.jsp")
			.forward(request, response);
			
		}else {
			System.out.println("프로젝트를 불러오지 못했습니다.");
		}
	}
	
	
	//초대된 프로젝트
	private void invitedPro(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//세션에서
		HttpSession session = request.getSession();
		//유저 속성을 전부 받아오고
		Member member = (Member)session.getAttribute("user");
		
		//getParameter: fetch의 body의 data를 받아옴
		String data = request.getParameter("data");
		
		Gson gson = new Gson();
		//json으로 넘어온 data를 Map으로 파싱
		Map parsedData = gson.fromJson(data, Map.class);
		
		//파싱된 데이터를 문자열로 변환
		String projectId = parsedData.get("projectId").toString();
		String leaderId = parsedData.get("leaderId").toString();
		
		//ProjUser 객체에
		ProjUser projUser = new ProjUser();
		//문자열로 변환된 데이터 담기
		projUser.setProjectId(projectId);
		projUser.setLeaderId(leaderId);
		
		
		request.getSession().setAttribute("selectProject", projUser);
		
		ProjUser pUser = new ProjUser();
		pUser.setUserId(member.getUserID());  //세션에 있는 유저의 아이디를 유저객체에 저장
		pUser.setProjectId(projectId);  // 선택한 프로젝트의 프로젝트 아이디를 유저객체에 저장
		
		ProjUser user = leaderService.chkAuthority(pUser); //유저의 권한을 포함한 유저정보를 얻는 코드				
		request.getSession().setAttribute("projUserInfo",user); //얻은 유저정보를 세션에 저장
			
		System.out.println("넘긴 프로젝트 아이디 : "+projUser.getProjectId());
		System.out.println("넘긴 프로젝트 아이디 : "+projUser.getLeaderId());
		System.out.println("넘긴 프로젝트 아이디 : "+user.getAuthority());
		
		
		if(projectId != null && leaderId != null) {
			response.getWriter().print("success");
		}else {
			response.getWriter().print("fail");
		}
	
	}
}
