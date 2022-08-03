package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.EmpBoardDTO;
import board.service.EmpBoardService;
import common.util.Paging;

@WebServlet("/board")
public class EmpBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EmpBoardService service = new EmpBoardService();
	
	private String view = "/WEB-INF/jsp/board/list.jsp";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 게시글을 불러와야 한다. 게시글 정보는 그냥 datas라고 저장하기
		String page = request.getParameter("page");
		String limit = (String)session.getAttribute("pageCount");
		
		if(page == null) page = "1";
		if(limit == null) limit = "5";
		
		Paging pageData = service.getPage(page, limit);
		
		request.setAttribute("datas", pageData);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
