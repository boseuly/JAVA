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
		List<LocsDTO> datas = null;
		
		if(search == null || search.equals("")) {
			datas = service.getAll();
		}else {
			boolean isNumber = search.matches("\\d+");
			if(isNumber) { // 만약 숫자가 맞다면
				LocsDTO data = service.getId(search);
				if(data != null) { // 만약 해당 데이터가 있다면
					datas = new ArrayList<LocsDTO>();
					datas.add(data);
				}
			}
		}
		request.setAttribute("datas", datas);
		String view = "/WEB-INF/jsp/locs/index.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
