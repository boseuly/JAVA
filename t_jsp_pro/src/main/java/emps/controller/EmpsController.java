package emps.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.Parameter;
import emps.model.EmpDTO;
import emps.service.EmpService;

@WebServlet("/emps")
public class EmpsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String view = "";
	private EmpService service = new EmpService();
	private Parameter param = new Parameter();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.view = "/WEB-INF/jsp/emps/index.jsp";
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = param.defaultIntValue(request, "page", "1");
		int pageCount = param.defaultSessionIntValue(request, "pageCount", "10");
		
		List<EmpDTO> datas = service.getEmpPage(page, pageCount);
		List<Integer> pageList = service.getPageList(pageCount);
		
		request.setAttribute("datas", datas);
		request.setAttribute("page", page);
		request.setAttribute("pageList", pageList);
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

}
