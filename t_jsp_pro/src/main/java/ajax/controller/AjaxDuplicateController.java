package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;
import emps.model.EmpDTO;
import emps.service.EmpService;
import locs.model.LocsDTO;
import locs.service.LocsService;

@WebServlet("/ajax/duplicate")
public class AjaxDuplicateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DeptService deptService = new DeptService();   
	private LocsService LocsService = new LocsService();
	private EmpService empService = new EmpService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		String name = request.getParameter("name");	// 스크립트에서 보낸 데이터는 parameter로 받는다.
		String value = request.getParameter("value");
		
		String errCode = "\"code\": \"%s\"";
		String errMsg = "\"message\":\"%s\"";
		if(name.equals("deptId") && !value.isEmpty()) {
			
			DeptDTO data = deptService.getId(value);	// 데이터 정보 하나 가져옴

			if(data != null) {	// 데이터가 이미 존재하면 중복 에러 내보내기
				errCode= String.format(errCode, "error");
				errMsg = String.format(errMsg, "부서 ID가 중복되었습니다.");
			}else {
				errCode= String.format(errCode, "success");
				errMsg = String.format(errMsg, "사용 가능한 부서 ID입니다.");
			}
		}else if(name.equals("locId") && !value.isEmpty()) {
			LocsDTO data = LocsService.getId(value);
			// 만약 해당 data가 이미 있다면 -> 중복 o
			if(data != null) {
				errCode = String.format(errCode, "error");
				errMsg = String.format(errMsg, "지역 ID가 중복되었습니다.");
			}else {
				errCode = String.format(errCode, "success");
				errMsg = String.format(errMsg, "사용 가능한 지역 ID입니다.");
			}
		}else if(name.equals("empId") && !value.isEmpty()) {
			
			EmpDTO data = empService.getId(value);
			// 만약 해당 data가 이미 있다면 -> 중복 o
			if(data != null) {
				errCode = String.format(errCode, "error");
				errMsg = String.format(errMsg, "직원 ID가 중복되었습니다.");
			}else {
				errCode = String.format(errCode, "success");
				errMsg = String.format(errMsg, "사용 가능한 직원 ID입니다.");
			}
		}
		PrintWriter out = response.getWriter();
		out.println("{");
		out.println(errCode + ",");	// 에러 코드와 에러 메시지를 json형식으로 보내준다. 
		out.println(errMsg);
		out.println("}");
			
	}


}
