package locs.model;

import java.util.List;
import java.util.Map;

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
}
