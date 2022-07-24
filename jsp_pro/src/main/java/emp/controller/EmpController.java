package emp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.util.Parameter;
import emp.model.EmpDTO;
import emp.service.EmpService;

@WebServlet("/emps")
public class EmpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String view = "/WEB-INF/jsp/emps/index.jsp";
	private Parameter param = new Parameter();
	private EmpService service = new EmpService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String search = request.getParameter("search");
		int page = param.defaultIntValue(request, "page", "1");
		int pageCount = param.defaultSessionIntValue(request, "pageCount", "10");
		
		List<EmpDTO> datas = new ArrayList<EmpDTO>();
		List<Integer> pageList = null;
		if(search == null) { // 여기까지 정상적으로 출력 됨 
			datas = service.getPage(page, pageCount);
			pageList = service.getPageList(pageCount);
		}else {
			EmpDTO data = service.getId(search);
			if(data == null) {
				request.setAttribute("errorMsg", "부서 ID를 잘못 입력하였습니다.");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error/error.jsp");
				rd.forward(request, response);
				return;
			}
			datas.add(data);
		}
		
		request.setAttribute("datas", datas);
		request.setAttribute("page", page);
		request.setAttribute("pageList", pageList);
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

}
