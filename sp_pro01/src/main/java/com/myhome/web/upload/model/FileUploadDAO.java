package com.myhome.web.upload.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileUploadDAO {

	@Autowired
	private SqlSession session;
	
	// 파일 추가하기
	public boolean insertFile(FileUploadDTO fileData) {
		int result = session.insert("fileUploadMapper.insertFile", fileData);
		
		return result == 1 ? true : false;
	}
	
	// 파일 가져오기
	public List<FileUploadDTO> getFileList(int bid) {
		return session.selectList("fileUploadMapper.selectFiles", bid);
	}

}
