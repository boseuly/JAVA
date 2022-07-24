package login.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dept.model.DeptDTO;
import dept.service.DeptService;
import emp.model.EmpDTO;
import emp.model.EmpDetailDTO;
import emp.service.EmpService;
import job.model.JobDTO;
import job.service.JobService;

@WebServlet("/myinfo")
public class MyInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmpService empService = new EmpService();
	private DeptService deptService = new DeptService();
	private JobService jobService = new JobService(); // 상세 페이지에서는 job 정보도 필요하다. 
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); // 로그인 한 사람의 정보를 가져와야 하기 때문에
		
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData"); // session에 저장된 loginData를 가져온다. 
		
		// 로그인한 계정의 empId를 전달하여 그 사람에 대한 상세 정보를 가져온다.
		EmpDetailDTO empDetail = empService.getDetail(empData.getEmpId());  
		List<DeptDTO> deptDatas = deptService.getAll(); // 부서와 직책은 모든 정보를 가져와야 하기 때문에
		List<JobDTO> jobDatas = jobService.getAll(); 	// 직책 다 가져오기
		
		request.setAttribute("empDetail", empDetail); // 얘는 session에 저장되지 않은 상세 정보임 -> 따로 request에 저장해둠
		request.setAttribute("deptDatas", deptDatas);
		request.setAttribute("jobDatas", jobDatas);
		
		// 로그인을 한 사원의 이미지 / static/img/emp/사원ID.png 가 있는지 확인 후 
		// 없으면 default.png 를 사용하는 것으로 하고, 있으면 사원ID.png 를 사용하는 것으로 구현

		// service 에서 해당 사원의 이미지가 있으면 그 이미지 주소를, 해당 사원의 이미지가 없으면 default.png 의 주소를 전달해준다.
		String imagePath = empService.getProfileImage(request, "/static/img/emp/", empData); 
		request.setAttribute("imagePath", imagePath);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login/myinfo.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
		
		String email = request.getParameter("email"); // EmpDTO
		String phone = request.getParameter("phone"); // EmpDetailDTO
		
		int empId = empData.getEmpId();
		EmpDTO updateEmpData = new EmpDTO();
		updateEmpData.setEmpId(empId);
		updateEmpData.setEmail(email);
		
		EmpDetailDTO updateEmpDetailData = new EmpDetailDTO();
		updateEmpDetailData.setEmpId(empId);
		updateEmpDetailData.setPhone(phone);
		
		boolean result = empService.setEmployee(updateEmpData, updateEmpDetailData); // 저장하는 거임
	}

}
