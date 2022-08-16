package com.myhome.web.login.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myhome.web.dept.model.DeptDTO;
import com.myhome.web.dept.service.DeptService;
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
	
	@GetMapping(value="/login")
	public String login(Model model) {
		List<DeptDTO> deptDatas = deptService.getAll();
		model.addAttribute("deptDatas", deptDatas);
		return "login/login";
	}
	
	@Autowired // 필드에서 바인딩을 시켜주는 경우에 해당 -> 기본생성자에서 객체를 바인딩 해준다.
	private LoginService service;
	
	@Autowired
	private DeptService deptService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST) // deptRe를 작성한 이유 -> 부서기억하기에 체크 여부를 확인하기 위해서
	public String login(Model model, LoginVO loginVo,String deptRe,String url, HttpServletRequest request, HttpSession session, HttpServletResponse response) { // 형변환이 불가능한 데이터가 들어오면 안 됨
		// 로그인 요청을 하면 서버에 데이터가 전달이 되고, 전달된 데이터를 추출할 수 있어야 한다.
		logger.info("login({}, {}, {}, {})", loginVo.getEmpName(), loginVo.getDeptId(), loginVo.getEmpName(), deptRe);
		
		
		boolean result = service.login(session,loginVo);
		
		
		if(result) { // 로그인 성공
			
			Cookie cookie;
			if(deptRe != null) { // 부서기억하기 체크 O
				cookie = new Cookie("deptRe", String.valueOf(loginVo.getDeptId()));
				cookie.setMaxAge(60*60*24*5);
			}else {				// 부서기억하기 체크 X
				cookie = new Cookie("deptRe", "");
				cookie.setMaxAge(0);
			}
			response.addCookie(cookie);  	// 앞에 있는 path인 spring만 지워야하기 때문(중복방지를 위해)에 replaceFirst 를 사용해준다. 
			return "redirect:" + url.replaceFirst(request.getContextPath(), ""); // response.redirect 를 써주지 않아도 된다. 그냥 redirect로 써준다. // 로그인 input에 
		}else { //로그인 실패
			return login(model);
		}
	}	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		if (session.getAttribute("loginData") != null ) {
			// 기존의 세션을 만료시켜 새로운 세션을 만들어지게 한다.
			//session.invalidate(); 
			
			// 세션을 만료시키는 게 아니라 session안에 저장한 loginData만 지운다.  -> 이 경우는 sessionId는 그대로지만 그 안에 데이터만 없애주는 방법이다.
			session.removeAttribute("loginData");  
		}
		return "redirect:/index"; // 로그아웃 시킨 다음에 index로 다시 전달
	}
	
}