package locs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locs.model.LocsDTO;
import locs.service.LocsService;

@WebServlet("/locs/mod")
public class LocsModController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LocsService service = new LocsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		LocsDTO data = service.getId(id);	// 해당 정보가 보여지도록 해야하기 때문에
		
		request.setAttribute("data", data);
		String view = "/WEB-INF/jsp/locs/mod.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// locId는 기본키이기 때문에 수정할 수 없도록 바꿈 (readonly)
		String locId = request.getParameter("locId");
		String stAddr = request.getParameter("stAddr");
		String postal = request.getParameter("postal");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String ctyId = request.getParameter("ctyId");	// 외래키 제약조건
		
		String view;

		// 우선 해당 제약조건을 확인해야 한다.
		int result = service.locsModify(locId, stAddr, postal, city, state, ctyId);
		if(result != 0) {	// 만약 데이터가 있다면
			if(result == -1) {	// 외래키 위배
				view = "/WEB-INF/jsp/locs/error.jsp";
				request.setAttribute("errorMsg", "해당 국가 ID는 존재하지 않습니다.");
				request.getRequestDispatcher(view).forward(request, response);
			}else if(result == 1){
				// 성공시
				// 수정 된 데이터를 보여주기 -> locs/search=id 우선 data를 먼저 넘기기
				response.sendRedirect(request.getContextPath() + "/locs?search=" + locId);	// 수정된 정보를 보여주는 페이지
			}
		}else {	// 만약 result가 0이라면 -> 알 수 없는 오류
			view = "/WEB-INF/jsp/locs/add.jsp";	
			request.setAttribute("errorMsg", "데이터 수정 중 알 수 없는 오류가 발생했습니다.");
			request.getRequestDispatcher(view).forward(request, response);
			
		}
	}
}
