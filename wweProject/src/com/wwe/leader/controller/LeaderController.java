package com.wwe.leader.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wwe.leader.model.service.LeaderService;
import com.wwe.leader.model.vo.Leader;

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
			inviteImpl(request,response);
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

		request.getRequestDispatcher("/WEB-INF/view/leader/leader_page.jsp").forward(request, response);
	}
	
	//업무관리 페이지로 이동
	private void totalTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/leader/total_task.jsp").forward(request, response);
	}
	
	//팀에 유저를 초대하는 작업을 처리
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
