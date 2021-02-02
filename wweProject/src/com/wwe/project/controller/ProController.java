//package com.wwe.project.controller;
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.wwe.common.code.ErrorCode;
//import com.wwe.common.exception.ToAlertException;
//import com.wwe.project.model.service.ProService;
//
//@WebServlet("/ProController")
//public class ProController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private ProService proService = new ProService();
//       
//    public ProController() {
//        super();
//    }
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String uri = request.getRequestURI();
//		String[] uriArr = uri.split("/");
//
//		switch(uriArr[uriArr.length - 1]) {
//			case "newpro": newPro(request, response);
//					  break;
//			case "recentpro": recentPro(request, response);
//					  break;
//			case "invitedpro": invitedPro(request,response);
//					  break;
//			default: throw new ToAlertException(ErrorCode.CD_404);
//		}
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}
//	
//	//새 프로젝트
//	private void newPro(HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException {
//		
//		request.getRequestDispatcher("/WEB-INF/view/project/newpro")
//		.forward(request, response);
//	
//	}
//	
//	//최근 프로젝트
//	private void recentPro(HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException {
//		
//		request.getRequestDispatcher("/WEB-INF/view/project/recentpro")
//		.forward(request, response);
//	
//	}
//	
//	//초대된 프로젝트
//	private void invitedPro(HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException {
//		
//		request.getRequestDispatcher("/WEB-INF/view/project/invitedpro")
//		.forward(request, response);
//	
//	}
//	
//}
