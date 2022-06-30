package dept.service;

import java.util.List;

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
				dao.close();
				return deptDto;
			}
			
			if(!dao.existManager(deptDto.getMngId())) {
				deptDto.setMngId(-1);
				dao.close();
				return deptDto;
			}
			
			if(!dao.existLocation(deptDto.getLocId())) {
				deptDto.setLocId(-1);
				dao.close();
				return deptDto;
			}
			
			boolean isSaved = dao.insertDept(deptDto);
			
			if(!isSaved) {
				dao.close();
				return null;
			}
		}
		
		dao.close();
		return deptDto;
	}

	public int modifyDept(DeptDTO data) {
		dao = new DeptDAO();
		
		if(!dao.existManager(data.getMngId())) {
			dao.close();
			return -1;
		}
		
		if(!dao.existLocation(data.getLocId())) {
			dao.close();
			return -2;
		}
		
		boolean isSaved = dao.updateDept(data);
		dao.close();
		
		if(isSaved) {
			return 1;
		}
		return 0;
	}
	
}
