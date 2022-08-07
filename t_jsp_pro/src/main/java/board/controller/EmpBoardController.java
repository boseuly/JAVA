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

import board.service.BoardService;
import common.util.Paging;

@WebServlet("/board")
public class EmpBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private BoardService boardService = new BoardService();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		// 간단한 조회 기능은 get에서 해결
		// 모든 정보를 가지고 와서 저장해야 한다.
		// 기본적으로 10개의 행을 가지고 오고, 1페이지로 설정한다. 
		// 만약 session에 pageCount라는 파라미터가 존재한다면
		String limit = null; // 우선은 10row로 설정
		String page = (String)request.getAttribute("page"); // 우선은 1페이지로 설정
		// 사용자가 pageCount를 요청했다면 
		
		
		if(request.getAttribute("pageCount") != null) { 
			limit = (String)request.getAttribute("pageCount"); 	// 요청한 행개수로 저장하기
			session.setAttribute("pageCount", limit); 			// pageCount 요청을 한 경우에만 세션에 저장한다. 
		}else {
			limit = (String)session.getAttribute("pageCount");
			if(session.getAttribute("pageCount") == null) {
				limit = "5";
			}
		}
		if(page == null) page= "1";	// 페이지 요청이 없다면 1페이지로 설정
		
		String search = request.getParameter("search"); // 검색을 한 경우
		
		Paging paging = null;
		if(search == null) {
			// 조회를 하지 않은 경우 -> 모든 데이터를 가져온다.
			paging = boardService.getPage(page, limit); // 한 페이지를 가지고 온다. // pageCount와 page를 전달해준다. session을 전달해줘야 하나..
		}else {
			// 조회를 한 경우
			if(search.isEmpty()) { // 조회를 했지만 값이 비어있다면
				paging = boardService.getPage(page, limit);
			}else {
				paging  = boardService.getPage(page,limit, search);
			}
		}
		
		// 만약 페이지데이터에 아무것도 안 담겨 있다면
		if(paging.getPageDatas().size() <= 0) {
			paging = boardService.getPage("1", limit);
		}
		
		request.setAttribute("datas", paging);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/board/list.jsp");
		rd.forward(request, response);
	}

}
