package com.wwe.storage.controller;

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
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.wwe.common.util.file.FileUtils;
import com.wwe.common.util.file.FileVo;
import com.wwe.common.util.page.PageUtils;
import com.wwe.member.model.vo.Member;
import com.wwe.storage.model.service.StorageService;


@WebServlet("/storage/*")
public class StorageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StorageService storageService = new StorageService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StorageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		switch (uriArr[uriArr.length-1]) {
		case "personal":
			personalStorage(request,response);
			break;
		case "share":
			shareStorage(request,response);
			break;
		case "pUpload":
			uploadFile(request,response,false); // 개인저장소 관련
			break;
		case "sUpload":
			uploadFile(request,response,true); // 공유저장소 관련
			break;
		case "download":
			downloadFile(request,response);
			break;	
		case "delete":
			deleteFile(request,response);
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
	
	private void personalStorage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("user");
		
		Map<String, Object> commandMap = storageService.selectStorage(member.getUserID(),false);
		List<Object> fileList = (List<Object>) commandMap.get("fileList");		
		
		PageUtils page = new PageUtils(request.getParameter("page"), fileList.size());
		request.setAttribute("dataList", page.getCommandList(fileList));
		request.setAttribute("viewList", page.getViewPages());
		request.setAttribute("pageNum", page.getPageNum());
		request.setAttribute("maxPage", page.getMaxPageNum());
		request.getRequestDispatcher("/WEB-INF/view/storage/personal_storage.jsp").forward(request, response);
	}
	
	private void shareStorage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idx = "project1";

		Map<String, Object> commandMap = storageService.selectStorage(idx,true);
		List<Object> fileList = (List<Object>) commandMap.get("fileList");
		
		PageUtils page = new PageUtils(request.getParameter("page"), fileList.size());
		request.setAttribute("dataList", page.getCommandList(fileList));
		request.setAttribute("viewList", page.getViewPages());
		request.setAttribute("pageNum", page.getPageNum());
		request.setAttribute("maxPage", page.getMaxPageNum());
		request.getRequestDispatcher("/WEB-INF/view/storage/share_storage.jsp").forward(request, response);
	}
	
	private void uploadFile(HttpServletRequest request, HttpServletResponse response,boolean isTeam) throws ServletException, IOException {
		// 파일 업로드
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("user");
		
		String userId = member.getUserID();
		String projectId = "project1"; // 프로젝트 입장 기능 추가후에 사용가능
		
		// 로그인한 세션정보로 파일을 업로드
		request.setAttribute("filterPath", userId);
		storageService.insertStroage(userId,projectId,isTeam, request);
		
		if(isTeam) {	
			response.sendRedirect("/storage/share");
		}else {	
			response.sendRedirect("/storage/personal");
		}
	}
	
	private void downloadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String originName = request.getParameter("ofname");
		String reName = request.getParameter("rfname");
		String path = request.getParameter("savePath");
		
		response.setHeader("content-disposition", "attachment; filename="+originName);
		request.getRequestDispatcher("/file"+path+reName).forward(request, response);
	}
	
	private void deleteFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idx = request.getParameter("fileIdx");
		String reName = request.getParameter("rfname");
		String path = request.getParameter("savePath");
		String isTeam = request.getParameter("isTeam");
		String url = "";
		
		if(isTeam.equals("true")) {
			storageService.deleteStorage(idx, true);
			url="share";
		}else {
			storageService.deleteStorage(idx, false);
			url="personal";
		}
		new FileUtils().deleteFile(path+reName);
		response.sendRedirect("/storage/" + url);
	}

}















