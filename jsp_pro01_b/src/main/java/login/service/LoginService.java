package login.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import emps.model.EmpDTO;
import login.model.LoginDAO;

public class LoginService {
	public boolean login(HttpSession session, String empId, String deptId, String empName) {
		LoginDAO dao = new LoginDAO();
		String fullName[] = empName.split(" ");	// 사용자가 실수로 공백을 구분자로 입력하지 않았을 수도 있으니까
		Map<String, Object> loginMap = new HashMap<String , Object>();
		loginMap.put("emp", empId);
		loginMap.put("deptId", empId);
		
		// 공백을 구분자로 입력을 해야 하는데 이름을 붙여서 입력할 수도 있으니까
		// 만약 fullName 배열이 " "공백으로 나눴을 때 제대로 2개가 나오면
		if(fullName.length == 2) {	 
			loginMap.put("firstName", empName.split(" ")[0]); // map으로 한 이유 : 우리가 받을 수 있는 건 selectOne이기 때문에
			loginMap.put("lastName", empName.split(" ")[1]); // 하나의 결과값인데 우리가 전달하려는 값은 여러개이기 때문에 map을 통해 하나로 만들어서 전달을 해줘야 한다. 
		}else {
			// 만약 fullName 배열이 공백없이 작성되어서 배열이 1개가 나오면
			loginMap.put("firstName", "");
			loginMap.put("lastName", "");
		}
		
		
		EmpDTO data = dao.selectEmployee(loginMap);
		dao.close();
		if(data == null) {	
			return false;
		}else {
			session.setAttribute("loginData", data); // 로그인 정보를 data에 담기
			return true;
		}
	}
}
