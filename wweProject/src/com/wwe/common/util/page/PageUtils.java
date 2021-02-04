package com.wwe.common.util.page;

import java.util.ArrayList;
import java.util.List;

public class PageUtils {
	private int pageNum = 1;
	private int maxPageNum = 0;
	private int viewPage = 5; // 하단 목록 리스트가 몇개씩 보이게 할지 정하는 변수
	private int viewRows = 5; // 해당테이블의 row의 수를 정하는 변수
	private List<Integer> viewPages = null; // 하단 목록리스트 값을 저장하는 배열

	// 기본생성자로 request.getParam...()으로 넘어온 해당 페이지 값과 테이블에 보여야할 리스트의 사이즈를 받아온다
	public PageUtils(String paramPage, int listSize) {
		// 받아온 페이지 값이 null일 경우 1, 아닐경우 해당값
		if(paramPage != null) {
			this.pageNum = Integer.parseInt(paramPage);
		}
		// 받아온 리스트의 사이즈를 계산하여 마지막 게시판의 번호를 만든다
		this.maxPageNum = listSize%viewRows == 0 ? listSize/viewRows : listSize/viewRows + 1;
		this.viewPages = new ArrayList<Integer>();
		// 만약 페이지값이 0이라면 맨마지막 페이지값을 넘긴다
		if(pageNum == 0) {
			pageNum = maxPageNum;
		}
	}
	
	// 하단 목록에 사용할 배열을 추출
	public List<Integer> getViewPages(){
		// 페이지번호가 보여질 페이지보다 작을경우
		if(pageNum < viewPage) {
			for(int i=1; i<=viewPage; i++) {
				viewPages.add(i);
			}
		// 페이지 번호가 맨마지막 페이지번호를 기준으로
		// 보여질 페이지보다 작을경우
		}else if(maxPageNum-pageNum+1 < viewPage) {
			for(int i=maxPageNum-viewPage+1; i<=maxPageNum; i++) {
				viewPages.add(i);
			}
		// 나머지의 경우
		}else {
			for(int i=pageNum-1; i<pageNum+viewPage-1; i++) {
				viewPages.add(i);
			}
		}
		return viewPages;
	}
	
	// 해당 페이지에 들어갈 값들을 불러준다
	public List<Object> getCommandList(List<Object> dataList){
		List<Object> commandList = new ArrayList<Object>();
		int maxNum = pageNum * viewRows;
		for(int i=maxNum-viewRows; i<maxNum; i++) {
			if(dataList.size() < i+1) {
				break;
			}
			commandList.add(dataList.get(i));
		}
		return commandList;
	}
	
	// 맨 마지막 페이지 번호 반환
	public int getMaxPageNum() {
		return maxPageNum;
	}
	
	// 해당 페이지의 번호를 반환
	public int getPageNum() {
		return this.pageNum;
	}
	
	
}
