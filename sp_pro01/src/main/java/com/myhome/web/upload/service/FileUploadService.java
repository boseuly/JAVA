package com.myhome.web.upload.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhome.web.upload.controller.FileUploadController;
import com.myhome.web.upload.model.FileUploadDAO;
import com.myhome.web.upload.model.FileUploadDTO;

@Service
public class FileUploadService {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@Autowired
	private FileUploadDAO dao;

	public boolean updateFile(FileUploadDTO fileData) {
		logger.info("updateFile(fileData={})", fileData);
		// 전달받은 파일 데이터를 저장해야 한다.
		boolean result = dao.insertFile(fileData);
		if(!result) {
			// 에러 던지기
		}
			
		return result;
	}

	// 해당 게시글의 파일들을 가지고 오기
	public List<FileUploadDTO> getFileList(int bid) { // bId임
		return dao.getFileList(bid);
	}

}
