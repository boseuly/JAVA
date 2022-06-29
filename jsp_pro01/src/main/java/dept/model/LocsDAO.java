package dept.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import conn.db.DBConn;

public class LocsDAO {
	SqlSession session = null;
	
	public LocsDAO () {
		session = DBConn.getSqlSession(); // 생성자 호출하면 sql 호출
	}
	public List<LocsDTO> searchAll(){
		List<LocsDTO> datas = session.selectList("locsMapper.locsSelectAll");
		return datas;
	}
	public LocsDTO searchId(int id) {
		LocsDTO data = session.selectOne("locsMapper.locsSelectId", id);
		return data;
	}
	
}
