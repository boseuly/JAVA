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
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		int page = param.defaultIntValue(request, "page", "1");
		int pageCount = param.defaultSessionIntValue(request, "pageCount", "10");
		
		List<EmpDTO> datas = service.getEmpPage(session, page, pageCount); // 권한 제한 하기 위해 session을 추가로 전달해줌 7.22
		List<Integer> pageList = service.getPageList(session, pageCount);			// 사용자 정보를 전달하기 위해서 session을 전달한 거임
		
		request.setAttribute("datas", datas);
		request.setAttribute("page", page);
		request.setAttribute("pageList", pageList);
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

}
