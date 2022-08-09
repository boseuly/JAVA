package com.myhome.web.login.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhome.web.emp.model.EmpDTO;
import com.myhome.web.login.controller.LoginController;
import com.myhome.web.login.model.LoginDAO;
import com.myhome.web.login.vo.LoginVO;


@Service
public class LoginService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	@Autowired
	private LoginDAO dao;
	
	public boolean login(HttpSession session,LoginVO loginVo) {
		logger.info("login({}, {})",session, loginVo); // 에러 확인하기 위함
		EmpDTO data = new EmpDTO(loginVo.getEmpId(), loginVo.getEmpName());
		
		data.setDeptId(loginVo.getDeptId());
		
		data = dao.selectEmployee(data);
		
		
		if(data == null) {
			return false;
		} else {
//			Map<String, PermDTO> permData = new HashMap<String, PermDTO>();
//			for(PermDTO perm : dao.selectPermission(data.getEmpId())) {
//				permData.put(perm.getTablename(), perm);
//			}
//			System.out.println(permData); // 데이터 구조 확인
//			session.setAttribute("permData",permData); // 권한 리스트 가져오기
			session.setAttribute("loginData", data); // 로그인 데이터 설정
			return true; 
		}
	}
	
}
