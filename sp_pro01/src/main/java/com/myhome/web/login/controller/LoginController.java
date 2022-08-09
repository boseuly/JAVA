package com.myhome.web.login.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myhome.web.login.service.LoginService;
import com.myhome.web.login.vo.LoginVO;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

/*
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model
			, @RequestParam("id") int empId
			, @RequestParam("deptId") int deptId
			, @RequestParam("name") String empName) { // 형변환이 불가능한 데이터가 들어오면 안 됨
		// 로그인 요청을 하면 서버에 데이터가 전달이 되고, 전달된 데이터를 추출할 수 있어야 한다.
		logger.info("empId : {}", loginVo.getEmpId());
		logger.info("deptId : {}", loginVo.getDeptId());
		logger.info("empName : {}", loginVo.getEmpName());
		
		return "";
	}
*/
	@Autowired
	private LoginService service;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model, LoginVO loginVo, HttpSession session) { // 형변환이 불가능한 데이터가 들어오면 안 됨
		// 로그인 요청을 하면 서버에 데이터가 전달이 되고, 전달된 데이터를 추출할 수 있어야 한다.
		logger.info("login({}, {}, {})", loginVo.getEmpName(), loginVo.getDeptId(), loginVo.getEmpName());
		
		boolean result = service.login(session,loginVo);
		if(result) {
			// 로그인 성공 
			return "redirect:/index"; // response.redirect 를 써주지 않아도 된다. 그냥 redirect로 써준다.
		}else {
			// 로그인 실패
			return "/login/login"; // 
		}
	}	
	
	
}