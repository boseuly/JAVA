package dept.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import conn.db.DBConn;
// mybatis 커넥션 객체를 사용하여 실질적인 조회를 한다.
// mybatis의 main에서의 코드가 DeptDAO 클래스 안에서 작성된다.
public class DeptDAO {
		SqlSession session = null;
		
		public DeptDAO() {
			session = DBConn.getSqlSession();
		}
		
		public List<DeptDTO> searchAll () {
				List<DeptDTO> datas = session.selectList("deptMapper.deptSelectAll");
				return datas;
		}
		public DeptDTO searchId(int id) {
			DeptDTO data = session.selectOne("deptMapper.deptSelectId", id);
			return data;
		}

		public boolean insertDept(DeptDTO deptDto) {
			int result = session.insert("deptMapper.deptInsert", deptDto);
			if(result == 1) {
				session.commit();
				return true; 
			}
			session.rollback();
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
				session.commit();
				return true;
			}
			session.rollback();
			return false;
		}
		public void close() {
			session.close();
		}
}
