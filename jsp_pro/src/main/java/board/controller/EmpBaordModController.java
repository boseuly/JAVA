package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.EmpBoardDTO;
import board.service.EmpBoardService;
import emp.model.EmpDTO;
import emp.service.EmpService;

@WebServlet("/board/mod")
public class EmpBaordModController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String view = "/WEB-INF/jsp/board/mod.jsp";
	private EmpBoardService boardService = new EmpBoardService(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id"); // 게시글 id를 가져온다. -> 게시글 정보를 가져와야 한다.
		HttpSession session = request.getSession();
		
		// delete 했던 것처럼 작성자와 

		EmpBoardDTO data = boardService.getData(Integer.parseInt(id)); // 게시글 정보를 가져온다. 
		// 작성자와 수정요청한 사람이 동일 인물이라면 수정 페이지를 보여준다.
		EmpDTO empData = (EmpDTO) session.getAttribute("loginData");

		
		
		if (data.getEmpId() == empData.getEmpId()) {	// 만약 게시글 id에 맞는 게시글 정보가 있다면 
			
			request.setAttribute("data", data);
			request.setAttribute("empData", empData);
			
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}else {
			// 별도의 페이지로 데이터가 없음을 알려야 한다.
			// 1. 포워드 : url 주소는 바뀌지 않는다.  -> 이거로 한 번 만들어보기 (경고창 띄우는 걸로)
			// 2. 리다이렉트 : url 주소가 바뀐다. 
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터 값으로 값을 변경해줘야 한다.
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		String id = request.getParameter("id");
		
		// 수정된 내용을 저장하기 전에 이전에 내용을 가져 와야 한다.
		
		
		EmpBoardDTO boardData = new EmpBoardDTO();
		boardData.setContent(content); 	// 수정된 내용
		boardData.setTitle(title);		// 수정된 제목
		boardData.setId(Integer.parseInt(id)); // id 저장
		// 값을 저장해줘야 한다.
		boolean result = boardService.modifyBoard(boardData);
		
		if(result) { 
			// 수정 성공 -> 리다이렉트로 수정된 게시글을 보여준다.
			response.sendRedirect(request.getContextPath() + "/board/detail?id=" + id);
		}else {
			// 수정 실패
			System.out.println("수정 실패");
		}
	
	}

}
