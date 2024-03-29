package emps.service;

import java.util.ArrayList;
import java.util.List;

import emps.model.EmpDAO;
import emps.model.EmpDTO;
import emps.model.EmpDetailDTO;

public class EmpService {

	public List<EmpDTO> getEmpAll() {
		EmpDAO dao = new EmpDAO();
		List<EmpDTO> datas = dao.selectAll();
		dao.close();
		return datas;
	}
	
	// 이전에는 Map 인터페이스를 사용하기도 했음
	public List<EmpDTO> getEmpPage(int page, int count) {
		EmpDAO dao = new EmpDAO();
		List<EmpDTO> datas = dao.selectPage((page -1) * count,((page -1) * count) + (count -1));	// 2-1 * 10 = 10(시작행)
		dao.close();							// 시작행과 총 몇행인지를 전달 
		return datas;
	}
	
	// 몇 페이지인지 구한다.
	public List<Integer> getPageList(int pageCount) {
		EmpDAO dao = new EmpDAO();
		
		List<Integer> pageList = new ArrayList<Integer>();
		int total = dao.totalRow();
		
		for(int num = 0; num <= (total - 1) / pageCount; num++) { //페이지번호가 1,2,3,4 ... 이렇게 추가될 것 
			pageList.add(num + 1);
		}
		
		return pageList;
	}


	public EmpDetailDTO getDetail(int empId) {
		EmpDAO dao = new EmpDAO();
		EmpDetailDTO data = dao.selectDetail(empId);
		dao.close();
		
		return data;
	}

	public boolean setEmployee(EmpDTO updateEmpData, EmpDetailDTO updateEmpDetaileData) {
		EmpDAO dao = new EmpDAO();
		// 사용자가 @emp.com 이라고 이메일 주소도 포함시켜 작성했다면 이걸 빼줘야 한다. 
		String email = updateEmpData.getEmail();
		if(email.contains("@emp.com")) {
			email = email.replace("@emp.com", "");
			updateEmpData.setEmail(email);
		}

		boolean res1 = dao.updateEmployee(updateEmpData);
		boolean res2 =dao.updateEmployeeDetail(updateEmpDetaileData);
		
		
		if(res1 && res2) {
			dao.commit();
			dao.close();
			return true;
		}
		dao.rollback();
		dao.close();
		return false;
	}
	
}
