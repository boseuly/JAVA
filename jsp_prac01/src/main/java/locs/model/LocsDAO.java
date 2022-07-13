package locs.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import conn.db.DBConn;

public class LocsDAO {
	SqlSession session;
	
	public LocsDAO () {
		session = DBConn.getSqlSession();
	}
	
	public List<LocsDTO> getAll() {
		List<LocsDTO> datas = session.selectList("locsMapper.locsSelectAll");
		
		return datas;
	}
	

	public LocsDTO getId(int id) {
		LocsDTO data = session.selectOne("locsMapper.locsSelectId", id);
		return data;
	}

	public boolean checkCtyId(String ctyId) {
		int result = session.selectOne("locsMapper.checkCtyId", ctyId);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public boolean locsAdd(LocsDTO data) {
		int result = session.insert("locsMapper.insertLocs", data);
		if(result == 1) {
			return true;
		}
		return false; 
	}

	public void close() {
		session.close();
	}
	
	public void commit() {
		session.commit();
	}
	public void rollback() {
		session.rollback();
	}
	// 한 페이지에 나오는 데이터들을 반환한다.
	public List<LocsDTO> searchPage(int start, int end) {
		RowBounds rb = new RowBounds(start, end);
		Cursor<LocsDTO> cursor = session.selectCursor("locsMapper.locsSelectAll", null, rb);
		// cursor List 대신 사용한다. 한줄 한줄씩 읽어온다.
		
		List<LocsDTO> datas = new ArrayList<LocsDTO>();
		Iterator<LocsDTO> it = cursor.iterator();
		
		if(it.hasNext()) {
			datas.add(it.next());
		}
		return datas;
	}

	public int totalRow() {
		int rowCount = session.selectOne("locsMapper.rowCount");
		return rowCount;
	}
	
	
	
}
