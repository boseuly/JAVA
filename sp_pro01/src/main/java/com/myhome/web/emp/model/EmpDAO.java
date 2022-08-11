package com.myhome.web.emp.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myhome.web.common.util.Paging;

@Repository
public class EmpDAO {
	
	@Autowired
	private SqlSession session;

	
	public void searchPage(Paging paging) { // Paging 객체를 가져오는 게 더 나을 듯
		RowBounds rb = new RowBounds(paging.getOffset(), paging.getLimit());
		Cursor<Object> cursor = session.selectCursor("empMapper.selectEmpAll", null, rb);
		paging.setPageDatas(cursor.iterator());
	}
	
	public int totalRow() {
		int totalRow = session.selectOne("empMapper.totalRow");
		
		return totalRow;
	}
/*

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
*/
	
	
}
