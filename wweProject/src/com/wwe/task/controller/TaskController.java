package com.wwe.task.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.wwe.common.code.AddAlarmCode;
import com.wwe.member.model.service.MemberService;
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
			case "updatestate": updateState(request,response);
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
		selectName(request,response);
			
		selectTaskbyMem(request,response);
		
		//deleteTask(request,response);
		
		request.getAttribute("leaderId");
		//request.setAttribute("userId","yeongwoo");
		Member user = (Member) request.getSession().getAttribute("user");
		String userId = user.getUserID();
		request.getAttribute("taskByMember");
		

		request.getRequestDispatcher("/WEB-INF/view/task/main.jsp").forward(request, response);
	
	}
	
	//마감기한 지난 업무 삭제하기
	protected void deleteTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Project project = (Project) request.getSession().getAttribute("project");
		
		String projectId = project.getProjectId();
		//String projectId = "프로젝트 1";
		
		int res = taskService.deleteTask(projectId);
		
		if(res > 0) {
			System.out.println("업무삭제 완료");
		}else {
			System.out.println("업무삭제 실패");
		}
		
	}
	
	//이름불러오기
	protected void selectName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Project project = (Project) request.getSession().getAttribute("project");
		
		//String projectId = project.getProjectId();
		String projectId = "프로젝트 1";
		
		ArrayList<String> memberList = taskService.selectName(projectId);

		if(memberList != null) {
			
			System.out.println("업무리스트 성공");
				  
			request.setAttribute("memberList", memberList);
			
		} else {
			System.out.println("업무리스트 불러오기실패");
		}
		
	}
	
	//업무리스트불러오기
	protected void selectAllTaskList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Project project = (Project) request.getSession().getAttribute("project");
		
		//String projectId = project.getProjectId();
		String projectId = "프로젝트 1";
		
		
		ArrayList<Task> taskList = taskService.selectAllTaskList(projectId);
		
		//leaderList로 넘기기 위한 id
		request.setAttribute("leaderId", "wwe123");//프로젝트 session으로 받아오기
		String leaderId = "wwe123";
		//myList로 넘겨 질 업무 식별 하기위한 id
		Member user = (Member) request.getSession().getAttribute("user");
		String userId = user.getUserID();
		//String userId = "test";
		
		

		if(taskList != null) {
			
			System.out.println("업무리스트 성공");
				  
			request.setAttribute("taskList", taskList);
			
		} else {
			System.out.println("업무리스트 불러오기실패");
		}
		
	}

	//멤버 별 리스트 
	protected void memberTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//멤버 별 업무리스트
		//memberList로 맞춰서 업무리스트 가져오기
		selectAllTaskList(request,response);
		
		String name = request.getParameter("name");
		request.setAttribute("name", name);
		ArrayList<Task> memTaskList = new ArrayList<>();
		ArrayList<Task> TaskList = (ArrayList<Task>) request.getAttribute("taskList");
		
		for (Task task : TaskList) {
			if(task.getUserId() == name) {

			}
		}

		
		request.setAttribute("memTaskList", memTaskList);
		request.getRequestDispatcher("/WEB-INF/view/task/member.jsp").forward(request, response);
	}
	
	//멤버 별 리스트 뽑아오기
	protected void selectTaskbyMem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//멤버 별 업무리스트
		//memberList로 맞춰서 업무리스트 가져오기
		//프로젝트 세션에서 불러오기
		//Project project = (Project) request.getSession().getAttribute("project");
		//String projectId = project.getProjectId();
		
		//session에서 leaderId userId 받아오기
		String projectId = "프로젝트 1";
		Member user = (Member) request.getSession().getAttribute("user");
		String userId = user.getUserID();
		//String userId = "yeongwoo";
		
		String leaderId = "wwe123";
		
		ArrayList<Task> taskByMember = taskService.selectTaskbyMem(projectId,leaderId,userId);
		
		for (Task task : taskByMember) {
			System.out.println(task);
		}

		
		if(taskByMember != null) {
			
			System.out.println("멤버 별 리스트 성공");
			
			request.setAttribute("taskByMember", taskByMember);
			
		}else {
			System.out.println("멤버 별 리스트 실패");
		}
	}
	
	
	
	//업무상세내역
	protected void detailTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String taskId = request.getParameter("name") ; //세션 값으로 받아와야 할까?
		//String leaderId = request.getSession.getAttribute("project");
		request.setAttribute("leaderId", "임희원"); // 세션 값으로 받아오기
		
		ArrayList<Task> detailList = taskService.detailTask(taskId);
		
		if(detailList != null) {
			request.setAttribute("detailList", detailList);
			
			request.getRequestDispatcher("/WEB-INF/view/task/detail.jsp").forward(request, response);
		}else {
			System.out.println("상세정보 불러오기 실패");
			
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
		  Member user = (Member)request.getSession().getAttribute("user");
		  
		  //Project project = (Project) request.getSession().getAttribute("project");
		  //String projectId = project.getProjectId();
		  String userId = user.getUserID();
		  //String userId = "yeongwoo";
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
			  
			  String typeAlarm = "업무추가";
			  new MemberService().addAlarm(userId, projectId, "업무");
			  
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
		Member user = (Member) request.getSession().getAttribute("user");
		String userId = user.getUserID();
		//Project project = (Project) request.getSession().getAttribute("project");
		//String projectId = project.getProjectId();
		String projectId = "프로젝트 1";
		
		
		ArrayList<Task> myList = taskService.selectMyList(userId,projectId);
		
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
		//Task task = (Task) request.getAttribute("detailList");
		//String taskId = task.getTaskId();
		String taskId = "업무삭제하기";
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
	
	//업무상태값변경하기
	public void updateState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String data = request.getParameter("data");
		Gson gson = new Gson();
		Map parsedData = gson.fromJson(data, Map.class);
		
		String taskState = (String) parsedData.get("state");
		String taskId = (String) parsedData.get("taskId");
		Member user = (Member) request.getSession().getAttribute("user");
		String userId = user.getUserID();
		
		int res = taskService.updateState(taskState,taskId,userId);
		
		if(res > 0) {
			System.out.println("수정완료");
		}else {
			System.out.println("수정실패");
		}
		
	}
	
	//권한 땡겨오는 메서드 만들기
	
	public void selectAuthority(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	//업무 수정 메서드
	public void updateTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
}
