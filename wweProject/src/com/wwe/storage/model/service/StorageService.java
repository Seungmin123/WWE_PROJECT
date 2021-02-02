package com.wwe.storage.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wwe.common.jdbc.JDBCTemplate;
import com.wwe.common.util.file.FileUtils;
import com.wwe.common.util.file.FileVo;
import com.wwe.storage.model.dao.StorageDao;

public class StorageService {
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	StorageDao storageDao = new StorageDao();
	
	public void insertStroage(String userId, HttpServletRequest request) {
		Connection conn = jdt.getConnection();
		
		Map<String,List> StorageData = new FileUtils().fileUpload(request);
		
		FileVo Storage = new FileVo();
		
		try {
			//storageDao.insertStorage(conn, Storage);
			for(FileVo data : (List<FileVo>)StorageData.get("fileData")) {
				data.setUserId(userId);
				data.setFileContent(StorageData.get("content").get(0).toString());
				storageDao.insertStorage(conn, data);
			}
			jdt.commit(conn);
		} catch (Exception e) {
			jdt.rollback(conn);
			System.out.println("Service Error");
		} finally {
			jdt.close(conn);
		}	
	}
	
	public void deleteStorage(String reName) {
		Connection conn = jdt.getConnection();
		
		
	}
	
	public Map<String, Object> selectStorage(String idx){
		Map<String, Object> commendMap = new HashMap<String, Object>();
		Connection conn = jdt.getConnection();
		
		try {
			List<FileVo> fileList = storageDao.selectStorage(conn, idx);
			commendMap.put("fileList", fileList);
		}catch (Exception e) {
			System.out.println("Service Error");
		} finally {
			jdt.close(conn);
		}
		
		return commendMap;
	}
}
