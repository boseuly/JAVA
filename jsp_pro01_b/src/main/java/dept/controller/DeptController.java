package dept.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("/depts")
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService service = new DeptService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		
		List<DeptDTO> deptDatas = null;
		if(search == null) {
			int page = 1;
			if (request.getParameter("page") == null) {
				deptDatas = service.getPage(page);
			}else if(request.getParameter("page").isEmpty()) { // null은 아니지만 비어져 있는 경우
				deptDatas = service.getPage(page);
			}else {
				
				if(request.getParameter("page").matches("\\d+")) { // 숫자인 경우
					page = Integer.parseInt(request.getParameter("page"));
				}
				deptDatas = service.getPage(page); // 숫자가 아닌 문자가 온 경우
			}
			request.setAttribute("pageList", service.getPageList());
			
			
		} else {
			boolean isNumber = search.matches("\\d+");
			if(isNumber) {
				DeptDTO data = service.getId(search);
				if(data != null) {
					deptDatas = new ArrayList<DeptDTO>();			
					deptDatas.add(data);
				}
			}
		}
		
		request.setAttribute("deptDatas", deptDatas);
		
		String view = "/WEB-INF/jsp/dept/index.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
