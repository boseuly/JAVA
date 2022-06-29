package dept.service;

import java.util.List;

import dept.model.DeptDAO;
import dept.model.DeptDTO;

public class DeptService {
	// 여러 개의 부서 정보를 가지고 올 거니까 list를 사용하는 게 좋다.
		private DeptDAO dao; // 여기에 요청할 거임
		
		public DeptService () {
			dao = new DeptDAO();
		}
		
		// 요청 메소드
		public List<DeptDTO>  getAll() {
			List<DeptDTO> datas = dao.searchAll();	// dao 클래스에 search 요청
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
			DeptDTO data = dao.searchId(id);
			return data;
		}
}
