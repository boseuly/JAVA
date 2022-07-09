package locs.model;

import java.util.List;

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

	public void close() {
		session.close();
	}
}
