package com.wwe.leader.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wwe.leader.model.service.LeaderService;
import com.wwe.leader.model.vo.ProjUser;
import com.wwe.task.model.vo.Task;

@WebServlet("/leader/*")
public class LeaderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LeaderService leaderService = new LeaderService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");

		switch (uriArr[uriArr.length - 1]) {
		case "manage":
			manage(request, response); //팀관리 메뉴를 클릭했을 시
			break;
		case "totaltask":
			totalTask(request, response);	//업무관리 메뉴를 클릭했을 시
			break;
		case "chkuser" :
			chkInvalidUser(request,response); //초대팝업에서 초대 버튼 클릭했을 시
			break;
		case "inviteimpl" :
			inviteImpl(request,response); //팀원 초대 기능을 수행
			break;
		case "gettaskimpl" :
			selectTaskList(request,response); //프로젝트의 업무 리스트를 불러오는 기능을 수행
			break;
		case "updateauthority" :
			updateAuthority(request,response);
			break;
		case "search" :
			searchTask(request,response);
			break;
 		default:
			break;
		}
	}

	//post요청이 들어왔을 시 doGet으로 넘겨준다,
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	//팀관리 페이지로 이동
	private void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//실제로는 세션에 있는 projectId를 받아서 처리할 예정
		String projectId = request.getParameter("projectId");
		ArrayList<ProjUser> userList = leaderService.selectUserListByPid(projectId);
		request.setAttribute("userList", userList); //페이지에 DB에서 읽어온 유저 리스트를 전송
		request.getRequestDispatcher("/WEB-INF/view/leader/leader_page.jsp").forward(request, response);
	}
	
	//업무관리 페이지로 이동
	private void totalTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/leader/total_task.jsp").forward(request, response);
	}
	
	//팀원을 초대하기 위해 입력한 아이디가 유효한지 확인
	private void chkInvalidUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String userId = request.getParameter("userId");
			System.out.println(userId);
			String mUserId = leaderService.chkInvalidUser(userId);
			if(!mUserId.equals(" ")) {
				response.getWriter().print("success");
			}else {
				response.getWriter().print("failed");
			}
	}
	
	//팀에 유저를 초대하는 작업을 처리
	private void inviteImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		Gson gson = new Gson();
		
		Map parsedData = gson.fromJson(data, Map.class);
		
		String userId =  parsedData.get("userId").toString();
		String authority = parsedData.get("authority").toString();
		
		int res = leaderService.inviteUser(userId,authority);
		if(res==1) {
			System.out.println("INSERT 성공");
			response.getWriter().print("success");
		}else {
			System.out.println("INSERT 실패");
			response.getWriter().print("failed");
		}
	}
	
	//프로젝트의 유저당 업무 리스트를 가져오는 메소드
	private void selectTaskList(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		String projectId = request.getParameter("projectId");
		System.out.println(projectId);
		ArrayList<Task> taskList =  leaderService.selectTaskList(projectId);
		if(taskList.size()>0) {
			System.out.println("업무리스트 불러오기 성공");
			request.setAttribute("taskList", taskList);
			request.setAttribute("taskCount", taskList.size());
			request.getRequestDispatcher("/WEB-INF/view/leader/total_task.jsp").forward(request, response);
		}else {
			System.out.println("업무리스트 불러오기 실패");
			response.getWriter().print("failed");
		}
	}
	
	private void updateAuthority(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		String data = request.getParameter("data");
		Gson gson = new Gson();
		Map parsedData = gson.fromJson(data, Map.class);
		
		String projectId = parsedData.get("projectId").toString();
		String userId = parsedData.get("userId").toString();
		String authority = parsedData.get("authority").toString();
		
		System.out.println(projectId);
		System.out.println(userId);
		System.out.println(authority);
		ProjUser projUser = new ProjUser();
		projUser.setProjectId(projectId);
		projUser.setUserId(userId);
		projUser.setAuthority(authority);
		
		int res = leaderService.updateAuthority(projUser);
		//권한 변경 성공 시 1반환
		if(res==1) {
			response.getWriter().print("success");
		}else {
			response.getWriter().print("failed");
		}
	}
	
	private void searchTask(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		String data = request.getParameter("data");
		Gson gson = new Gson();
		
		Map parsedData = gson.fromJson(data, Map.class);
		
		String projectId = parsedData.get("projectId").toString();		
		String word = parsedData.get("word").toString();		
		
		Task task = new Task();
		task.setProjectId(projectId);
		task.setTaskId(word);
		
		ArrayList<Task> searchTaskList = leaderService.selectSearchTask(task);
		
		if(searchTaskList.size()>0) {
			String jArray = gson.toJson(searchTaskList);
			response.getWriter().print(jArray);
		}else {
			response.getWriter().print("failed");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
