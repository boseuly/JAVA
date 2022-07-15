package login.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dept.model.DeptDTO;
import dept.service.DeptService;
import emps.model.EmpDTO;
import emps.model.EmpDetailDTO;
import emps.service.EmpService;
import job.model.JobDTO;
import job.service.JobService;
@MultipartConfig
@WebServlet("/myinfo")
public class MyInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String view = "/WEB-INF/jsp/login/myinfo.jsp";
	
	private EmpService empService = new EmpService();
	private DeptService deptService = new DeptService();
	private JobService jobService = new JobService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginData") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
		} else {
			EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
			
			EmpDetailDTO empDetail = empService.getDetail(empData.getEmpId());
			List<DeptDTO> deptDatas = deptService.getAll();
			List<JobDTO> jobDatas = jobService.getAll();
			
			request.setAttribute("empDetail", empDetail);
			request.setAttribute("deptDatas", deptDatas);
			request.setAttribute("jobDatas", jobDatas);
			
			// 로그인을 한 사원의 이미지 /static/img/emp/사원ID.png 가 있는지 확인 후 
			// 없으면 default.png를 사용하는 것으로 하고, 있으면 사원ID.png를 사용하는 것으로 만든다.
			String realPath = request.getServletContext().getRealPath("/static/img/emp/");	// 진짜 주소를 가지고 옴
			File file = new File("/static/img/emp/" + empData.getEmpId() + ".png");	// 진짜주소에서 이미지를 가지고 온다.
			
			if(file.exists()) {	// 있는지 없는지 알려준다. 있으면 true
				request.setAttribute("imagePath", "/static/img/emp/" + empData.getEmpId() + ".png");	// 파일명이 empId에 따라 붙여졌기 때문에 
			}else {
				request.setAttribute("imagePath", "/static/img/emp/default.png");	// url상으로는 이 주소가 맞지만 서버의 하드디스크에 저장된 경로는 여기가 아님
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData"); 
/*		
		String imgFileName = part.getSubmittedFileName();	// 이미지 file명
		long imgSize = part.getSize();	// 이미지 사이즈(bite 단위)를 알려준다.
		
		System.out.println(imgFileName); // 출력 예시) test.png
		System.out.println(imgSize);	// 84560 파일 크기
*/
		
		
		String email = request.getParameter("email");	// EmpDTO 에 담긴 정보
		String phone = request.getParameter("phone");	// EmpDetailDTO 에 담긴 정보
		
		
		// 로그인이 된 상태인지 확인 -> 이건 필터에서 이미 검사하기 때문에 빼줌
//		if(session.getAttribute("loginData") == null) {	// 만약 세션이 만료돼서 null이라면
//			response.sendRedirect(request.getContextPath() + "/login");
//			return;
//		}
		// 로그인 객체가 존재한다면
		int empId = ((EmpDTO)session.getAttribute("loginData")).getEmpId();
		
		EmpDTO updateEmpData = new EmpDTO();
		updateEmpData.setEmpId(empId);	// 찾기위해서 id전달
		updateEmpData.setEmail(email);
		
		EmpDetailDTO updateEmpDetaileData = new EmpDetailDTO();
		updateEmpDetaileData.setEmpId(empId); // 따로따로 저장하기
		updateEmpDetaileData.setPhone(phone);
		
		boolean result = empService.setEmployee(updateEmpData, updateEmpDetaileData);
		
		if(result) {
			// 수정 완료 -> 로그아웃 시켜버린다. 이유 : 정보가 수정되면 session도 다 수정해줘야 하는데 너무 그러기엔 session이 너무 
			// 많아서 그냥 로그아웃했다가 다시 db에 저장된 정보를 가져오도록 하는 게 편함..ㅎ
			// 수정 성공한 경우에만 해당 파일을 변경시킨다. 
			
			Part part = request.getPart("uploadImage"); // multipart
			if(!part.getSubmittedFileName().isEmpty()) {	// 업로드한 파일이 없으면 
				String realPath = request.getServletContext().getRealPath("/static/img/emp/"); 
				part.write(realPath + empData.getEmpId() + ".png"); // 해당 경로 + 파일이름으로 저장해줘라
			}
			response.sendRedirect(request.getContextPath() + "/logout");
			session.invalidate();	// session은 초기화해준다.
		}else {
			// 수정 실패i
			doGet(request,response);
		}
	}

}
