package com.myhome.web.login.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myhome.web.emp.model.EmpDTO;
import com.myhome.web.login.controller.LoginController;

@Repository
public class LoginDAO{
	private static final Logger logger = LoggerFactory.getLogger(LoginDAO.class);
	
	@Autowired
	private SqlSession session;
	private String mapper = "loginMapper.%s";
	
	public EmpDTO selectEmployee(EmpDTO data) {
		logger.info("selectEmployee({})",data);
		String mapId = String.format(mapper, "selectEmployee");
		EmpDTO result  = session.selectOne(mapId, data);
		return result;
	}

	// 권한 설정-> 권한 리스트를 가지고 온다. 
//	public List<PermDTO> selectPermission(int empId) {
//		String mapId = String.format(mapper, "selectPermission");
//		List<PermDTO> datas = session.selectList(mapId, empId);
//		return datas;
//	}

}
