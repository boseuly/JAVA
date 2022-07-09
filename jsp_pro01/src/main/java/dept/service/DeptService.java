package dept.service;

import java.util.List;

import dept.model.DeptDAO;
import dept.model.DeptDTO;

public class DeptService {
	// 여러 개의 부서 정보를 가지고 올 거니까 list를 사용하는 게 좋다.
		private DeptDAO dao; // 여기에 요청할 거임
//		
//		public DeptService () { -> 이렇게 하면 세션이 다른 경우 변경 내용이 적용 안 됨
//			dao = new DeptDAO(); 
//		}
		
		// 요청 메소드
		public List<DeptDTO>  getAll() {
			dao = new DeptDAO();
			List<DeptDTO> datas = dao.searchAll();	// dao 클래스에 search 요청
			dao.close();
			return datas;
		}
		
		public DeptDTO getId(String id) {
				return _getId(Integer.parseInt(id));
		}
		// 위에처럼 문자열로 전달해도 되고 int형으로 전달해도 되고
		public DeptDTO getId(int id) {
			return _getId(id);
		}
		
		public DeptDTO getId(DeptDTO deptDto) {
			return _getId(deptDto.getDeptId());
		}
		// 위에 중복되는 코드를 하나로 묶어버림
		private DeptDTO _getId(int id) {
			dao = new DeptDAO();
			
			DeptDTO data = dao.searchId(id);
			dao.close();
			return data;
		}
		//service에서는 전달 받은 데이터를 객체에 담아서 전달하기  ->  dao에서 작업하기 쉽게
		// 서비스에서 전달 받은 데이터를 가공
		public DeptDTO addDept(String deptId, String deptName, String mngId, String locationId) {
			dao = new DeptDAO();
			
			DeptDTO deptDto = null;
			if(deptId.matches("//d+") && mngId.matches("//d+") && locationId.matches("//d+")) {
				deptDto = new DeptDTO();
				deptDto.setDeptId(Integer.parseInt(deptId));
				deptDto.setDeptName(deptName);
				deptDto.setMngId(Integer.parseInt(mngId));
				deptDto.setLocationId(Integer.parseInt(locationId));
				
				
				if(dao.searchId(deptDto.getDeptId()) != null) { // 유효성검사
					deptDto.setDeptId(-1); 			// 이미 해당 아이디가 존재한다면
					dao.close();
					return deptDto; // 만약 제약조건에 위배된다면 detpId를 -1로 설정해서 반환
				}
				
				if(!dao.existManager(deptDto.getMngId())) { // FK -> 참조할 객체가 없으면
					deptDto.setMngId(-1);	
					dao.close();
					return deptDto; 
				}
				
				if(!dao.existLocation(deptDto.getLocationId())) { // FK -> 참조할 객체가 없으면
					deptDto.setLocationId(-1);
					dao.close();
					return deptDto;
				}
				
				boolean isSaved = dao.insertDept(deptDto);
				if(!isSaved) { // 저장 실패
					dao.close();
					return null;
				}
			}
			dao.close();
			return deptDto;
		}

		// 수정 -> 외래키가 있는지 없는지 확인해줘야 한다.
		public int modifyDept(DeptDTO data) {
			dao = new DeptDAO();
			if(!dao.existManager(data.getMngId())) { // FK -> 참조할 객체가 없으면
				dao.close();
				return -1; // mngId 참조 위배
			}
			
			if(!dao.existLocation(data.getLocationId())) { // FK -> 참조할 객체가 없으면
				dao.close();
				return -2;	// location 참조 위배
			}
			boolean isSaved = dao.updateDept(data);
			dao.close();
			if(isSaved) { // save에 성공 
				return 1;
			}
			return 0; // save 실패
		}
}
