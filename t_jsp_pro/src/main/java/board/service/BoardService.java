package board.service;

import java.util.List;

import board.model.BoardDAO;
import board.model.BoardDTO;
import common.util.Paging;

public class BoardService {

	// 한 페이지
	public Paging getPage(String page, String limit) {
		// int 형으로 바꿔야할 듯
		BoardDAO dao = new BoardDAO();
		int totalRows = dao.totalRows(); // 게시글의 총 행수를 가지고 오기 -> 얘는 매개변수 필요 없음 
		
		Paging paging = new Paging(Integer.parseInt(page), Integer.parseInt(limit), totalRows); // 페이징 객체를 생성해준다. 
		dao.selectPage(paging);
		
		dao.close();
		return paging;
	}
	public Paging getPage(String page, String limit, String search) {
		BoardDAO dao = new BoardDAO();
		int totalRows = dao.totalRows(); // 게시글의 총 행수를 가지고 오기 -> 얘는 매개변수 필요 없음 
		
		Paging paging = new Paging(Integer.parseInt(page), Integer.parseInt(limit), totalRows); // 페이징 객체를 생성해준다. 
		dao.selectPage(paging, search);
		
		dao.close();
		return paging;
	}
	public int boardAdd(BoardDTO data) { // boardId를 반환해준다.
		// update할 데이터들 
		BoardDAO dao = new BoardDAO();
		int boardId = dao.nextSeq(); // 다음 번호를 알아냄 -> 그냥 넣어준다.
		data.setId(boardId); 	 // upate할 데이터에 다음 boardId를 넣어준다.
		boolean result = dao.insertBoard(data);
		
		if(result) {
			dao.commit();
			dao.close();
			return data.getId();
		}else {
			dao.rollback();
			dao.close();
		}
		
		return 0; // 만약 실패하면 boardId를 0으로 반환
	}

}
