package com.myhome.web.comment.service;

import java.sql.SQLDataException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.web.comment.model.CommentDAO;
import com.myhome.web.comment.model.CommentDTO;
import com.myhome.web.common.util.Paging;
@Service
public class CommentService {

	@Autowired
	private CommentDAO dao;

	@Transactional
	public boolean add(CommentDTO data) throws SQLDataException{
		boolean result = dao.insert(data);
		if(!result) { // 저장이 제대로 됐다면
			throw new SQLDataException("댓글을 저장하는 과정에서 SQLDataException 발생"); // 에러 던져주기
		}
		return result;
	}
	
	@Transactional
	public Paging getCommentPage(int page, int limit, int bid) {
		// 페이징 하려면 총 몇 행인지 알아야 한다. 
		int totalRows = dao.getTotalRows(bid); 
		
		Paging paging = new Paging(page, limit, totalRows); 
		dao.selectPage(paging, bid);
		return paging;
	}


/*
	public boolean modify(CommentDTO data) {
		boolean result = dao.updateData(data);
		return result;
	}


	public List<CommentDTO> getDatas(int bid) {
		List<CommentDTO> datas = dao.selectDatas(bid);
		
		return datas;
	}
	
	public CommentDTO getData(int id) { 
		CommentDTO data = dao.selectDatasData(id);
		return data;
	}
	
	public boolean remove(CommentDTO data) {
		boolean result = dao.deleteData(data);
		return result;
	}



*/
	
	
}
