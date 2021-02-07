package com.wwe.views.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wwe.leader.model.service.LeaderService;
import com.wwe.leader.model.vo.ProjUser;
import com.wwe.task.model.service.TaskService;
import com.wwe.task.model.vo.Task;
import com.wwe.views.model.vo.UserByTaskVo;

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
			calendar(request,response);
			break;
		case "graph":
			viewgraph(request,response);
		case "test":
			viewcalendar(request,response);
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
	
	private void calendar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		// 해당 프로젝트의 이름을 받아온다...
		String pId = "프로젝트 1";
		
		//해당 프로젝트의 구성원 정보를 받아 request로 넘겨준다.
		List<ProjUser> userList = leaderService.selectUserListByPid(pId);
		
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("/WEB-INF/view/calendar/calendar.jsp").forward(request, response);
	}
	
	private void viewgraph(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/calendar/graph.jsp").forward(request, response);
	}
	
	private void viewcalendar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UserByTaskVo> taskList = new ArrayList<>(); //calendar.js 에 넣을 event리스트
		Gson gson = new Gson();
		String[] colorList = {"purple","red","blue","pink","yellow"};
		int colorIdx = 0;
		
		String filterData = request.getParameter("data");
		Map filterMap = gson.fromJson(filterData, Map.class);
		List<String> filterList = (List<String>) filterMap.get("name");
		
		// 해당 프로젝트의 이름을 받아온다...
		String pId = "프로젝트 1";
		
		//해당 프로젝트의 구성원 정보를 받기
		List<ProjUser> userList = leaderService.selectUserListByPid(pId);		
		
		// 해당 프로젝트의 구성원들의 각 업무리스트 가져오기
		for (ProjUser users : userList) { // 각 인원별
			if(!filterList.contains(users.getUserId())){
				for (Task task : taskService.selectMyList(users.getUserId())) { // 업무리스트 순환
					taskList.add(new UserByTaskVo(task.getTaskId(),task.getStartDate(),task.getDeadLine(),colorList[colorIdx])); // 파싱파싱
				}
			}
			colorIdx++;
		}
		
		String data = gson.toJson(taskList);
		
		response.setContentType("application/json");
		response.getWriter().print(data);
	}

}
