package com.wwe.leader.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/leader/*")
public class LeaderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		case "inviteimpl" :
				inviteUser(request,response); //초대팝업에서 초대 버튼 클릭했을 시
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
	private void inviteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
	}
}
