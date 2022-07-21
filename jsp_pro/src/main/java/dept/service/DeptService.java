package dept.service;

import java.util.List;

import dept.model.DeptDAO;
import dept.model.DeptDTO;

public class DeptService {

	private DeptDAO dao = null;
	
	public List<DeptDTO> getAll() {
		dao = new DeptDAO();
		List<DeptDTO> datas = dao.getAll();
		dao.close();
		return datas;
	}

	public DeptDTO getId(String deptId) {
		if(!(deptId.matches("\\d+"))) { // 만약 숫자형이 아니라면
			return null;
		}
		dao = new DeptDAO();
		DeptDTO data = dao.getId(Integer.parseInt(deptId));
		dao.close();
		return data;
	}

	public List<DeptDTO> getPage(int page, int pageCount) {
		// 몇 페이지부터 몇 페이지인지 구하는 게 우선이다. start, end 변수 필요
		int pageNumber = page;
		int start, end;
		start = (pageNumber -1) * pageCount;
		end = pageCount; // cursor를 사용하려면 row 수를 전달해야 함. 시작 row 부터 몇 개의 row를 가져올지 cursor가 알아서 가져온다. 
		DeptDAO dao = new DeptDAO();
		List<DeptDTO> datas = dao.searchPage(start, end);
		dao.close();
		return datas;
	}

}
