package com.wwe.storage.model.service;

import com.wwe.common.jdbc.JDBCTemplate;
import com.wwe.storage.model.dao.StorageDao;

public class StorageService {
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	StorageDao storageDao = new StorageDao();
	
	
}
