package dept.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import conn.db.DBConn;

public class DeptDAO {
	SqlSession session = null;
	
	public DeptDAO(){
		session = DBConn.getSqlSession();
	}
	
	public List<DeptDTO> getAll(){
		List<DeptDTO> datas = session.selectList("deptMapper.deptSelectAll");
		return datas;
	}

	public DeptDTO getId(int id) {
		DeptDTO data = session.selectOne("deptMapper.deptSelectId", id);
		return data;
	}

	public void close() {
		session.close();
	}
	public void commit() {
		session.commit();
	}
	public void rollback() {
		session.rollback();
	}

	// dept 추가
	public boolean insertDept(DeptDTO data) {
		int result = session.insert("deptMapper.insertDept", data);
		if(result == 1) {	// 성공적으로 추가됨
			return true;
		}
		return false;
	}
	// location 테이블 확인(locId)
	public boolean checkLocId(int locId) {
		int result = session.selectOne("deptMapper.checkLocId", locId);
		if(result == 1) {	// locId가 location테이블에 존재하면 true
			return true;
		}
		return false;
	}
	
	// employee 테이블 확인(employee_id - mngId )
	public boolean checkMngId(int mngId) {	
		int result = session.selectOne("deptMapper.checkMngId", mngId);
		if(result == 1) {	// mngId가 employees 테이블에 존재하면 true
			return true;
		}
		return false;
	}
	// 데이터 수정
	public boolean updateDept(DeptDTO data) {
		int result = session.update("deptMapper.updateDept", data);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public boolean deptDelete(DeptDTO data) {
		int result = session.delete("deptMapper.deleteDept",data);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
}
