package emps.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import common.util.AbstractDAO;
import conn.db.DBConn;

public class EmpDAO extends AbstractDAO {

	private String mapper = "empMapper.%s";
	
	public List<EmpDTO> selectAll() {
		String mapId = String.format(mapper, "selectAll");
		List<EmpDTO> datas = session.selectList(mapId);
		return datas;
	}

	public List<EmpDTO> selectPage(EmpDTO data, int offset, int count) {
		String mapId = String.format(mapper, "selectAll");
		RowBounds rb = new RowBounds(offset, count);
		
		Cursor<EmpDTO> cursor = session.selectCursor(mapId, data, rb); // 검색할 수 있는 조건이 들어가니까 null -> data 로 수정
		
		List<EmpDTO> datas = new ArrayList<EmpDTO>();
		Iterator<EmpDTO> iter = cursor.iterator();
		while(iter.hasNext()) {
			datas.add(iter.next());
		}
		
		return datas;
	}
	public boolean getId(int empId) {
		String mapId = String.format(mapper, "selectEmpId");
		int result = session.selectOne(mapId, empId);
		if(result >= 1) {
			return true; // 존재하면 안 됨 -> 기본키 위배 
		}
		return false;
	}

	public int totalRow(EmpDTO data) {
		String mapId = String.format(mapper, "totalRow");
		int rowCount = session.selectOne(mapId, data);
		return rowCount;
	}

	public EmpDetailDTO selectDetail(int empId) {
		String mapId = String.format(mapper, "selectDetail");
		EmpDetailDTO data = session.selectOne(mapId, empId);
		return data;
	}

	public boolean existjob(String jobName) {
		String mapId = String.format(mapper, "existJob");
		int result = session.selectOne(mapId, jobName);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public boolean existDept(String deptName) {
		String mapId = String.format(mapper, "existDept");
		int result = session.selectOne(mapId,deptName);
		if(result == 1) {
			return true;
		}
		return false;
	}


	public boolean existMngId(int mngId) { // mngId가 있는지 확인을 해야 한다. -> EMPLOYEES테이블의 EMPLOYEE_ID 를 참조 
		String mapId = String.format(mapper, "selectEmpId"); 
		int result = session.selectOne(mapId, mngId);
		
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	// 직원 추가
	public boolean addEmps(EmpAllDTO data) {
		String mapId = String.format(mapper, "insertEmps");
		int result = session.update(mapId, data);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public EmpDTO selectId(int empId) {
		String mapId = String.format(mapper, "selectId");
		EmpDTO data = session.selectOne(mapId, empId);
		return data;
	}

	public boolean insertEmployee(EmpDTO empData) {
		String mapId = String.format(mapper, "insertEmployee");
		int result = session.insert(mapId, empData);
		
		return result == 1 ? true : false;
	}

	public boolean updateEmployeeDetail(EmpDetailDTO empDetailData) {
		
		return false;
	}

	public boolean deleteId(int id) {
		String mapId = String.format(mapper, "deleteId");
		int result = session.delete(mapId, id);
		
		return result == 1 ? true : false;
	}
	

}
