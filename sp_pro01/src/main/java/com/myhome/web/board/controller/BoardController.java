package com.myhome.web.board.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.myhome.web.board.model.BoardDTO;
import com.myhome.web.board.service.BoardService;
import com.myhome.web.board.vo.BoardVO;
import com.myhome.web.common.util.Paging;
import com.myhome.web.emp.model.EmpDTO;


@Controller
@RequestMapping(value="/board") // 모든 메소드를 board로 매핑한다. 
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	 
	// 조회
	@RequestMapping(value="", method=RequestMethod.GET) // 정보만 가져다 줄 거니까 GET
	public String getData(Model model, HttpSession session
			, @RequestParam(defaultValue="1", required=false) int page 			// 몇 페이지인지 받아온다.
			, @RequestParam(defaultValue="0", required=false) int pageCount) { 	// 몇 행 나오게 할 것인지 
		logger.info("getData(page={},pageCount={})",page, pageCount);
		
		if(session.getAttribute("pageCount") == null) {
			session.setAttribute("pageCount", 5); // 이전에 저장한 session이 없을 수도 있으니까 미리 초기화해둔다.
		}
		
		// pageCount를 변경하고자 요청한 경우
		if(pageCount > 0) {
			session.setAttribute("pageCount", pageCount); // 이전에 저장해둔 로그인 정보 가져오기 
		}
		
		Paging pageData =  service.getPage(page, Integer.parseInt(session.getAttribute("pageCount").toString())); // session은 object로 반환되기 
		
		model.addAttribute("pageData", pageData);
		model.addAttribute("datas", pageData.getPageDatas());
		
		
		return "board/list"; // 이건 jsp url 주소임
	}
	
	// 조회 상세		
	/* 
	@RequestMapping(value="/detail/{id}", method=RequestMethod.GET)
	public String getDetail(Model model
			,@PathVariable int id) 
	*/
	// 아래 방법보다 위에 방법이 요즘 트렌드임
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String getDetail(Model model
			,@RequestParam int id) {			// @PathVariable을 사용하면 ?를 사용하지 않아도 된다. // path로 설정해두는 것이다.
		BoardDTO data = service.getData(id);
		model.addAttribute("data", data);
		
		return "board/detail";
		
	}
	
	
	//추가 폼 요청 -> 폼을 보여주는 것은 모델이 필요하다. 
	@GetMapping(value="/add") // 정보만 가져다 줄 거니까 GET
	public String add(@SessionAttribute(name="loginData", required=true) EmpDTO empDto) {
		logger.info("add(empDto={})", empDto);
		return "board/add";
	}
	
	// 추가 저장 요청
	@PostMapping(value="/add") // 정보만 가져다 줄 거니까 GET
	public String add(@ModelAttribute BoardVO boardVo
			, @SessionAttribute(name="loginData", required=true) EmpDTO empDto) { // session에 있는 loginData 속성을 가지고 오는 것, session이름이랑 매개변수 이름이랑 매치가 되어야 한다. -> 만약 매치가 안 되면 name="loginData" 이런식으로 해줘야 함 
		logger.info("add(boardVo={}, empDto={})", boardVo, empDto);
		// EmpDTO empDto = (EmpDTO)session.getAttribute("loginData");
		// 위에서 @SessionAttribute를 했기 때문에 굳이 로직에서 session.getAttribute하지 않아도 된다.
		
		int id = service.add(empDto, boardVo);
		
		if(id > 0) {
			return "redirect:/board/detail?id=" + id; // 추가 성공시 해당 게시글 상세 페이지로 이동
		}else {
			return "board/add"; // 문제가 생기면 다시 baord/add로 forward
		}
	}
	
	// 수정 폼요청 
	@GetMapping(value="/modify")
	public String modify(Model model
			, @SessionAttribute(name="loginData", required=true) EmpDTO empDto
			, @RequestParam int id) {
		logger.info("modify(empDto={}, id={})", empDto, id);
		
		BoardDTO data = service.getData(id);
		if(empDto.getEmpId() == data.getEmpId()) {
			model.addAttribute("data", data);
			return "board/modify";		// 만약 수정하려는 사람과 작성자가 서로 같다면
		}else {
			model.addAttribute("error", "해당 작업을 수행할 권한이 없습니다.");
			return "error/permission"; 	// 만약 수정하려는 사람과 작성자가 서로 다르다면
		}
	}
	
	// 수정 저장 요청
	@PostMapping(value="/modify")
	public String modify(Model model
			,@SessionAttribute(name="loginData", required=true) EmpDTO empDto
			,@ModelAttribute BoardVO boardVo) {
		BoardDTO data = service.getData(boardVo.getId()); // 게시글 데이터 조회 먼저 해준다.
		
		if(empDto.getEmpId() == data.getEmpId()) {
			data.setTitle(boardVo.getTitle());
			data.setContent(boardVo.getContent());
			boolean result = service.modifyBoard(data);
			if(result) {
				return "redirect:/board/detail?id=" + data.getId();
			}else {
				return "board/modify";
			}
		}else {
			model.addAttribute("error", "해당 작업을 수행할 권한이 없습니다.");
			return "error/permission";
		}
	}
	
	
	// 삭제
}
