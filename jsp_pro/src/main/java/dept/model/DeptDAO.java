package dept.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;

import common.util.AbstractDAO;

public class DeptDAO extends AbstractDAO {
	private String mapper = "deptMapper.%s";
	
	public List<DeptDTO> getAll() {
		String mapId = String.format(mapper, "selectDeptAll");
		
		List<DeptDTO> datas = session.selectList(mapId);
		return datas;
	}

	public DeptDTO getId(int deptId) {
		String mapId = String.format(mapper, "selectDeptId");
		
		DeptDTO data = session.selectOne(mapId, deptId);
		return data;
	}

	public List<DeptDTO> searchPage(int start, int end) {
		String mapId = String.format(mapper, "selectDeptAll");
		RowBounds rb = new RowBounds(start, end); // start 부터 end 만큼 가져온다.
		Cursor<DeptDTO> cursor = session.selectCursor(mapId, null , rb);
		// List 대신 Cursor 를 사용해주면 된다.
		
		
		List<DeptDTO> datas = new ArrayList<DeptDTO>();
		Iterator<DeptDTO> iter = cursor.iterator(); // cursor에 불러운 정보를 저장하고 cursor를 이용하여 Iterator 객체를 만든다.
		while(iter.hasNext()) {
			datas.add(iter.next()); // cursor객체의 데이터가 담긴 iter의 내부를 순환하면서 안에 있는 데이터를 datas에 하나씩 추가해준다.
		}
		return datas;
	}

	public int totalRow() {
		
		int rowCount = session.selectOne("deptMapper.deptTotalRow");
		return rowCount;
	}

	// mng 제약조건
	public boolean existMngId(int mngId) {
		// EMPLOYEES테이블 - EMPLOYEE_ID에 해당 mngId가 존재하는지
		String mapId = String.format(mapper,"existMngId");
		int result = session.selectOne(mapId,mngId);
		if (result == 1) {
			return true;
		}
		return false;
	}
	public boolean existLocId(int locId) {
		// EMPLOYEES테이블 - EMPLOYEE_ID에 해당 mngId가 존재하는지
		String mapId = String.format(mapper,"existLocId");
		int result = session.selectOne(mapId,locId);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean deptAdd(DeptDTO data) {
		String mapId= String.format(mapper, "insertDept");
		int result = session.insert(mapId, data);
		return result == 1 ? true : false;
	}

	public boolean deptMod(DeptDTO data) {
		String mapId= String.format(mapper, "updateDept");
		int result = session.update(mapId, data);
		return result == 1 ? true : false;
	}

	public boolean deptDel(int deptId) {
		String mapId = String.format(mapper, "deleteDept");
		int result = session.delete(mapId, deptId);
		return result == 1 ? true : false;
		
	}

}
