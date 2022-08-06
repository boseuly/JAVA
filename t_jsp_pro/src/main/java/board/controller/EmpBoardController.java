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

import board.model.BoardDTO;
import board.service.BoardService;

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
		String limit = "10"; // 우선은 10row로 설정
		String page = "1"; // 우선은 1페이지로 설정
		
		// 만약 둘다 이전 기록이 session에 저장되어 있다면 // 우선 이전 기록으로 초기화시켜놓기
		if(session.getAttribute("pageCount") != null && request.getAttribute("page") !=null) { 
			limit = (String)session.getAttribute("pageCount");
			page = (String)session.getAttribute("page");
		}
		// 만약 page를 요청했다면 -> page 저장
		if(request.getAttribute("page") != null) {
			page = (String)request.getAttribute("page"); 
		}
		// 만약 pageCount가 존재한다면 -> 이전에 pageCount를 요청한 적이 있다면
		if(request.getAttribute("pageCount") != null) { 
			// 근데 request를 찾아봐야하는 거 아닌가....// 우선 request로 해본다
			limit = (String)request.getAttribute("pageCount"); // 요청한 행개수로 저장하기
		}
		
		request.setAttribute("page", session); // 몇 페이지인지 저장하기
		session.setAttribute("pageCount", limit);	// 몇 row인지 저장하기
		// 만약 pageCount가 존재하지 않다면 -> 그냥 기본 조회라면 -> 기본조회는 10row로 한다. 
		
		
		
		String search = request.getParameter("search"); // 검색을 한 경우
		
		List<BoardDTO> datas = null;
		if(search == null) {
			// 조회를 하지 않은 경우 -> 모든 데이터를 가져온다.
			datas = boardService.getPage(page, limit); // 한 페이지를 가지고 온다. // pageCount와 page를 전달해준다. session을 전달해줘야 하나..
			// 페이지 리스트 가져오기
			List<Integer> pageList = boardService.getPageList(limit); // 몇 개 행으로 나눌지 알아야 한다. 
		}else {
			// 조회를 한 경우
			datas  = boardService.getPage(page,limit, search);
			
			
		}
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/board/list.jsp");
		rd.forward(request, response);
	}

}
