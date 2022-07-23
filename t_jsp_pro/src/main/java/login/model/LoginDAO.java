package login.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import common.util.AbstractDAO;
import conn.db.DBConn;
import emps.model.EmpDTO;

public class LoginDAO extends AbstractDAO {

	private SqlSession session = DBConn.getSqlSession();
	private String mapper = "loginMapper.%s";
	
	public EmpDTO selectEmployee(Map<String, Object> loginMap) {
		String mapId = String.format(mapper, "selectEmployee");
		EmpDTO data = session.selectOne(mapId, loginMap);
		return data;
	}

	// 권한 설정-> 권한 리스트를 가지고 온다. 
	public List<PermDTO> selectPermission(int empId) {
		String mapId = String.format(mapper, "selectPermission");
		List<PermDTO> datas = session.selectList(mapId, empId);
		return datas;
	}

}
