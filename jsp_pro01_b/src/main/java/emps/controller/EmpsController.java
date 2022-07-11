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
	
	// 방법만 살짝 다를 뿐 기존 방법이랑 별 차이는 없다. 단지 init단계를 우리가 직접 만들어줬을 뿐 
	@Override
	public void init(ServletConfig config) throws ServletException {	// 우리가 별도로 명시하지 않아도 처음에 init(초기화) 단계를 거친다. 
		super.init(config);
		this.view = "/WEB-INF/jsp/emps/index.jsp";		// 애초에 초기화 할 때 내가 사용할 경로를 초기화해 둔 것이다. 
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// List<EmpDTO> datas = service.getEmpAll(); 페이징 이전
		// 페이징
		int page = param.defaultIntValue(request, "page", "1");
		int pageCount = param.defaultSessionIntValue(request, "pageCount", "10");	// 이전 페이지에서 저장한 세션 가져오기
		
		List<EmpDTO> datas = service.getEmpPage(page, pageCount);
		// 페이지 리스트 // 우선 총 몇 행인지 알아야 한다.
		List<Integer> pageList = service.getPageList(pageCount);	// 행수에 따라서 페이지 목록이 만들어진다.
		
		request.setAttribute("datas", datas);
		request.setAttribute("page", page);
		request.setAttribute("pageList", pageList);
		
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}
