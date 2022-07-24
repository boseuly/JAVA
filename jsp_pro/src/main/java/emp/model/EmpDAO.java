package emp.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;

import common.util.AbstractDAO;

public class EmpDAO extends AbstractDAO {

	
	public List<EmpDTO> searchPage(int offset, int count) {
		RowBounds rb = new RowBounds(offset, count);
		Cursor<EmpDTO> cursor = session.selectCursor("empMapper.selectEmpAll", null, rb);
		
		List<EmpDTO> datas = new ArrayList<EmpDTO>();
		Iterator<EmpDTO> iter = cursor.iterator();
		
		while(iter.hasNext()) {
			datas.add(iter.next());
		}
		return datas;
	}

	public int totalRow() {
		int totalRow = session.selectOne("empMapper.totalRow");
		
		return totalRow;
	}

	public EmpDTO getId(int empId) {
		EmpDTO data = new EmpDTO();
		data = session.selectOne("empMapper.selectId", empId);
		return data;
	}

	public EmpDetailDTO getDetail(int empId) {
		// 아이디를 정보가져오기
		EmpDetailDTO data = session.selectOne("empMapper.selectDetail", empId); // 해당 아이디 정보 
		
		return data;
	}

	public boolean updateEmployee(EmpDTO updateEmpData) {
		int result = session.update("empMapper.updateEmployee", updateEmpData);
		return result == 1? true : false;
	}

	public boolean updateEmployeeDetail(EmpDetailDTO updateEmpDetailData) {
		int result = session.update("empMapper.updateEmployeeDetail", updateEmpDetailData);
		
		return result == 1 ? true : false;
	}

}
