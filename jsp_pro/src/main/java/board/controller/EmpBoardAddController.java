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

@WebServlet("/board/add")
public class EmpBoardAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmpBoardService service = new EmpBoardService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String view = "/WEB-INF/jsp/board/add.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		EmpBoardDTO data = new EmpBoardDTO();
		data.setTitle(title);
		data.setContent(content);
		data.setEmpId(((EmpDTO)session.getAttribute("loginData")).getEmpId()); // 로그인한 사원의 id를 저장한다.
		
		int boardId = service.add(data);
		if(boardId > 0) {
			System.out.println("저장 이후 board Id : " + boardId);
			response.sendRedirect(request.getContextPath() + "/board/detail?id=" + boardId);
		}else {
			request.setAttribute("errorMsg", "데이터 추가 작업 중 오류가 발생하였습니다.");
			doGet(request, response);
		}
	}

}
