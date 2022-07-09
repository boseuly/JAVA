package locs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locs.model.LocsDTO;
import locs.service.LocsService;

@WebServlet("/locs/del")
public class LocsDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	LocsService service = new LocsService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String locsId = request.getParameter("id");
		
		LocsDTO data = service.getId(locsId);
		request.setAttribute("data", data);
		
		String view = "/WEB-INF/jsp/locs/del.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String locId = request.getParameter("locId");
		String stAddr = request.getParameter("stAddr");
		String postal = request.getParameter("postal");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String ctyId = request.getParameter("ctyId");
		
		boolean result = service.deleteLocs(locId);
		String view;
		if(result) {	// 삭제 성공 -> 목록 화면
			view = "/WEB-INF/jsp/locs/index.jsp";
			request.setAttribute("msg", "삭제가 완료 되었습니다.");
			request.getRequestDispatcher(view).forward(request, response);
		}else {
			view = "/WEB-INF/jsp/locs/errorDel.jsp";
			request.setAttribute("errorMsg", "해당 지역 정보를 삭제할 수 없습니다.");
			request.getRequestDispatcher(view).forward(request, response);
		}
		
	}

}
