package com.wwe.leader.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wwe.common.code.ErrorCode;
import com.wwe.common.exception.DataAccessException;
import com.wwe.common.jdbc.JDBCTemplate;
import com.wwe.leader.model.vo.Leader;

public class LeaderDao {

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	// 유저에게 권한을 추가하는 메소드
	public int inviteUser(Connection conn, String userId, String authority) {
		
		int res =0;
		PreparedStatement pstm = null;
		
		try {
			String query = "INSERT INTO TB_PROJECT_USER"
					+"(USER_ID, AUTHORITY, PROJECT_ID) "
					+"VALUES(?,?,?)";
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			pstm.setString(2, authority);
			pstm.setString(3, "프로젝트 1");
			res = pstm.executeUpdate();
			
		}catch(SQLException e) {
			throw new DataAccessException(ErrorCode.IN01,e);
		}finally {
			jdt.close(pstm);
		}
		return res;
		
	}
	
	//팀원 초대 시 입력한 아이디가 유효한 아이디인지 체크하는 메소드
	public String chkInvalidUser(Connection conn, String userId) {
		
		String mUserid = " ";
		int res =0;
		PreparedStatement pstm =null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT USER_ID FROM TB_USER "
					+"WHERE USER_ID =?";
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				mUserid = rset.getString("USER_ID");
			}
		}catch (SQLException e) {
			//커스텀 예외를 처리해주어야 할 부분
			throw new DataAccessException(ErrorCode.SM01, e);
		}finally {
			jdt.close(rset,pstm);
		}
		return mUserid;
	}
	
}