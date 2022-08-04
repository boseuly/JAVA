package board.model;


import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import common.util.Paging;
import conn.db.DBConn;

public class EmpBoardDAO {
	private SqlSession session;
	
	public EmpBoardDAO() {
		this.session = DBConn.getSqlSession();
	}
	
	public boolean insertData(EmpBoardDTO data) {
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
	
	public EmpBoardDTO selectData(int id) {
		EmpBoardDTO data = session.selectOne("empBoardsMapper.selectData", id);
		
		return data;
	}
	
	public boolean updateViewCnt(EmpBoardDTO data) {
		int result = session.update("empBoardsMapper.updateViewCnt", data);
		return result == 1 ? true : false;
	}

	public boolean updateLike(EmpBoardDTO data) {
		int result = session.update("empBoardsMapper.updateLike", data);
		return result == 1 ? true : false;
	}
	// 방문 내역이 있는지 확인
	public EmpBoardStatisDTO selectStatis(EmpBoardStatisDTO data) {
		EmpBoardStatisDTO result = session.selectOne("empBoardsMapper.selectStatis", data);
		return result;
	}

	// 방문 기록
	public boolean insertStatis(EmpBoardStatisDTO data) {
		int result = session.insert("empBoardsMapper.insertStatis", data); // 방문 기록을 추가해주기
		return result == 1 ? true : false;
	}

	// 7일 후 방문한 기록 수정 
	public boolean updateStatis(EmpBoardStatisDTO data) {
		int result = session.update("empBoardsMapper.updateStatis", data); // 방문 기록을 추가해주기
		return result == 1 ? true : false;
		
	}
	// like 수정
	public boolean updateStatis(EmpBoardStatisDTO data, String type) {
		if(type.equals("like")) {
			int result = session.update("empBoardsMapper.updateLikeStatis", data);
			return result == 1 ? true : false;
		}else {
			return updateStatis(data);
		}
		
	}

	// 게시글 전부 가져오기
	public List<EmpBoardDTO> selectBoardAll() {
		List<EmpBoardDTO> datas = session.selectList("empBoardsMapper.selectBoardAll");
		return datas;
	}


	public void commit() {
		this.session.commit();
	}
	public void rollback() {
		this.session.rollback();
	}
	public void close() {
		this.session.close();
	}

	public boolean modifyBoard(EmpBoardDTO boardUpdateData) {
		int result = session.update("empBoardsMapper.modifyBoard", boardUpdateData);
		
		return result == 1 ? true : false;
	}

	public boolean boardDelete(EmpBoardDTO data) {
		int result = session.delete("empBoardsMapper.deleteBoard", data.getId());
		return result == 1 ? true : false;
	}
	public boolean deleteStatisdata(EmpBoardDTO data) {
		int result = session.delete("empBoardsMapper.deleteStatisData", data.getId());
		return result >= 0 ? true : false; // 해당 게시물을 다른 사람들이 본 기록이 있다면 기록을 다 지워라 -> 없을 수도 있고 여러 개일 수도 있다. 
	}

	// 페이징
	public int getTotalRows() {
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
	}
	// 파라미터 있음 
	public void selectPage(Paging paging, String search) {
		RowBounds rb = new RowBounds(paging.getOffset(), paging.getLimit());
		Cursor<Object> cursor = session.selectCursor("empBoardsMapper.selectPage", search, rb);
		paging.setPageDatas(cursor.iterator());
		
	}


	
}
