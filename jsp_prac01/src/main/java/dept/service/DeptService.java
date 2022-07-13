package dept.service;

import java.util.ArrayList;
import java.util.List;

import dept.model.DeptDAO;
import dept.model.DeptDTO;

public class DeptService {
	private DeptDAO dao;
	/* 페이징 공식
	 [ ( page - 1) * count + 1 ] ~ [ (page -1) * count + count ]  
	 */
	
	// 한 페이지에 들어갈 내용들을 가져오는 것
	public List<DeptDTO> getPage(int page, int pageCount){	// pageCount : 몇 행씩 보여줄지
		dao = new DeptDAO();
		int pageNumber = page;	// page : 현재 몇 페이지인지
		int start, end;			// 시작페이지와 끝페이지
		start = (pageNumber - 1) * pageCount;	// 만약 현재 1페이지이면 0 * pageCount = 0 
		end = pageCount;		// 
		List<DeptDTO> datas = dao.searchPage(start, end);
		dao.close();
		return datas;
	}

	
	// 총 몇 페이지인지 알려준다. 
	public List<Integer> getPageList(int pageCount){
		dao = new DeptDAO();
		
		List<Integer> pageList = new ArrayList<Integer>();
		int total = dao.totalRow(); // 전체 행 수
		
		for(int num = 0; num <= (total - 1) / pageCount; num++) {	// pageCount = 한페이지에 들어가는 행 수  
			pageList.add(num + 1);			// 총 페이지 수에서 -1을 한 뒤 한 페이지에 넣을 
		}
		dao.close();
		return pageList;
	}
	
	
	// 모든 정보 조회
	public List<DeptDTO> getAll() {
		dao = new DeptDAO(); 
		List<DeptDTO> datas = dao.getAll(); // 모든 정보 가져오기

		dao.close();
		return datas; 
	}
	//특정 id만 조회
	public DeptDTO getId(String deptId) {
		dao = new DeptDAO();
		// id를 먼저 숫자로 변환해줘야 한다.
		int id = Integer.parseInt(deptId);
		DeptDTO data = dao.getId(id);
		
		dao.close();
		return data;
	}
	
	// dept 추가
	public int insertDept(String deptId, String deptName, String mngId, String locId) {
		// dao 에 전달하기 전에 확인해야 할 것 
		// 1. deptId, mngId, locId 가 숫자인지 확인
		if(deptId.matches("\\d+") && mngId.matches("\\d+") && locId.matches("\\d+")) {	// 만약 숫자가 맞다면
			// 숫자가 맞으면 해당 정보를 DTO에 저장해서 dao에 전달해야 한다. 
			dao = new DeptDAO();
			DeptDTO data = new DeptDTO();
			data.setDeptId(Integer.parseInt(deptId));	
			data.setDeptName(deptName);
			data.setMngId(Integer.parseInt(mngId)); 	
			data.setLocId(Integer.parseInt(locId));
			// deptId -> 기본키 (getId 사용)
			// locId -> location 테이블 참조
			// mngId는 employee 테이블을 참조하고 있음
			DeptDTO checkDeptId = dao.getId(Integer.parseInt(deptId));
			boolean checkLocId = dao.checkLocId(Integer.parseInt(locId));
			boolean checkMngId = dao.checkMngId(Integer.parseInt(mngId));
			if(!checkLocId) {			// 만약 locId 참조 위배
				dao.close();
				return -1;
			}
			if (!checkMngId) {	// 만약 mngId 참조 위배
				dao.close();
				return -2;
			}
			if(checkDeptId == null) {	// 만약 deptId 기본키 ok
				boolean result = dao.insertDept(data);
				if(result) {	// 만약 해당 데이터 추가가 성공
					dao.commit();
					dao.close();
					return 1;
				}
			}else {	// 만약 deptId 키본키 위배
				return -3;
			}
		}
		// 만약 숫자가 아니라면, 또는 저장이 안 된 경우 
		dao.rollback();
		dao.close();
		return 0;
	}
	
	// 데이터 수정
	public int deptMod(String deptId, String deptName, String mngId, String locId) {
		// 숫자인지 확인하기
		if(deptId.matches("\\d+") && locId.matches("\\d+") && mngId.matches("\\d+")){
			dao = new DeptDAO();
			// 만약 숫자가 맞다면 제약조건 확인	// deptId는 수정 불가라서 확인할 필요가 없음
			boolean checkMngId = dao.checkMngId(Integer.parseInt(mngId));
			boolean checkLocId = dao.checkLocId(Integer.parseInt(locId));
			
			if(!checkMngId) {	// 만약 mngId가 존재하지 않는다면
				dao.close();
				return -1;
			}else if(!checkLocId) {	// 만약 locId가 존재하지 않는다면
				dao.close();
				return -2;
			}
			
			DeptDTO data = new DeptDTO();
			data.setDeptId(Integer.parseInt(deptId));
			data.setDeptName(deptName);
			data.setLocId(Integer.parseInt(locId));
			data.setMngId(Integer.parseInt(mngId));
			
			boolean result = dao.updateDept(data);
			if(result) {
				dao.commit();
				dao.close();
				return 1;	// 데이터 업데이트 성공
			}
		}else {
			return -3;	// 숫자가 아닌 경우 
		}
		dao.rollback();
		dao.close();
		return 0; // 알 수 없는 오류로 저장이 안 됐다면
	}
	public boolean deptDel(String deptId, String deptName, String mngId, String locId) {
		dao = new DeptDAO();
		DeptDTO data = new DeptDTO();
		data.setDeptId(Integer.parseInt(deptId));
		data.setDeptName(deptName);
		data.setMngId(Integer.parseInt(mngId));
		data.setLocId(Integer.parseInt(locId));
		
		
		boolean result = dao.deptDelete(data);
		if(result) {
			dao.commit();
			dao.close();
			return true;
		}
		dao.rollback();
		dao.close();
		return false;
	}
}
