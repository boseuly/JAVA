package emps.service;

import java.util.ArrayList;
import java.util.List;

import emps.model.EmpDAO;
import emps.model.EmpDTO;

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
	
}
