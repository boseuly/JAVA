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
