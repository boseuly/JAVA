package dept.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import conn.db.DBConn;

public class DeptDAO {

	SqlSession session = null;
	
	public DeptDAO() {
		session = DBConn.getSqlSession();
	}
	
	public List<DeptDTO> searchAll() {
		List<DeptDTO> datas = session.selectList("deptMapper.deptSelectAll");
		return datas;
	}
	// 페이징 방법 1
	public List<DeptDTO> searchPage(Map<String, Integer> page) {
		List<DeptDTO> datas = session.selectList("deptMapper.deptSelectPage", page);
		return datas;
	}
	// 페이징 방법 2 : MyBatis 사용하기
	public List<DeptDTO> searchPage(int start, int end) {
		RowBounds rb = new RowBounds(start, end);
		Cursor<DeptDTO> cursor = session.selectCursor("deptMapper.deptSelectAll", null, rb);
		
		List<DeptDTO> datas = new ArrayList<DeptDTO>();
		Iterator<DeptDTO> iter = cursor.iterator();
		while(iter.hasNext()) {
			datas.add(iter.next());
		}
		
		return datas;
	}
	
	// 페이징을 하기 위해서는 전체 행 수를 알아야 한다.
	public int totalRow() {
		int rowCount = session.selectOne("deptMapper.deptTotalRow");
		return rowCount;
	}
	
	public DeptDTO searchId(int id) {
		DeptDTO data = session.selectOne("deptMapper.deptSelectId", id);
		return data;
	}

	public boolean insertDept(DeptDTO deptDto) {
		int result = session.insert("deptMapper.deptInsert", deptDto);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public boolean existManager(int id) {
		int result = session.selectOne("deptMapper.existManager", id);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public boolean existLocation(int id) {
		int result = session.selectOne("deptMapper.existLocation", id);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public boolean updateDept(DeptDTO deptDto) {
		int result = session.update("deptMapper.deptUpdate", deptDto);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public boolean deleteDept(int id) {
		int result = session.delete("deptMapper.deptDelete", id);
		if(result == 1) {
			return true;
		}
		return false;
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
}
