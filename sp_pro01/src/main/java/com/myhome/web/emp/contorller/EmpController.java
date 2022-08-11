package com.myhome.web.emp.contorller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myhome.web.common.util.Paging;
import com.myhome.web.emp.service.EmpService;

@Controller
@RequestMapping(value="/emps")
public class EmpController {
	private static final Logger logger = LoggerFactory.getLogger(EmpController.class);
	
	@Autowired
	private EmpService service;
	
	// 직원 목록 조회
	@GetMapping(value="") 
	public String getData(Model model
			, @RequestParam(defaultValue="1", required=false) int page
			, @RequestParam(defaultValue="0", required=false) int pageCount
			, HttpSession session) {
		// emp전체 리스트를 불러오기 위해서 필요한 것 정보를 전달할 수 있도록 해주는 model만 매개변수로 받는다.
		logger.info("getEmpData(page={}, pageCount={})",page, pageCount);
		
		if(session.getAttribute("pageCount") == null) { // 만약 이전에 저장한 pageCount가 없다면
			session.setAttribute("pageCount", 5); 		// 기본적으로 5행씩 나오도록 해라 
		}
		
		// 요청한 pageCount가 존재한다면(없으면 0으로 설정하기로 했는데 0보다 크다는 건 pageCount를 요청했다는 거다.)
		if(pageCount > 0) { 
			session.setAttribute("pageCount", pageCount); // 요청한 pageCount로 값을 설정하기
		}
		Paging pageData = service.getPage(page, pageCount);
		model.addAttribute("datas", pageData); // 이름 어떻게 사용했는지 확인 해야 함 
		
		return "emps/index"; // emp/index로 전달
	}
}
