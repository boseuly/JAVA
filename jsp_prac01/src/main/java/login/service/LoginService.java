package login.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import emps.model.EmpDTO;
import login.model.LoginDAO;

public class LoginService {
	

	public boolean login(HttpSession session, String empId, String deptId, String empName) {
		LoginDAO dao = new LoginDAO();
		String fullName[] = empName.split(" "); 
		
		Map<String, Object> loginMap = new HashMap<String, Object>();
		loginMap.put("empId", empId);
		loginMap.put("deptId", deptId);
		
		if(fullName.length == 2) {	// 만약 공백으로 잘 쪼개졌다면
			loginMap.put("firstName",fullName[0]);
			loginMap.put("lastName", fullName[1]);
		}else {
			loginMap.put("firstName", "");
			loginMap.put("lastName", "");
		}
		
		EmpDTO data = dao.selectEmployee(loginMap);
		dao.close();
		if(data == null) {
			// 직원이 없음
			return false;
		}else {
			// 직원이 있음
			session.setAttribute("loginData", data);
			return true;
			
		}
	}

}
