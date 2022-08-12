package com.myhome.web.board.service;

import java.sql.SQLDataException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.web.board.controller.BoardController;
import com.myhome.web.board.model.BoardDAO;
import com.myhome.web.board.model.BoardDTO;
import com.myhome.web.board.model.BoardStatisDTO;
import com.myhome.web.board.vo.BoardVO;
import com.myhome.web.common.util.Paging;
import com.myhome.web.emp.model.EmpDTO;


@Service
public class BoardService {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardDAO dao;
	// 게시글을 전부 가져오기
	
	public List<BoardDTO> getBoardAll() {
		logger.info("getBoardAll()");
		
		List<BoardDTO> datas = dao.selectBoardAll();
		
		return datas;
	}
	public int nextSeq() {
		return dao.getNextSeq();  // 다음 bId를 불러온다.
		
	}

	public BoardDTO getData(int id) {
		BoardDTO data = dao.selectData(id);
		return data;
	}
	

	// 조회 없는 페이징 
	@Transactional
	public Paging getPage(int page, int limit) {
		logger.info("getPage()");
		
		int totalRows = dao.getTotalRows();
		
		Paging paging = new Paging(page,limit, totalRows);
		dao.selectPage(paging); // selectPage()에 paging을 매개변수로 주고 selectPage()에서 수정된 paging을 return한다.
		
		return paging;
	}
	
	 // 게시글 추가 
	@Transactional
	public int add(EmpDTO empDto, BoardVO data) {
		logger.info("add(empDto={}, data={})", empDto, data);
		BoardDTO boardDto = new BoardDTO();
		boardDto.setTitle(data.getTitle());
		boardDto.setContent(data.getContent());
		boardDto.setEmpId(empDto.getEmpId());
		
		int seq = dao.getNextSeq();
		boardDto.setId(seq);
		
		boolean result = dao.insertData(boardDto); 
		
		if(result) {
			return boardDto.getId();
		}
		return 0;
	}
	
	// 게시글 수정
	public boolean modifyBoard(BoardDTO boardUpdateData) { // update될 내용이 담긴 객체 
		boolean result = dao.modifyBoard(boardUpdateData); // 게시글 수정
		return result;
		
	}
	// 게시글 삭제 
	@Transactional
	public boolean boardDelete(BoardDTO data) {
		boolean result1 = dao.deleteStatisdata(data);  	// 얘를 수행한 다음
		boolean result2 = dao.boardDelete(data);		// 얘도 수행해야 함
		if(result1 && result2) { // 삭제가 성공한 경우			// 하나의 트랜젝션에 해당 -> @Transactional
			return true;
		}else {
			return false;
		}
	}


	// 조회수 올리기 -> 제한 기능도 구현
	@Transactional
	public void incViewCnt(EmpDTO empData, BoardDTO data) {
		boolean result = false;
		BoardStatisDTO statisData = new BoardStatisDTO();
		statisData.setbId(data.getId());	// bId는 게시물의 id이다. 즉 ,EmpBoardDTO의 id에 해당한다.
		statisData.setEmpId(empData.getEmpId());// 방문한 직원 id 
		
		statisData = dao.selectStatis(statisData); // 방문 기록을 가져온다.
		if(statisData == null) { 				// 만약 방문한 적이 없다면
			result = dao.updateViewCnt(data); 	// 해당 게시물의 조회수 증가
			statisData = new BoardStatisDTO();
			statisData.setbId(data.getId());	// bId는 게시물의 id이다. 즉 ,EmpBoardDTO의 id에 해당한다.
			statisData.setEmpId(empData.getEmpId()); // 방문한 사람을 알려주기 위해서 
			
			dao.insertStatis(statisData); 			// 방문 기록 해주기 -> 어느 게시물을 봤는지, 봤다면 언제 봤는지 좋아요를 눌렀는지 등의 정보 저장
			
		}else { // 방문한 적이 있는 경우
			java.util.Date now = new java.util.Date(); // 현재 날짜 구하기
			long timeDiff = now.getTime() - statisData.getLatestView().getTime(); // 현재 시각 - 이전 방문 시간
			if(timeDiff >= (1000 * 60 * 60 * 24 * 7)) { // 만약 7일이 경과했다면 다른 식 : timeDiff / (1000 * 60 * 60 * 24) >= 7
				result = dao.updateViewCnt(data);		// 그럼 +1 해준다.
				dao.updateStatis(statisData);
			}
		}
		
		if(result) { //조회수 + 1을 성공적으로 했다면 
			data.setViewCnt(data.getViewCnt() + 1); // db는 변경이 됐을지 몰라도 자바에서 불러온 데이터는 이전 데이터이기 때문에
		}
		
//		throw new RuntimeException("RuntimeException을 발생시키면 롤백"); -> 이렇게 하면 이전 트랜젝션 상태로 돌아가게 된다.
	}
	
	
	// 추천수 올리기
	@Transactional(rollbackFor = SQLDataException.class) // SQLDataException이 발생하면 롤백해줘라
	public void incLike(HttpSession session, BoardDTO data) throws SQLDataException {
		logger.info("incLike(data={})",data);
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData"); // 로그인 사원 정보 가져오기
		
		/* 	1. EMP_BOARDS_STATISTICS 테이블에서 추천 했던 기록을 찾는다.
			2. 찾은 기록에서 ISLIKE 컬럼의 값에 따라 다음의 작업을 진행한다.
				2-1. 찾은 기록에서 ISLIKE 컬럼의 값이 N이면 Y로 변경 후
					 EMP_BOARDS에서 추천수 + 1을 한다.
				2-2. 찾은 기록에서 ISLIKE 컬럼의 값이 Y이면 N으로 변경 후 
					 EMP_BOARDS에서 추천수 - 1을 한다.  
		 */

		boolean result = false;
		BoardStatisDTO statisData = new BoardStatisDTO();
		
		statisData.setbId(data.getId());		// bId는 게시물의 id이다. 즉 ,EmpBoardDTO의 id에 해당한다.
		statisData.setEmpId(empData.getEmpId());// 방문한 사람의 id
		
		statisData = dao.selectStatis(statisData);
		if(statisData.isLike()) {
			// 추천을 했음 -> 추천수 - 1 / 추천안함(false)
			statisData.setLike(false);	// 추천 안 한다로 변경
			data.setLike(data.getLike() - 1); // 게시글 추천수 - 1
		}else {
			// 추천을 안 했음 -> 추천수 + 1 / 추천함(true)
			statisData.setLike(true); 	// 추천 한다로 변경
			data.setLike(data.getLike() + 1);	// 게시글 추천수 + 1
		}
		
		result = dao.updateStatis(statisData, "like"); 	 
		if(!result) {
			throw new SQLDataException("BoardService.incLike - 추천 통계 업데이트 중 문제 발생");
		}
		
		result = dao.updateLike(data);
		if(!result) {
			throw new RuntimeException("BoardService.incLike - 추천 통계 업데이트 중 문제 발생");
		}
		
		if(result) {
			data.setViewCnt(data.getLike() + 1); // db는 변경이 됐을지 몰라도 자바에서 불러온 데이터는 이전 데이터이기 때문에
		}
	}
/*	
	// 조회 페이징
	public Paging getPage(int page, int limit, String search) {
		
		int totalRows = dao.getTotalRows(search); // 무엇을 검색할지 정보를 넣어줌
		
		Paging paging = new Paging(page ,limit, totalRows);
		dao.selectPage(paging, search); // selectPage()에 paging을 매개변수로 주고 selectPage()에서 수정된 paging을 return한다.
		
		return paging;
	}
	
	
*/

}
