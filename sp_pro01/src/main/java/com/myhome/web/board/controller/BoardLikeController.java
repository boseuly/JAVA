package com.myhome.web.board.controller;

import java.sql.SQLDataException;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhome.web.board.model.BoardDTO;
import com.myhome.web.board.service.BoardService;

import netscape.javascript.JSObject;

@Controller
public class BoardLikeController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;

	
	@PostMapping(value="/board/like", produces="applicaton/json; charset=utf-8")
	@ResponseBody 
	public String like(Model model
			, HttpSession session
			, int id) {
		logger.info("getData(id={})", id);
		
		// 추천수를 올리려면 게시글 id로 boardData를 찾고, session과 전달
		BoardDTO boardData = service.getData(id);
		JSONObject json = new JSONObject();
		int likeCnt = boardData.getLike();
		try {
			service.incLike(session, boardData);
			json.put("code", "success");
			json.put("message", "데이터 처리가 완료되었습니다.");
			json.put("likeCnt", likeCnt); // like + 1 한 정보를 전달한다. 
		} catch (SQLDataException e) {
			json.put("code", "noData");
			json.put("message", "데이터가 존재하지 않습니다.");
		}
		
		return json.toJSONString();
	}
}
