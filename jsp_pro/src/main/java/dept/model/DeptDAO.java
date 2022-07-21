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

}
