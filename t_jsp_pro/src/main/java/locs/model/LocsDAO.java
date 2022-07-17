package locs.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.util.AbstractDAO;
import conn.db.DBConn;
import locs.model.LocsDTO;

public class LocsDAO extends AbstractDAO{
	
	public List<LocsDTO> searchAll() {
		List<LocsDTO> datas = session.selectList("locsMapper.locsSelectAll");
		return datas;
	}
	
	public LocsDTO searchId(int id) {
		LocsDTO data = session.selectOne("locsMapper.locsSelectId", id);
		return data;
	}

	public boolean addLocs(LocsDTO data) {
		int result = session.insert("locsMapper.insertLocs", data);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public boolean checkCtyId(String ctyId) {
		int result = session.selectOne("locsMapper.checkCtyId", ctyId);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public boolean delLocs(LocsDTO data) {
		// 해당 아이디를 찾아서 삭제 한다.
		int result = session.delete("locsMapper.deleteLocs", data);
		if(result == 1) {	// 만약 삭제 성공
			return true;
		}
		return false;
	}

	public boolean modLoc(LocsDTO data) {
		int result = session.update("locsMapper.updateLocs", data);
		if(result == 1) {
			return true;
		}
		return false;
	}
}
