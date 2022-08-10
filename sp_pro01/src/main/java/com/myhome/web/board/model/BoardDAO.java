package com.myhome.web.board.model;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myhome.web.common.util.Paging;

@Repository
public class BoardDAO {
	private static final Logger logger = LoggerFactory.getLogger(BoardDAO.class);
	
	@Autowired
	private SqlSession session;
	
	// 게시글 전부 가져오기
	public List<BoardDTO> selectBoardAll() {
		logger.info("selectBoardAll()");
		List<BoardDTO> datas = session.selectList("empBoardsMapper.selectBoardAll");
		return datas;
	}

	// 페이징
	public int getTotalRows() {
		logger.info("getTotalRows()");
		int result = session.selectOne("empBoardsMapper.getTotalRows");
		return result;
	}
	
	public int getTotalRows(String search) {
		int result = session.selectOne("empBoardsMapper.getTotalRows", search);
		return result;
	}
	

	// 파라미터 없음
	public void selectPage(Paging paging) { // 매개변수를 통해서 paging객체의 주소를 전달한다. -> 주소를 참조하니까 매개변수를 수정하면 getPage() 즉, 호출한 곳에서의 paging 객체도 수정됨
		RowBounds rb = new RowBounds(paging.getOffset(), paging.getLimit());
		Cursor<Object> cursor = session.selectCursor("empBoardsMapper.selectPage", null, rb);
		
		paging.setPageDatas(cursor.iterator()); 
		// 이전에는 session을 우리가 제어하기 때문에 cursor를 open을 하고 다 사용하면 dao.close를 통해서 세션을 종료
		// 하지만 이제는 제어권을 spring에게 넘겼기 때문에 cursor 안에 있는 객체를 완전히 다 꺼내기 전에
		// dao를 닫아버린 것이다. -> @Transactional 어노테이션을 사용해주면 return이 될때까지는 dao.close를 시키지 않도록 해준다.
		// @Transactional 을 사용하기 위해서는 추가해줘야할 것들이 몇 가지 잇음 
	}
	
	public BoardDTO selectData(int id) {
		BoardDTO data = session.selectOne("empBoardsMapper.selectData", id);
		
		return data;
	}
	
	// 추가 작업
	public boolean insertData(BoardDTO data) {
		logger.info("insertData(data={})", data);
		int result = 0;
		if(data.getId() == 0) {
			result = session.insert("empBoardsMapper.insertDataAutoSeq", data);
		}else {
			result = session.insert("empBoardsMapper.insertData", data);
		}
		return result == 1 ? true : false;
	}
	
	public int getNextSeq() {
		int result = session.selectOne("empBoardsMapper.getNextSeq");
		return result;
	}
	
	// 수정
	public boolean modifyBoard(BoardDTO boardUpdateData) {
		int result = session.update("empBoardsMapper.modifyBoard", boardUpdateData);
		
		return result == 1 ? true : false;
	}
	
/*	
	// 파라미터 있음 
	public void selectPage(Paging paging, String search) {
		RowBounds rb = new RowBounds(paging.getOffset(), paging.getLimit());
		Cursor<Object> cursor = session.selectCursor("empBoardsMapper.selectPage", search, rb);
		paging.setPageDatas(cursor.iterator());
	}
	
	
	
	
	
	public boolean updateViewCnt(BoardDTO data) {
		int result = session.update("empBoardsMapper.updateViewCnt", data);
		return result == 1 ? true : false;
	}

	public boolean updateLike(BoardDTO data) {
		int result = session.update("empBoardsMapper.updateLike", data);
		return result == 1 ? true : false;
	}
	// 방문 내역이 있는지 확인
	public BoardStatisDTO selectStatis(BoardStatisDTO data) {
		BoardStatisDTO result = session.selectOne("empBoardsMapper.selectStatis", data);
		return result;
	}

	// 방문 기록
	public boolean insertStatis(BoardStatisDTO data) {
		int result = session.insert("empBoardsMapper.insertStatis", data); // 방문 기록을 추가해주기
		return result == 1 ? true : false;
	}

	// 7일 후 방문한 기록 수정 
	public boolean updateStatis(BoardStatisDTO data) {
		int result = session.update("empBoardsMapper.updateStatis", data); // 방문 기록을 추가해주기
		return result == 1 ? true : false;
		
	}
	// like 수정
	public boolean updateStatis(BoardStatisDTO data, String type) {
		if(type.equals("like")) {
			int result = session.update("empBoardsMapper.updateLikeStatis", data);
			return result == 1 ? true : false;
		}else {
			return updateStatis(data);
		}
		
	}

	

	

	public boolean boardDelete(BoardDTO data) {
		int result = session.delete("empBoardsMapper.deleteBoard", data.getId());
		return result == 1 ? true : false;
	}
	public boolean deleteStatisdata(BoardDTO data) {
		int result = session.delete("empBoardsMapper.deleteStatisData", data.getId());
		return result >= 0 ? true : false; // 해당 게시물을 다른 사람들이 본 기록이 있다면 기록을 다 지워라 -> 없을 수도 있고 여러 개일 수도 있다. 
	}


*/
	

	
}
