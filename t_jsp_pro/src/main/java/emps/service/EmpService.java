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

	public List<EmpDTO> getEmpPage(int page, int count) {
		EmpDAO dao = new EmpDAO();
		List<EmpDTO> datas = dao.selectPage((page - 1) * count, count);
		dao.close();
		return datas;
	}

	public List<Integer> getPageList(int pageCount) {
		EmpDAO dao = new EmpDAO();
		List<Integer> pageList = new ArrayList<Integer>();
		
		int total = dao.totalRow();
		
		for(int num = 0; num <= (total - 1) / pageCount; num++) {
			pageList.add(num + 1);
		}
		
		dao.close();
		return pageList;
	}

	public EmpDetailDTO getDetail(int empId) {
		EmpDAO dao = new EmpDAO();
		EmpDetailDTO data = dao.selectDetail(empId);
		dao.close();
		return data;
	}

}
