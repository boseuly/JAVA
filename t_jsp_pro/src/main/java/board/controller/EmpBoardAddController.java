package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDTO;
import board.service.BoardService;
import emps.model.EmpDTO;

@WebServlet("/board/add")
public class EmpBoardAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardService boardService = new BoardService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/board/add.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 추가는 post로 전달
		HttpSession session = request.getSession();
		String title = request.getParameter("title"); 		// 추가할 게시글 제목
		String content = request.getParameter("content");	// 추가할 게시글 내용 
		
		BoardDTO data = new BoardDTO();
		data.setTitle(title);
		data.setContent(content);
		data.setEmpId(((EmpDTO)session.getAttribute("loginData")).getEmpId());
		
		// boardId를 반환받아 추가된 게시글로 이동해준다. -> 상세페이지로 이동
		int boardId = boardService.boardAdd(data); // BoardDTO 전체 다 넣어준다.
		
		// 만약 boardId가 0보다 크다면
		if(boardId > 0) {
			response.sendRedirect(request.getContextPath() + "/board/detail?id=" + boardId);
		}else {
			request.setAttribute("errorMsg", "게시글 추가 작업 중 오류가 발생하였습니다.");
			doGet(request, response);
		}
		
	
	}

}
