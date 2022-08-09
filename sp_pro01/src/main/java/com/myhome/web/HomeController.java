package com.myhome.web;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myhome.web.dept.model.DeptDTO;
import com.myhome.web.dept.service.DeptService;

/**
 * Handles requests for the application home page.
 */
@Controller // 스프링에서는 controller면 controller 어노테이션 지정해줘야 한다. -> 그래야 bean객체로 ㄷ등록이 된다. 
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class); // logging 기능 
	
	@Autowired
	private DeptService deptService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET) 	
	public String index(Model model) {			 
		logger.info("Welcom index!"); // log 기능 출력
		
		List<DeptDTO> deptDatas = deptService.getAll();
		
		
		model.addAttribute("deptDatas", deptDatas);		
		
		return "index";
	}
/*
	@RequestMapping(value = "/", method = RequestMethod.GET) 	// 이전에는 webServlet()에서 매핑을 했지만 이제는 메소드를 통해서 매핑이 가능하다.
	public String home(Locale locale, Model model) {			// 이전에는 doGet, doPost로 구분을 했지만 이제는 requestMethod.Get 이런 식으로 함 
		logger.info("Welcome home! The client locale is {}.", locale); // log 기능 출력
		
		// setvlet -> jsp 로 정보를 전달할 때 request.setAttribute() 이것과 같은 역할
		model.addAttribute("title", "home메소드" ); 
		
		return "home"; // views안에 있는 jsp파일에 해당함 -> 이렇게 
	}
*/	
	
	
	
}
