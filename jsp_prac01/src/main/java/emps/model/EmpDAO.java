package emps.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;

import common.util.AbstractDAO;

public class EmpDAO extends AbstractDAO{
	private String mapper = "empMapper.%s";
	
	public List<EmpDTO> selectAll(){
		String mapId = String.format(mapper, "selectAll");
		List<EmpDTO> datas = session.selectList(mapId);
		return datas;
	}

	public List<EmpDTO> selectPage(int offset, int count) {
		String mapId = String.format(mapper, "selectAll");
		RowBounds rb = new RowBounds(offset, count);
		
		Cursor<EmpDTO> cursor = session.selectCursor(mapId, null, rb);
		Iterator<EmpDTO> it = cursor.iterator();
		List<EmpDTO> datas = new ArrayList<EmpDTO>();
		
		while(it.hasNext()) {
			datas.add(it.next());
		}
		return datas;
	}

	public int totalRow() {
		String mapId = String.format(mapper, "totalRow");
		int rowCount = session.selectOne(mapId);
		return rowCount;
	}
}
