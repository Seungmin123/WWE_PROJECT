package com.wwe.views.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wwe.leader.model.service.LeaderService;
import com.wwe.leader.model.vo.ProjUser;
import com.wwe.task.model.service.TaskService;
import com.wwe.task.model.vo.Task;

/**
 * 
 * @author 김선민
 *
 */
@WebServlet("/views/*")
public class ViewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LeaderService leaderService = new LeaderService();
	TaskService taskService = new TaskService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		switch (uriArr[uriArr.length-1]) {
		case "calendar":
			viewcalendar(request,response);
			break;
		case "graph":
			viewgraph(request,response);
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void viewcalendar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Object> taskList = new ArrayList<Object>();
		Map<String, Object> commandMap = new HashMap<String, Object>();
		
		// 해당 프로젝트의 이름을 받아온다...
		String pId = "프로젝트 1";
		
		//해당 프로젝트의 구성원 정보를 받아 request로 넘겨준다.
		List<ProjUser> userList = leaderService.selectUserListByPid(pId);
		//ArrayList<Task> myList = taskService.selectMyList(userId); 희원이누나 구현중인듯
		// 값을 Task 배열로 저장하즈아아!!!!
		for (ProjUser users : userList) {
			commandMap.put(users.getUserId(), taskService.selectMyList(users.getUserId()));
		}
		
		System.out.println(commandMap);
		
		response.getWriter().print("김선민만세");
		
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("/WEB-INF/view/calendar/calendar.jsp").forward(request, response);
	}
	
	private void viewgraph(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/calendar/graph.jsp").forward(request, response);
	}

}
