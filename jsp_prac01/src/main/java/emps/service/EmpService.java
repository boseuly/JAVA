package emps.service;

import java.util.ArrayList;
import java.util.List;

import emps.model.EmpDAO;
import emps.model.EmpDTO;

public class EmpService {

	public List<EmpDTO> getEmpAll() {
		EmpDAO dao = new EmpDAO();
		List<EmpDTO> datas = dao.selectAll();
		return datas;
	}
	// 한 페이지에 들어갈 데이터 가져오기 
	public List<EmpDTO> getEmpPage(int page, int count) {
		EmpDAO dao = new EmpDAO();
		List<EmpDTO> datas = dao.selectPage(page, count);
		dao.close();
		return datas;
	}
	public List<Integer> getPageList(int pageCount) {
		EmpDAO dao = new EmpDAO();
		List<Integer> pageList = new ArrayList<Integer>();
		int total = dao.totalRow();
		
		for(int num = 0; num <= (total -1) / pageCount; num++) {
			pageList.add(num + 1);
		}
		dao.close();
		return pageList;
	}
	
}
