package com.wwe.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wwe.member.model.service.MemberService;
import com.wwe.member.model.vo.Member;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");
		
		switch(uriArr[uriArr.length-1]) {
			case "signin" : signIn(request, response);
				break;
			case "signup" : signUp(request, response);
				break;
			case "signinimpl" : signInImpl(request, response);
				break;
			case "signupimpl" : signUpImpl(request, response);
				break;
				
				
			case "logout" : logout(request, response);
				break;
			default : ;	
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void signIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/SignIn.jsp")
		.forward(request, response);
	}
	
	private void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/SignUp.jsp")
		.forward(request, response);
	}
	
	private void signInImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String data = request.getParameter("data");
		Gson gson = new Gson();
		Map parsedData = gson.fromJson(data, Map.class);

		String userID = (String) parsedData.get("userID");
		String userPW = (String) parsedData.get("userPW");
		
		Member user = memberService.memberAuthenticate(userID, userPW);
		
		if(user != null) {
			//session scope로 user 전달
			request.getSession().setAttribute("user", user);
			response.getWriter().print("success");
		}else {
			response.getWriter().print("fail");
			System.out.println("hihihihihihihihihi");
		}
	}
	
	private void signUpImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/SignUp.jsp")
		.forward(request, response);
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().removeAttribute("user");
		request.getRequestDispatcher("/WEB-INF/view/index/index.jsp")
		.forward(request, response);
	}

}
