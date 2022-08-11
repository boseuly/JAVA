package com.myhome.web.comment.model;

import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.web.common.util.Paging;

@Repository
public class CommentDAO {
	
	@Autowired
	private SqlSession session;

	public boolean insert(CommentDTO data) {
		int result = session.insert("commentMapper.insertData", data);
		return result == 1? true:false;
	}

	public List<CommentDTO> selectDatas(int bid) {
		List<CommentDTO> result = session.selectList("commentMapper.selectDatas",bid);
		return result;
	};
	
	// 특정 게시물의 댓글에 대한 페이징
	public int getTotalRows(int bid) { 
		int result = session.selectOne("commentMapper.getTotalRows", bid);
		return result;
	}

	@Transactional // 커서가 들어가 있으면 저절로 session이 종료되지 않도록 Transaction
	public void selectPage(Paging paging, int bid) {
		RowBounds rb = new RowBounds(paging.getOffset(), paging.getLimit());
		Cursor<Object> cursor = session.selectCursor("commentMapper.selectDatas", bid, rb);
		paging.setPageDatas(cursor.iterator());
		
	}
	
	/*
	// 댓글 삭제 x 업데이트 할 거임 
	public CommentDTO selectDatasData(int id) {
		CommentDTO result = session.selectOne("commentMapper.selectData", id);
		return result;
	}

	public boolean deleteData(CommentDTO data) {
		int result = session.update("commentMapper.deleteData", data);
		return result  == 1? true : false;
	}

	
	public boolean updateData(CommentDTO data) {
		int result = session.update("commentMapper.updateData",data);
		
		return result == 1? true: false;
	}

	
*/	
	
}
