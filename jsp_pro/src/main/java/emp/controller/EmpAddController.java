package emp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;
import emp.service.EmpService;
import job.model.JobDTO;
import job.service.JobService;

@WebServlet("/emps/add")
public class EmpAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String view = "/WEB-INF/jsp/emps/add.jsp";
	private EmpService empService = new EmpService();
	private JobService jobService = new JobService();	// 직업 목록 불러와야 함
	private DeptService deptService = new DeptService(); // 부서 목록 불러와야 함
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 부서와 직업 정보를 다 가져와야 한다. -> 리스트로 보여줘야 함
		List<DeptDTO> deptDatas =  deptService.getAll();
		List<JobDTO> jobDatas = jobService.getAll();
		
		request.setAttribute("deptDatas", deptDatas);
		request.setAttribute("jobDatas", jobDatas);
		
		String realPath = request.getServletContext().getRealPath("/static/img/emp/");
		request.setAttribute("imagePath", "static/img/emp/default.png"); // 그냥 기본 이미지로 설정해둔다.
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
