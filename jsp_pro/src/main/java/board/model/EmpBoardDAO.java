package board.model;


import org.apache.ibatis.session.SqlSession;

import conn.db.DBConn;

public class EmpBoardDAO {
	private SqlSession session;
	
	public EmpBoardDAO() {
		this.session = DBConn.getSqlSession();
	}
	
	public boolean insertData(EmpBoardDTO data) {
		int result = 0;
		if(data.getId() == 0) {
			result = session.insert("empBoardsMapper.insertDataAutoSeq", data);
		}else {
			result = session.insert("empBoardsMapper.insertData", data);
		}
		return result == 1 ? true : false;
	}
	
	public int getNextSeq() {
		int result = session.selectOne("empBoardsMapper.getNextSeq");
		return result;
	}
	
	public EmpBoardDTO selectData(int id) {
		EmpBoardDTO data = session.selectOne("empBoardsMapper.selectData", id);
		
		return data;
	}
	
	public void commit() {
		this.session.commit();
	}
	public void rollback() {
		this.session.rollback();
	}
	public void close() {
		this.session.close();
	}

	public boolean updateViewCnt(EmpBoardDTO data) {
		int result = session.update("empBoardsMapper.updateViewCnt", data);
		return result == 1 ? true : false;
	}

	public boolean updateLike(EmpBoardDTO data) {
		int result = session.update("empBoardsMapper.updateLike", data);
		return result == 1 ? true : false;
	}
	// 방문 내역이 있는지 확인
	public EmpBoardStatisDTO selectStatis(EmpBoardDTO data) {
		EmpBoardStatisDTO result = session.selectOne("empBoardsMapper.selectStatis", data);
		return result;
	}

	// 방문 기록
	public boolean insertStatis(EmpBoardDTO data) {
		int result = session.insert("empBoardsMapper.insertStatis", data); // 방문 기록을 추가해주기
		return result == 1 ? true : false;
	}

	// 7일 후 방문한 기록 수정 
	public boolean updateStatis(EmpBoardStatisDTO statisData) {
		int result = session.update("empBoardsMapper.updateStatis", statisData); // 방문 기록을 추가해주기
		return result == 1 ? true : false;
		
	}


	
}
