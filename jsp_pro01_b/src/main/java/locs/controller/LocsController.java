package locs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locs.model.LocsDTO;
import locs.service.LocsService;

@WebServlet("/locs")
public class LocsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LocsService service = new LocsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		
		List<LocsDTO> locsDatas = null;
		if(search == null) {
			int page = 1;
			if (request.getParameter("page") == null) {
				locsDatas = service.getPage(page);
			}else if(request.getParameter("page").isEmpty()) {
				locsDatas = service.getPage(page);
			}else {
				if(request.getParameter("page").matches("\\d+")) {
					page = Integer.parseInt(request.getParameter("page"));
				}
				locsDatas = service.getPage(page);	// 숫자가 아니라 문자가 온 경우
			}
			locsDatas = service.getAll();
		} else {
			boolean isNumber = search.matches("\\d+");
			if(isNumber) {
				LocsDTO data = service.getId(search);
				if(data != null) {
					locsDatas = new ArrayList<LocsDTO>();
					locsDatas.add(data);
				}
			}
		}
		
		request.setAttribute("locsDatas", locsDatas);
		
		String view = "/WEB-INF/jsp/locs/index.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
