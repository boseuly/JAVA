package emps.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;

import common.util.AbstractDAO;

public class EmpDAO extends AbstractDAO{
	private String mapper = "empMapper.%s";	// 변수에 저장시켜준다. 고정값이라
	
	public List<EmpDTO> selectAll() {
		String mapId = String.format(mapper, "selectAll");
		List<EmpDTO> datas = session.selectList(mapId);
		return datas;
	}

	public List<EmpDTO> selectPage(int offset, int count) {
		String mapId = String.format(mapper, "selectAll");
		// RowBounds rb = new RowBounds(시작Row, 총 Row수)
		RowBounds rb = new RowBounds(offset, count);
		
		Cursor<EmpDTO> cursor = session.selectCursor(mapId, null, rb);
		
		List<EmpDTO> datas = new ArrayList<EmpDTO>();
		Iterator<EmpDTO> iter = cursor.iterator();
		while(iter.hasNext()) {
			datas.add(iter.next());	
		}
		return datas;
	}

	public int totalRow() {
		String mapId = String.format(mapper, "totalRow");
		int rowCount = session.selectOne(mapId);
		return rowCount;
	}
	
	public EmpDetailDTO selectDetail(int empId) {
		String mapId = String.format(mapper, "selectDetail");
		EmpDetailDTO data = session.selectOne(mapId, empId);
		return data;
	}

	// 해당 정보의 객체가 따로 
	public boolean updateEmployee(EmpDTO updateEmpData) {
		String mapId = String.format(mapper, "updateEmployee");
		int result = session.update(mapId, updateEmpData);
		
		return result == 1 ? true : false;
	}

	public boolean updateEmployeeDetail(EmpDetailDTO updateEmpDetaileData) {
		String mapId = String.format(mapper, "updateEmployeeDetail");
		int result = session.update(mapId, updateEmpDetaileData);
		return result == 1 ? true : false;
	}
	

	
}
