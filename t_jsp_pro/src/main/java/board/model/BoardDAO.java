package board.model;

import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import common.util.AbstractDAO;
import common.util.Paging;
import conn.db.DBConn;

public class BoardDAO extends AbstractDAO{

	private SqlSession session = DBConn.getSqlSession();
	
	// 총 몇 행인지 구하기
	public int totalRows() {
		int totalRow = session.selectOne("boardMapper.boardTotalRow");
		return totalRow;
	}
	// 게시글 조회한 경우
	public int totalRows(String search) {
		int totalRow = session.selectOne("boardMapper.boardTotalRow", search);
		return totalRow;
	}

	// 한 페이지에 들어갈 내용 구하기
	public void selectPage(Paging paging) {
		// 커서 생성
		RowBounds rb = new RowBounds(paging.getOffset(), paging.getLimit());
		Cursor<Object> cursor = session.selectCursor("boardMapper.selectBoardPage", null, rb);
		paging.setPageDatas(cursor.iterator()); // setPageDatas에서 값을 넣어준다.
	}
	// 게시글 조회
	public void selectPage(Paging paging, String search) {
		// 커서 생성
		RowBounds rb = new RowBounds(paging.getOffset(), paging.getLimit());
		Cursor<Object> cursor = session.selectCursor("boardMapper.selectBoardPage", search, rb);
		paging.setPageDatas(cursor.iterator()); // setPageDatas에서 값을 넣어준다.
	}
	
	
	public void commit() {
		session.commit();
	}
	public void rollback(){
		session.rollback();
	}
	public void close() {
		session.close();
	}

	

}
