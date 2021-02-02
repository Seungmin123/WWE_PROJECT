package com.wwe.storage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wwe.common.jdbc.JDBCTemplate;
import com.wwe.common.util.file.FileVo;

public class StorageDao {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance(); 
	
	public int insertStorage(Connection conn, FileVo fileData) {
		int res = 0;
		
		String sql = "insert into tb_private_file"
				+ "(user_id,file_name,file_content,file_path,file_rename)"
				+ " values(?,?,?,?,?)";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, fileData.getUserId());
			pstm.setString(2, fileData.getFileName());
			pstm.setString(3, fileData.getFileContent());
			pstm.setString(4, fileData.getFilePath());
			pstm.setString(5, fileData.getFileRename());
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdt.close(pstm);
		}
		return res;
	}
	
	public void deleteStorage(String reName) {
		PreparedStatement pstm = null;
		String sql = "";
	}
	
	public List<FileVo> selectStorage(Connection conn, String idx){
		List<FileVo> res = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select file_name,file_content,file_path,update_date,file_rename from tb_private_file where user_id = ?";
		
		res = new ArrayList<FileVo>();

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, idx);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				FileVo fileVo = new FileVo();
				fileVo.setFileName(rset.getString(1));
				fileVo.setFileContent(rset.getString(2));
				fileVo.setFilePath(rset.getString(3));
				fileVo.setUpdateDate(rset.getDate(4));
				fileVo.setFileRename(rset.getString(5));
				res.add(fileVo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}
	
	
}
