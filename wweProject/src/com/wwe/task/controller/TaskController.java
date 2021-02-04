package com.wwe.task.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.wwe.member.model.vo.Member;
import com.wwe.project.model.vo.Project;
import com.wwe.task.feedback.Feedback;
import com.wwe.task.model.service.TaskService;
import com.wwe.task.model.vo.Task;

/**
 * Servlet implementation class TaskController
 */
@WebServlet("/task/*")
public class TaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TaskService taskService = new TaskService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] uriArr = request.getRequestURI().split("/");
		switch (uriArr[uriArr.length-1]) {
			case "main": mainTask(request,response);
				break;
			case "member": memberTask(request,response);
				break;
			case "detail": detailTask(request,response);
				break;
			case "add": addTask(request,response);
				break;
			case "addimpl": addImpl(request,response);
				break;
			case "my": myTask(request,response);
				break;
			case "myimpl": selectMyList(request,response);
				break;
			case "insertfeedback": insertFeedback(request,response);
				break;
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
	
	//메인페이지 이동
	protected void mainTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		selectAllTaskList(request,response);
		
		request.getAttribute("taskList");
		request.setAttribute("leaderId", "임희원");//프로젝트 session으로 받아오기

		request.getRequestDispatcher("/WEB-INF/view/task/main.jsp").forward(request, response);
	
	}
	
	//업무리스트불러오기
	protected void selectAllTaskList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Project project = (Project) request.getSession().getAttribute("project");
		
		//String projectId = project.getProjectId();
		String projectId = "프로젝트 1";
		
		ArrayList<Task> taskList = taskService.selectAllTaskList(projectId);

		if(taskList != null) {
			
			System.out.println("업무리스트 성공");
				  
			request.setAttribute("taskList", taskList);
			
		} else {
			System.out.println("업무리스트 불러오기실패");
		}
		
	}

	//멤버 별 업무리스트
	protected void memberTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/task/member.jsp").forward(request, response);
	}
	
	//업무상세내역
	protected void detailTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String taskId = "업무삭제하기" ; //세션 값으로 받아와야 할까?
		request.setAttribute("leaderId", "임희원"); // 세션 값으로 받아오기
		
		ArrayList<Task> detailList = taskService.detailTask(taskId);
		
		if(detailList != null) {
			request.setAttribute("detailList", detailList);
			
			request.getRequestDispatcher("/WEB-INF/view/task/detail.jsp").forward(request, response);
		}else {
			System.out.println("상세정보 불거오기 실패");
			
			request.getRequestDispatcher("/WEB-INF/view/task/my.jsp").forward(request, response);
		}
		
		
	}
	
	//업무추가 페이지 이동
	protected void addTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/task/add.jsp").forward(request, response);
	}
	
	//업무 추가 실행
	protected void addImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		  String taskName = request.getParameter("taskName"); 
		  String deadLine = request.getParameter("deadLine"); 
		  String taskContent = request.getParameter("taskContent");
		  //Member user = (Member)request.getSession().getAttribute("user");
		  
		  //String projectId = 
		  //String userId = user.getUserID();
		  String userId = "wwe123";
		  String projectId = "프로젝트 1";
		  
		  if(taskName == null || taskContent == null || deadLine == null) {
			  request.setAttribute("alertMsg", "내용을 모두 입력해주세요.");
			  request.setAttribute("url", "/task/add");
		  }
		  
		  Task task = new Task(); 
		  task.setTaskId(taskName); 
		  task.setDeadLine(deadLine);
		  task.setTaskContent(taskContent); 
		  task.setUserId(userId);
		  task.setProjectId(projectId);
		  
		  
		  int res = taskService.insertTask(task);
		  
		  if(res > 0) { 
			  System.out.println("업무추가성공");

			  request.setAttribute("alertMsg", "업무 추가에 성공하였습니다.");
			  request.setAttribute("url", "/task/my");
			  
			  request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
			  
		  } else { 
			  System.out.println("업무추가실패");
			  
			  request.setAttribute("alertMsg", "업무 추가에 실패하였습니다.");
			  request.setAttribute("url", "/task/add");
			  
			  request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
		  }
		 
	}
	
	//내 업무리스트 페이지 이동
	protected void myTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		selectMyList(request,response);
		
		request.getAttribute("myList");
		
		request.getRequestDispatcher("/WEB-INF/view/task/my.jsp").forward(request, response);
	}
	
	//내 업무리스트 불러오기
	protected void selectMyList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//세션으로 아이디값 받아와서 하기
		//Member user = (Member) request.getSession().getAttribute("user");
		//String userId = user.getUserID();
		String userId = "wwe123";
		
		ArrayList<Task> myList = taskService.selectMyList(userId);
		
		if(myList != null) {
			System.out.println("내 업무리스트 불러오기 성공");
			request.setAttribute("myList", myList);
			
		}else {
			System.out.println("내 업무리스트 불러오기 실패");
			
			request.setAttribute("alertMsg", "내 업무리스트를 불러오지 못했습니다.");
			request.setAttribute("url", "/task/main");
			  
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
		}
		
	}
	
	//피드백 추가
	protected void insertFeedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//id세션에서 받아오기
		String data = request.getParameter("feedback");
		Gson gson = new Gson();
		Map parsedData = gson.fromJson(data, Map.class);
		
		Feedback feedback = null;
		
		String feedbackComment = (String) parsedData.get("feedbackComment");
		Task task = (Task) request.getAttribute("detailList");
		String taskId = task.getTaskId();
		int privateComment = 0;
		
		feedback.setFeedbackComment(feedbackComment);
		feedback.setTaskId(taskId);
		feedback.setPrivateComment(privateComment);
		
		int res = taskService.insertFeedback(feedback);
		
		if(res > 0) { 
			  System.out.println("피드백추가성공");
			  
		 } else { 
			 System.out.println("피드백추가실패");
			  
		 }
			
	}
	

}
