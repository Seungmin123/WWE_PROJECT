package com.wwe.member.model.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wwe.common.jdbc.JDBCTemplate;
import com.wwe.member.model.dao.MemberDao;
import com.wwe.member.model.vo.Alarm;
import com.wwe.member.model.vo.Member;

public class MemberService {

	public MemberService() {}

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	MemberDao memberDao = new MemberDao();
	
	public Member memberAuthenticate(String userID, String userPW) {
		
		Connection conn = jdt.getConnection();
		Member res = null;
		
		try {
			res = memberDao.memberAuthenticate(conn, userID, userPW);
		}catch(Exception e) {
			e.printStackTrace();
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		
		
		return res;
	}
	
	public Member memberAuthenticateWithEmail(String userEmail) {
		
		Connection conn = jdt.getConnection();
		Member res = null;
		
		try {
			res = memberDao.memberAuthenticateWithEmail(conn, userEmail);
		}catch(Exception e) {
			e.printStackTrace();
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		
		return res;
	}
	
	public Member getMemberProject(String userID) {
		
		Connection conn = jdt.getConnection();
		Member res = null;
		
		try{
			res = memberDao.getMemberProject(conn, userID);
		}catch (Exception e) {
			e.printStackTrace();
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		
		return res;
	}
	
	public int insertMember(Member member) {
		
		Connection conn = jdt.getConnection();
		int res = 0;
		
		try {
			res = memberDao.insertMember(conn,member);
			jdt.commit(conn);
			
		}catch(Exception e) {
			e.printStackTrace();
			jdt.rollback(conn);
		}
		finally {
			jdt.close(conn);
		}
		
		return res;
		
		
		
	}
	
	public Member findMemberID(String userEmail) {
		
		Connection conn = jdt.getConnection();
		Member res = null;
		
		try {
			res = memberDao.findMemberID(conn, userEmail);
		}catch(Exception e) {
			e.printStackTrace();
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		
		return res;
	}
	
	public Member findMemberPW(String userID, String userEmail) {
		
		Connection conn = jdt.getConnection();
		Member res = null;
		
		try {
			res = memberDao.findMemberPW(conn, userID, userEmail);
		}catch(Exception e) {
			e.printStackTrace();
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		
		return res;
	}
	
	public void mailSender(String user, String title, String content) {
		
		Connection conn = jdt.getConnection();
		
		MemberDao memberDao = new MemberDao();
		try {
			memberDao.mailSender(conn, user, title, content);
			jdt.commit(conn);
		}finally {
			jdt.close(conn);
		}
	}
	
	public int modifyMember(Member member) {
		
		Connection conn = jdt.getConnection();
		int res = 0;
		
		try {
			
			res = memberDao.modifyMember(conn,member);
			jdt.commit(conn);
			
		}catch(Exception e) {
			e.printStackTrace();
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		
		return res;
		
	}	
	
	public String getAccessToken(String authorize_code) {
		String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=aede6a7de778e3191a75f6f270b8e947");
            sb.append("&redirect_uri=http://localhost:9393/member/kakao");
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();
            
            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
 
            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            
            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            
            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
            
            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);
            
            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
        return access_Token;
	}
	
	
	public Member kakaoUserInfo(String access_Token){
		
		Member userInfo = new Member();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
	    try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        
	        //    요청에 필요한 Header에 포함될 내용
	        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
	        
	        int responseCode = conn.getResponseCode();
	        System.out.println("responseCode : " + responseCode);
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        
	        String line = "";
	        String result = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        System.out.println("response body : " + result);
	        
	        JsonParser parser = new JsonParser();
	        JsonElement element = parser.parse(result);
	        
	        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
	        JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
	        
	        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
	        String email = kakao_account.getAsJsonObject().get("email").getAsString();
	        
	        userInfo.setUserEmail(email);
	        userInfo.setUserName(nickname);
	        
	        
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    
	    return userInfo;
	}
	
	public void kakaoLogout(String access_Token) {
	    String reqURL = "https://kapi.kakao.com/v1/user/logout";
	    try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
	        
	        int responseCode = conn.getResponseCode();
	        System.out.println("responseCode : " + responseCode);
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String result = "";
	        String line = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        System.out.println(result);
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	
	public Map<String, Object> selectAlarm(String userID, String projectID){
		Map<String, Object> commandMap = new HashMap<String, Object>();
		List<Alarm> alarmList = null;
		Connection conn = jdt.getConnection();
		
		try {
			alarmList = memberDao.selectAlarm(conn, userID, projectID);
			System.out.println("service ㅎㅎ");
			commandMap.put("alarmList", alarmList);
			
		}catch(Exception e) {
			System.out.println("Service Error");
			e.printStackTrace();
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		
		return commandMap;
		
	}
	
	public void addAlarm(String userID, String projectID, String typeOfAlarm){
		int res = 0;
		Connection conn = jdt.getConnection();
		
		try {
			
			res = memberDao.addAlarm(conn, userID, "프로젝트 1", typeOfAlarm);
			
			if(res == 0) {
				System.out.println("등록오류");
			}else {
				jdt.commit(conn);
				System.out.println("등록완료");
			}
			
		}catch(Exception e) {
			System.out.println("Service Error");
			e.printStackTrace();
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}

	}

}
