package board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import board.model.EmpBoardDAO;
import board.model.EmpBoardDTO;
import board.model.EmpBoardStatisDTO;
import emp.model.EmpDTO;

public class EmpBoardService {

	// 게시글을 전부 가져오기
	public List<EmpBoardDTO> getBoardAll() {
		EmpBoardDAO dao = new EmpBoardDAO();
		List<EmpBoardDTO> datas = dao.selectBoardAll();
		
		dao.close();
		return datas;
	}
	
	public int add(EmpBoardDTO data) {
		EmpBoardDAO dao = new EmpBoardDAO();
		
		int seq = dao.getNextSeq();
		data.setId(seq);
		
		boolean result = dao.insertData(data);
		
		if(result) {
			dao.commit();
			dao.close();
			return data.getId();
		}
		dao.rollback();
		dao.close();
		return 0;
	}

	public EmpBoardDTO getData(int id) {
		EmpBoardDAO dao = new EmpBoardDAO();
		EmpBoardDTO data = dao.selectData(id);
		dao.close();
		return data;
	}
	
	// 게시글 수정
	public boolean modifyBoard(EmpBoardDTO boardUpdateData) { // update될 내용이 담긴 객체 
		EmpBoardDAO dao = new EmpBoardDAO();
		boolean result = dao.modifyBoard(boardUpdateData); // 게시글 수정
		if(result) {
			dao.commit();
		}else {
			dao.rollback();
		}
		dao.close();
		return result;
		
	}

	// 조회수 올리기 -> 제한 기능도 구현
	public void incViewCnt(HttpSession session, EmpBoardDTO data) {
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData"); // 본 사람이 누구인지 알아야 하니까 현재 로그인 한 사람의 정보를 session에서 불러온다.
		EmpBoardDAO dao = new EmpBoardDAO();
		
		
		boolean result = false;
		EmpBoardStatisDTO statisData = new EmpBoardStatisDTO();
		statisData.setbId(data.getId());	// bId는 게시물의 id이다. 즉 ,EmpBoardDTO의 id에 해당한다.
		statisData.setEmpId(empData.getEmpId());// 방문한 직원 id 
		
		statisData = dao.selectStatis(statisData); // 방문 기록을 가져온다.
		if(statisData == null) { 				// 만약 방문한 적이 없다면
			result = dao.updateViewCnt(data); 	// 해당 게시물의 조회수 증가
			statisData = new EmpBoardStatisDTO();
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
			dao.commit();							// 자바에서도 기존의 viewCnt에서 + 1을 해줘야 한다. 안 그럼 증가 X
		}else {
			dao.rollback();
		}
		dao.close();
	}
	// 추천수 올리기
	public void incLike(HttpSession session, EmpBoardDTO data) {
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData"); // 로그인 사원 정보 가져오기
		EmpBoardDAO dao = new EmpBoardDAO();
		
		/* 	1. EMP_BOARDS_STATISTICS 테이블에서 추천 했던 기록을 찾는다.
			2. 찾은 기록에서 ISLIKE 컬럼의 값에 따라 다음의 작업을 진행한다.
				2-1. 찾은 기록에서 ISLIKE 컬럼의 값이 N이면 Y로 변경 후
					 EMP_BOARDS에서 추천수 + 1을 한다.
				2-2. 찾은 기록에서 ISLIKE 컬럼의 값이 Y이면 N으로 변경 후 
					 EMP_BOARDS에서 추천수 - 1을 한다.  
		*/

		boolean result = false;
		EmpBoardStatisDTO statisData = new EmpBoardStatisDTO();
		
		statisData.setbId(data.getId());	// bId는 게시물의 id이다. 즉 ,EmpBoardDTO의 id에 해당한다.
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
		dao.updateStatis(statisData, "like"); 	 
		result = dao.updateLike(data);
		
		
		if(result) {
			data.setViewCnt(data.getLike() + 1); // db는 변경이 됐을지 몰라도 자바에서 불러온 데이터는 이전 데이터이기 때문에
			dao.commit();							// 자바에서도 기존의 like에서 + 1을 해줘야 한다. 안 그럼 증가 X
		}else {
			dao.rollback();
		}
		dao.close();
	}

	public boolean boardDelete(int boardId) {
		EmpBoardDAO dao = new EmpBoardDAO();
		boolean result = dao.boardDelete(boardId);
		if(result) { // 삭제가 성공한 경우
			dao.commit();
		}else {
			dao.rollback();
		}
		
		dao.close();
		
		return result;
	}

	

}
