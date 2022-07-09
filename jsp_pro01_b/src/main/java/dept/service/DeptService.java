package dept.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dept.model.DeptDAO;
import dept.model.DeptDTO;

public class DeptService {
	private DeptDAO dao;
	
	public List<DeptDTO> getAll() {
		dao = new DeptDAO();
		
		List<DeptDTO> datas = dao.searchAll();
		
		dao.close();
		return datas;
	}

	// mybatis를 이용한 페이징
	public List<Integer> getPageList(int pageCount) {	// pageCount 로 나눠서 몇 페이지인지 알아내야 한다.
		dao = new DeptDAO();
		
		List<Integer> pageList = new ArrayList<Integer>();
		int total = dao.totalRow();
		
		for(int num = 0; num <= (total - 1) / pageCount; num++) {
			pageList.add(num + 1);
		}
		
		return pageList;
	}
	public List<DeptDTO> getPage(int page, int pageCount) {
		int pageNumber = page;
		int start, end;
		start = (pageNumber - 1) * pageCount;
		end = pageCount;
		dao = new DeptDAO();
		List<DeptDTO> datas = dao.searchPage(start, end);
		dao.close();
		return datas;
	}
	// mybatis를 이용한 페이징
	public List<DeptDTO> getPage(String pageNumber){
		int start, end;
		int number = Integer.parseInt(pageNumber); 
		start = (number - 1) * 10;
		end = 10;
		dao = new DeptDAO();
		List<DeptDTO> datas = dao.searchPage(start, end);
		dao.close();
		return datas;
	}	
	// 이전방식 
//	public List<DeptDTO> getPage(int pageNumber){ // 두번째 페이지를 요청하면 그에 해당하는 데이터 전달
//		dao = new DeptDAO();
//		Map<String, Integer> page = new HashMap<String, Integer>();
//		page.put("start", (pageNumber -1) * 10 + 1);
//		page.put("end", (pageNumber -1) * 10 + 10);
//		
//		List<DeptDTO> datas = dao.searchPage(page);
//		dao.close();
//		return datas;
//		
//	}
	
	public List<Integer> getPageList(){
		dao = new DeptDAO();
		
		List<Integer> pageList = new ArrayList<Integer>();
		int total = dao.totalRow(); // 전체 몇 개의 행인지 찾기
		
		// 전체 행 수를 가지고 페이지 목록을 만든 거다.
		for(int num = 0; num <= (total -1) / 10; num++) { // 기본이 10개라서 10이라고 한 거임
			pageList.add(num + 1);
		}
		return pageList;
	}
	
	public DeptDTO getId(String id) {
		return _getId(Integer.parseInt(id));
	}
	
	public DeptDTO getId(int id) {
		return _getId(id);
	}
	
	public DeptDTO getId(DeptDTO deptDto) {
		return _getId(deptDto.getDeptId());
	}
	
	private DeptDTO _getId(int id) {
		dao = new DeptDAO();
		
		DeptDTO data = dao.searchId(id);
		
		dao.close();
		return data;
	}

	public DeptDTO addDept(String deptId, String deptName, String mngId, String locId) {
		dao = new DeptDAO();
		
		DeptDTO deptDto = null;
		if(deptId.matches("\\d+") && mngId.matches("\\d+") && locId.matches("\\d+")) {
			deptDto = new DeptDTO();
			deptDto.setDeptId(Integer.parseInt(deptId));
			deptDto.setDeptName(deptName);
			deptDto.setMngId(Integer.parseInt(mngId));
			deptDto.setLocId(Integer.parseInt(locId));
			
			if(dao.searchId(deptDto.getDeptId()) != null) { 
				deptDto.setDeptId(-1);
				dao.rollback();
				dao.close();
				return deptDto;
			}
			
			if(!dao.existManager(deptDto.getMngId())) {
				deptDto.setMngId(-1);
				dao.rollback();
				dao.close();
				return deptDto;
			}
			
			if(!dao.existLocation(deptDto.getLocId())) {
				deptDto.setLocId(-1);
				dao.rollback();
				dao.close();
				return deptDto;
			}
			
			boolean isSaved = dao.insertDept(deptDto);
			
			if(!isSaved) {
				dao.rollback();
				dao.close();
				return null;
			}
		}
		
		dao.commit();
		dao.close();
		return deptDto;
	}

	public int modifyDept(DeptDTO data) {
		dao = new DeptDAO();
		
		if(!dao.existManager(data.getMngId())) {
			dao.rollback();
			dao.close();
			return -1;
		}
		
		if(!dao.existLocation(data.getLocId())) {
			dao.rollback();
			dao.close();
			return -2;
		}
		
		boolean isSaved = dao.updateDept(data);
		
		if(isSaved) {
			dao.commit();
			dao.close();
			return 1;
		}
		dao.rollback();
		dao.close();
		return 0;
	}
	public int deleteDept(String id) {
		dao = new DeptDAO();
		if(dao.searchId(Integer.parseInt(id)) == null) { // 데이터가 존재하지 않으면 삭제할 데이터가 없음
			dao.rollback();
			dao.close();
			return -1;	// 삭제 대상이 없음을 알림
		}
		boolean result = dao.deleteDept(Integer.parseInt(id));
		
		if(result) {
			dao.commit();
			dao.close();
			return 1;
		}
		dao.rollback();
		dao.close();
		return 0; 	// 알 수 없는 오류
	}
	
}
