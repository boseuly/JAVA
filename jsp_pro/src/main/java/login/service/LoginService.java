package login.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import emp.model.EmpDTO;
import login.model.LoginDAO;

public class LoginService {

	public boolean login(HttpSession session, String empId, String deptId, String empName) {
		LoginDAO dao = new LoginDAO();
		// 우리가 확인 해야 할 것 -> 해당 empId, deptId, empName 이 모두 같은 사람의 정보인지 확인
		// 단 empName의 경우 확인시 주의할 점 -> sql에서는 first_name, last_name 으로 나눠있음 
		// -> 그래서 이름을 " "으로 나눠서 하나는 first_name이랑, 하나는 last_name이랑 비교 해야 한다.
		String[] fullName = empName.split(" "); // " "으로 이름을 쪼갠다.
		
		Map<String, Object> loginMap = new HashMap<String, Object>();
		loginMap.put("empId", empId);
		loginMap.put("deptId", deptId);
		
		if(fullName.length == 2) { // 만약 두 개로 잘 쪼개진 경우
			loginMap.put("firstName", fullName[0]);
			loginMap.put("lastName", fullName[1]);
		}else {  // 사용자가 잘못 입력해서 제대로 쪼개지지 않은 경우 
			loginMap.put("firstName", "");
			loginMap.put("lastName", "");
		}
		
		EmpDTO data = dao.selectEmployee(loginMap); // 사용자가 입력한 정보의 직원이 존재하는지 확인
		dao.close();
		if(data == null ) { // 해당 직원이 없음 
			return false;
		}else { // 사용자가 입력한 조건에 맞는 직원이 존재한다면 -> 로그인 성공 -> session에 저장 
			session.setAttribute("loginData", data);
			return true;
		}
	}
	
}
