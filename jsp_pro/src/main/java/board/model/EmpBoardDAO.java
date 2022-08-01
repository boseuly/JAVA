package board.model;


import java.util.List;

import javax.servlet.http.HttpSession;

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
	
	public boolean updateViewCnt(EmpBoardDTO data) {
		int result = session.update("empBoardsMapper.updateViewCnt", data);
		return result == 1 ? true : false;
	}

	public boolean updateLike(EmpBoardDTO data) {
		int result = session.update("empBoardsMapper.updateLike", data);
		return result == 1 ? true : false;
	}
	// 방문 내역이 있는지 확인
	public EmpBoardStatisDTO selectStatis(EmpBoardStatisDTO data) {
		EmpBoardStatisDTO result = session.selectOne("empBoardsMapper.selectStatis", data);
		return result;
	}

	// 방문 기록
	public boolean insertStatis(EmpBoardStatisDTO data) {
		int result = session.insert("empBoardsMapper.insertStatis", data); // 방문 기록을 추가해주기
		return result == 1 ? true : false;
	}

	// 7일 후 방문한 기록 수정 
	public boolean updateStatis(EmpBoardStatisDTO data) {
		int result = session.update("empBoardsMapper.updateStatis", data); // 방문 기록을 추가해주기
		return result == 1 ? true : false;
		
	}
	// like 수정
	public boolean updateStatis(EmpBoardStatisDTO data, String type) {
		if(type.equals("like")) {
			int result = session.update("empBoardsMapper.updateLikeStatis", data);
			return result == 1 ? true : false;
		}else {
			return updateStatis(data);
		}
		
	}

	// 게시글 전부 가져오기
	public List<EmpBoardDTO> selectBoardAll() {
		List<EmpBoardDTO> datas = session.selectList("empBoardsMapper.selectBoardAll");
		return datas;
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

	
}
