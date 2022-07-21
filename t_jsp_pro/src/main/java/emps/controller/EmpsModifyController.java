package emps.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dept.service.DeptService;
import emps.model.EmpDTO;
import emps.model.EmpDetailDTO;
import emps.service.EmpService;
import job.service.JobService;

@WebServlet("/emps/modify")
@MultipartConfig
public class EmpsModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String view = "/WEB-INF/jsp/emps/modify.jsp";


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		EmpService empService = new EmpService();
		DeptService deptService = new DeptService();
		JobService jobService = new JobService();
		
		
		EmpDTO data = empService.getId(id);
		EmpDetailDTO dataDetail = empService.getDetail(data.getEmpId());
		
		request.setAttribute("data", data);
		request.setAttribute("dataDetail", dataDetail);
		request.setAttribute("deptDatas", deptService.getAll());
		request.setAttribute("jobDatas", jobService.getAll());
		
		
		String imagePath = empService.getProfileImage(request, "/static/img/emp/", data); // 이미지가 위치하는 경로
		request.setAttribute("imagePath", imagePath);
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId = request.getParameter("empId"); 		// employees
		String empName = request.getParameter("empName"); 	// employees  
		String email = request.getParameter("email");     	// employees
		String jobId = request.getParameter("jobId"); 	// jobs - jobName으로 보여지지만 value=jobId 이다.
		String deptId = request.getParameter("deptId"); // departments - -> deptName이 보여지지만 value=deptId이다.
		
		EmpService empService = new EmpService();
		
		EmpDTO empData = empService.getId(empId); // 일부러 수정 작업을 하기 전에 조회를 먼저 함 -> 에러를 방지하기 위해 -> 다른 사용자가 삭제를 한 경우 에러 페이지가 나올 수 있도록 함 
		if(empData == null) {
			request.getSession().setAttribute("error", "해당 데이터는 존재하지 않습니다.");
			response.sendRedirect(request.getContextPath() + "/error");
			return;
		}
		
		
		String phone = request.getParameter("phone");
		String hireDate = request.getParameter("hireDate");
		String salary = request.getParameter("salary");
		String commission = request.getParameter("commssion");
		String mngId = request.getParameter("mngId");
		
		empData.setEmpName(empName);
		empData.setJobId(jobId);
		empData.setEmail(email);
		
		EmpDetailDTO empDetailData = empService.getDetail(empData.getEmpId());
		if(empDetailData == null) {
			request.getSession().setAttribute("error", "해당 데이터는 존재하지 않습니다.");
			response.sendRedirect(request.getContextPath() + "/error");
			return;
		}
		empDetailData.setEmpId(empId);
		empDetailData.setHireDate(hireDate);
		empDetailData.setPhone(phone);
		empDetailData.setSalary(salary);
		empDetailData.setCommission(commission);
		
		
		boolean result = empService.setEmployee(empData, empDetailData);
		
		if(result) {
			// 추가 작업 성공
			Part part = request.getPart("uploadImage");
			
			if(!part.getSubmittedFileName().isEmpty()){
				String realPath = request.getServletContext().getRealPath("/static/");
				part.write(realPath + empData.getEmpId() + "png");
			}
			
			response.sendRedirect(request.getContextPath() + "/emps/detail?id=" + empData.getEmpId()); // 여기 이어서 작업 7.21
			
		}else {
			doGet(request,response); // 에러 나는 경우 doGet 메서드 호출
		}
		
	}

}
