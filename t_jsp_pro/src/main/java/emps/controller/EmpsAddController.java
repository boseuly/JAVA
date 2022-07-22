package emps.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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

@WebServlet("/emps/add")
public class EmpsAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String view = "/WEB-INF/jsp/emps/add.jsp";
	private EmpService empService  = new EmpService();
	private DeptService deptService = new DeptService();
	private JobService jobService = new JobService();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId = request.getParameter("empId"); 		// employees
		String empName = request.getParameter("empName"); 	// employees  
		String email = request.getParameter("email");     	// employees
		String jobId = request.getParameter("jobId"); 	// jobs - jobName으로 보여지지만 value=jobId 이다.
		String deptId = request.getParameter("deptId"); // departments - -> deptName이 보여지지만 value=deptId이다.
		
		
		String phone = request.getParameter("phone");
		String hireDate = request.getParameter("hireDate");
		String salary = request.getParameter("salary");
		String commission = request.getParameter("commssion");
		String mngId = request.getParameter("mngId");
		
		EmpDTO empData = new EmpDTO();
		empData.setEmpId(empId);
		empData.setEmpName(empName);
		empData.setJobId(jobId);
		empData.setEmail(email);
		
		EmpDetailDTO empDetailData = new EmpDetailDTO();
		empDetailData.setEmpId(empId);
		empDetailData.setHireDate(hireDate);
		empDetailData.setPhone(phone);
		empDetailData.setSalary(salary);
		empDetailData.setCommission(commission);
		
		
		boolean result = empService.add(empData, empDetailData);
		
		if(result) {
			// 추가 작업 성공
			Part part = request.getPart("uploadImage");
			
			if(!part.getSubmittedFileName().isEmpty()){
				String realPath = request.getServletContext().getRealPath("/static/");
				
			}
		}
	}

}
