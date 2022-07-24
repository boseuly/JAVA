package login.model;

import java.util.Map;

import common.util.AbstractDAO;
import emp.model.EmpDTO;

public class LoginDAO extends AbstractDAO{

	public EmpDTO selectEmployee(Map<String, Object> loginMap) { // 사용자가 입력한 정보
		EmpDTO data = session.selectOne("loginMapper.selectEmployee", loginMap);
		
		return data;
	}

}
