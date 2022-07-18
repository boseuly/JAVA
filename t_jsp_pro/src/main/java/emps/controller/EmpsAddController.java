package emps.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emps.model.EmpDTO;
import emps.service.EmpService;

@WebServlet("/emps/add")
public class EmpsAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String view = "/WEB-INF/jsp/emps/add.jsp";
	private EmpService empService  = new EmpService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId = request.getParameter("empId"); 		// employees
		String empName = request.getParameter("empName"); 	// employees 
		String email = request.getParameter("email");     	// employees
		String jobName = request.getParameter("jobName"); 	// jobs - job_title
		String deptName = request.getParameter("deptName"); // departments - department_name
		
		
		String phone = request.getParameter("phone");
		String hireDate = request.getParameter("hireDate");
		String salary = request.getParameter("salary");
		String commission = request.getParameter("commssion");
		String mngId = request.getParameter("mngId");
		
				
		// 수정 할 수 있는지 확인 하기 위해서 정보를 전달한다.
		int result = empService.addEmps(empId, empName, email, jobName, deptName, 
				phone, hireDate, salary, commission, mngId);
		
		
		
	
	}

}
