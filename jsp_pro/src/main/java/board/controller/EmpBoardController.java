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
		
		String limit = null;
		if(request.getParameter("pgc") != null) { 		// 사용자가 따로 요청한 게 있으면 사용자가 요청한 값으로 해야한다.
			limit = request.getParameter("pgc");
			session.setAttribute("pageCount", limit); 	// 사용자가 요청한 값 세션에 저장하기
		}else {
			limit = (String)session.getAttribute("pageCount"); 
			if(session.getAttribute("pageCount") == null) { // 만약 처음 들어오는 경우라면 
				limit = "5";								// 기본 5개씩 보여지도록 설정해준다. 
			}
		}
		
		
		if(page == null) page = "1";
		
		Paging pageData = null;
		if(request.getParameter("search")== null) { // 만약 search 하지 않았다면
			pageData = service.getPage(page, limit);
		}else {	// 만약 검색을 했다면 
			if(request.getParameter("search").isEmpty()) { 
				pageData = service.getPage(page, limit); // 만약 빈문자열이 들어왔다면 그냥 전체 검색
			}else {
				pageData = service.getPage(page, limit, request.getParameter("search"));	
			}
		}
		
		if (pageData.getPageDatas().size() <= 0) {
			pageData = service.getPage("1", limit);
		}
		
		request.setAttribute("datas", pageData);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
