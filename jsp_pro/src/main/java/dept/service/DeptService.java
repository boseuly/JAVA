package dept.service;

import java.util.ArrayList;
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

	public List<Integer> getPageList(int pageCount) {
		dao = new DeptDAO();
		
		List<Integer> pageList = new ArrayList<Integer>();
		int total = dao.totalRow();
		
		for(int num = 0; num <= (total - 1) / pageCount; num++) {
			pageList.add(num + 1); // 인덱스는 0부터 시작하는데 pageList는 1부터 시작해야 하기 때문에 +1을 해줌
		}
		dao.close();
		return pageList;
	}

	public DeptDTO deptAdd(String deptId, String deptName, String mngId, String locId) {
		dao = new DeptDAO();
		DeptDTO data = new DeptDTO(); 
		data.setDeptId(deptId);
		data.setDeptName(deptName);
		data.setLocId(locId);
		data.setMngId(mngId);
		
		boolean isNumber = deptId.matches("\\d+") &&  mngId.matches("\\d+") && locId.matches("\\d+");
		boolean mngExist = dao.existMngId(Integer.parseInt(mngId));
		boolean locExist = dao.existLocId(Integer.parseInt(locId));
		boolean save = dao.deptAdd(data);
		
		// 숫자 저장을 먼저 하면 return 할 때 data 정보가 맞지
		
		if(!isNumber) { // 만약 해당 id 가 존재하지 않는다면
			data.setDeptId(-1);
		}
		if(!mngExist) {
			data.setMngId(-1);
		}
		if(!locExist) {	// 존재하지 않는 지역 id
			data.setLocId(-1);
		}
		// 만약 저장에 성공했다면 
		if(save) {
			dao.commit();
			dao.close();
			return data; 
		}
		dao.rollback();
		dao.close();
		return null; // 알 수 없는 오류
	}
	
	// locId가 있는지 확인
	public boolean existLocId(int locId) {
		dao = new DeptDAO();
		boolean result = dao.existLocId(locId);
		dao.close();
		return result ? true : false; // result 가 참이면 true
	}
	// mngId가 있는지 확인
	public boolean existMngId(int mngId) {
		dao = new DeptDAO();
		boolean result = dao.existMngId(mngId);
		
		dao.close();
		return result ? true : false;
	}

	public DeptDTO deptMod(String deptId, String deptName, String mngId, String locId) {
		// locId, mngId 검사해야 한다.
		dao = new DeptDAO();
		DeptDTO data = new DeptDTO();
		data.setDeptId(deptId);
		data.setDeptName(deptName);
		data.setMngId(mngId);
		data.setLocId(locId);
		
		
		boolean checkLoc = dao.existLocId(Integer.parseInt(locId));
		boolean checkMng = dao.existMngId(Integer.parseInt(mngId));
		boolean save = dao.deptMod(data);
		
		if(!checkLoc) { // 만약 거짓이면 해당 locId 가 없는 거임
			data.setLocId(-1);
		}
		if(!checkMng) {
			data.setMngId(-1);
		}
		if(!save) {
			dao.rollback();
			data.setDeptId(-1); // 알 수 없는 오류
		}else {
			dao.commit();
		}
		
		dao.close();
		return data;
	}

	public boolean deptDel(String deptId) {
		dao = new DeptDAO();
		
		boolean result = dao.deptDel(Integer.parseInt(deptId));
		if(result) { // 삭제 성공
			dao.commit();
			dao.close();
			return true;
		}else {
			dao.rollback();
			dao.close();
			return false;
		}
	}

}
