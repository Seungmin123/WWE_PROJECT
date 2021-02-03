package com.wwe.common.code;

public enum ErrorCode {
	SM01("회원정보를 조회하는 도중 에러가 발생하였습니다"),
	SM02("프로젝트의 팀원리스트를 조회하는 도중 에러가 발생했습니다."),
	IN01("팀원을 초대하는 도중 에러가 발생했습니다."),
	UU01("팀원의 권한을 변경하는 도중 에러가 발했습니다."),
	TK01("업무리스트를 조회하는 도중 에러가 발생했습니다."),
	UM01("회원정보 수정 중 에러가 발생하였습니다."),
	DM01("회원정보 삭제 중 에러가 발생하였습니다."),
	//SB01("게시판 정보를 조회하는 도중 에러가 발생","/board/list"(에러발생시 이동할 페이지));
	//		+ dao의 해당 메서드의 throw new DataAccessException에 (ErrorCode.SB01,e) 추가
	// 	  (에러 관리 시 이 ErrorCode 페이지만 보면 됨 - 유지보수 및 기능확장에 용이)
	SB01("게시글 정보를 조회하는 도중 에러가 발생했습니다."),
	SF01("파일 정보를 조회하는 도중 에러가 발생했습니다."),
	IB01("게시글 등록 중 에러가 발생했습니다."),
	IF01("파일정보 등록 중 에러가 발생했습니다."),
	AUTH01("해당 페이지에 접근하실 수 없습니다."), // 경로 지정 안하면 기본경로: index
	MAIL01("메일 발송 중 에러가 발생하였습니다."),
	AUTH02("이미 인증이 만료된 링크입니다."),
	AUTH03("게시글은 로그인 후 작성할 수 있습니다."),
	API01("API통신 도중 에러가 발생하였습니다."),
	CD_404("존재하지 않는 경로입니다."),
	FILE01("파일업로드중 예외가 발생하였습니다.");
	
	//result.jsp를 사용해 띄울 안내문구
	private String errMsg;
	//result.jsp를 사용해 이동시킬 경로
	private String url = "/index";
	
	//index로 이동시킬 경우
	ErrorCode(String errMsg) {
		this.errMsg = errMsg;
	}
	
	//index 외의 지정 페이지로 이동시킬 경우
	ErrorCode(String errMsg, String url) {
		this.errMsg = errMsg;
		this.url = url;
	}
	
	//errMsg getter
	public String errMsg() {
		return errMsg;
	}
	
	//url getter
	public String url() {
		return url;
	}
}
