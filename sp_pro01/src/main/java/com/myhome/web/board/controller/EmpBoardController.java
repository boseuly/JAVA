package com.myhome.web.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myhome.web.board.model.BoardDTO;
import com.myhome.web.board.service.BoardService;


@Controller
@RequestMapping(value="/board") // 모든 메소드를 board로 매핑한다. 
public class EmpBoardController {
	private static final Logger logger = LoggerFactory.getLogger(EmpBoardController.class);
	
	@Autowired
	private BoardService service;
	 
	// 조회
	@RequestMapping(value="", method=RequestMethod.GET) // 정보만 가져다 줄 거니까 GET
	public String getData(Model model) { 
		List<BoardDTO> datas = service.getBoardAll();
		model.addAttribute("datas", datas);
		return "board/list";
	}
	
	//추가 폼 요청 -> 폼을 보여주는 것은 모델이 필요하다. 
	@RequestMapping(value="/add", method=RequestMethod.GET) // 정보만 가져다 줄 거니까 GET
	public String add(Model model) {
		return "";
	}
	
	// 추가 저장 요청
//	@RequestMapping(value="/board", method=RequestMethod.POST) // 정보만 가져다 줄 거니까 GET
//	public String add(BoardVO boardVo) { 
//		return "";
//	}
//	
	// 수정 
	
	
	// 삭제
}
