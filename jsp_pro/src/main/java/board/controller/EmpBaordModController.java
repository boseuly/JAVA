package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		EmpBoardDTO data = boardService.getData(Integer.parseInt(id)); // 게시글 정보를 가져온다. 
		
		if (data != null) {	// 만약 게시글 id에 맞는 게시글 정보가 있다면 
			EmpService empService = new EmpService();
			EmpDTO empData = empService.getId("" + data.getEmpId()); // 사원 아이디에 따른 이름을 가져오기 위해서 
			
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
	}

}
