package locs.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import conn.db.DBConn;
import locs.model.LocsDTO;

public class LocsDAO {
	
	SqlSession session = null;
	
	public LocsDAO() {
		session = DBConn.getSqlSession();
	}
	
	public List<LocsDTO> searchAll() {
		List<LocsDTO> datas = session.selectList("locsMapper.locsSelectAll");
		return datas;
	}
	
	public LocsDTO searchId(int id) {
		LocsDTO data = session.selectOne("locsMapper.locsSelectId", id);
		return data;
	}
	
	// 외래키 확인
	public boolean checkCtyId(String ctyId) {
		int result = session.selectOne("locsMapper.checkCtyId",ctyId );
		if(result == 1) {		// 1이면 이미 해당 아이디가 존재 -> 사용 O
			return true;
		}
		return false;
	}
	// data 추가하기 
	public int addLocs(LocsDTO data) {
		int result = session.insert("locsMapper.locsAdd", data);
		if(result != 0) {
			return 1;
		}
		return 0;
	}
	
	// 수정하기(update)
	public boolean update(LocsDTO data) {
		// 해당 아이디를 가지고 검색을 한 다음에 update를 해야 한다.
		int result = session.update("locsMapper.locsMod", data);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	
	public void commit() {
		session.commit();
	}
	public void rollback() {
		session.rollback();
	}
	public void close() {
		session.close();
	}
}
