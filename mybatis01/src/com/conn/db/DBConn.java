package com.conn.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.data.vo.EmpComplexVO;
import com.data.vo.TestVO;

public class DBConn {
	//  mybatis를 사용할 수 있도록 만드는 구문 
	public static SqlSession getSqlSession() {
		SqlSession sess = null;
		String config = "resources/mybatis-config.xml";  // mybatis-config에 대한 경로 설정 -> 경로만 매치시켜줄 수 있으면 이름, 파일 위치 상관 없음 단, 경로를 잘 지정해야 한다.
		try {
			// inputstream 으로 읽어들이기
			InputStream is = Resources.getResourceAsStream(config);
			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);  // sessionFactoryBuilder 를 통해 SessionFactory를 만들기
			sess = ssf.openSession(false);  // sessionFactory 로 Session을 생성 // 자동 커밋이 되도록 하려면 true로 해줘야 한다.
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return sess;
	}
	public static void main(String[] args) {
		// 기본
		SqlSession session = DBConn.getSqlSession();
//		String result = session.selectOne("testMapper.test"); // 여기에 mapper.xml에 있는 태그의 namespace.id 를 적어주면 된다. 내가 실행하고자 하는 태그의 id를 넣어주는 것
//		System.out.println(result);
		
		// 검색결과가 여러개인 경우에는 selectList 사용하기
//		SqlSession session = DBConn.getSqlSession();
//		List<Object> result = session.selectList("testMapper.test");
//		System.out.println(result);
		
//		SqlSession session = DBConn.getSqlSession();
//		Map<String , Integer> data = new HashMap<String, Integer>(); //파라미터값으로 넣어줄 data를 map 타입으로 만들어준다. 
//		data.put("id1", 100);	// data에 키와 값을 넣어준다.
//		data.put("id2", 110);
//		List<EmpVO> result = session.selectList("testMapper.employee", data);  // 여러개가 나왔는데 selectOne을 해주면 에러가 뜬다. // 뒤에 숫자는 파라미터를 전달하는 거임 
//		for(EmpVO d : result) {
//			System.out.println(d.getEmployee_id() + ", " + d.getFirst_name());
//		}
//			
//		List<EmpVO> result = session.selectList("testMapper.employee");
//		System.out.println(result.get(0).getEmployee_id() + ", " + result.get(0).getFirst_name()); // 배열(selectList) 아니면 이거 필요 없음
		
//		int res1 = session.selectOne("testMapper.test1");
//		System.out.println(res1);
//		
//		String res2 = session.selectOne("testMapper.test2");
//		System.out.println(res2);
//		
//		List<Map<String, Object>> res3 = session.selectList("testMapper.test3");
//		for(Map<String, Object> data1: res3) {
//			System.out.println(data1.get("EMPLOYEE_ID") + ", " + data1.get("FIRST_NAME"));
//		}
		
		// 별칭 사용하기
//		List<Map<String, Object>> res4 = session.selectList("testMapper.test4");
//		for(Map<String, Object> data: res4) {
//			System.out.println(data.getEmpId() + ", " + data.getfName());
//		}
		// resultMap 을 사용한경우 
//		List<EmpVO> res5 = session.selectList("testMapper.test5");
//		for(EmpVO data:res5) {
//			System.out.println(data.getEmpId() + ", " + data.getfName());
//		}
		
		
//		List<EmpVO> res5 = session.selectList("testMapper.test5");
//		for(EmpVO data: res5) {
//			System.out.println(data.getEmpId() + ", " + data.getfName());
//		}
//		
//		for(int idx = 100; idx < 110; idx++) {
//			EmpVO res6 = session.selectOne("testMapper.test6", idx);
//			System.out.println(res6.getEmpId() + ", " + res6.getfName());
//		}
//		
//		List<EmpVO> res7 = session.selectList("testMapper.test7", "Steven");
//		for(EmpVO data: res7) {
//			System.out.println(data.getEmpId() + ", " + data.getfName());
//		}
//		
//		List<EmpVO> res8 = session.selectList("testMapper.test8", "on");
//		for(EmpVO data: res8) {
//			System.out.println(data.getEmpId() + ", " + data.getfName());
//		}
//		// 파라미터 
//		Map<String, Integer> mapData = new HashMap<String, Integer>();
//		mapData.put("start", 100);
//		mapData.put("end", 109);
//		List<EmpVO> res9 = session.selectList("testMapper.test9", mapData);
//		for(EmpVO data: res9) {
//			System.out.println(data.getEmpId() + ", " + data.getfName());
//		}
//
//		EmpVO empData = new EmpVO();
//		empData.setEmployee_id(200);
//		EmpVO res10 = session.selectOne("testMapper.test10" , empData);
//		System.out.println(res10.getEmployee_id() + ", " + res10.getFirst_name());
		
		// insert로 데이터를 넣을 때 primary key에 중복값을 넣으면 에러가 나기 때문에 그걸 방지하기 위해서 아래와 같이 해준다.
		// 시퀀스 객체를 사용하지 않았을 때
//		TestVO insertData = new TestVO();
//		insertData.setId(2); insertData.setName("test"); insertData.setToday(new java.sql.Date(new Date().getTime())); // sql db에 전달할 date는  sql.Date() 를 사용해야 하니까 sql.Date()로 자바에서 사용하는 util.Date()를 덮어준다. -> 결론적으로 sql에 전달할 때는 sql.Date를 사용해야 한다. 
//		TestVO checkData = session.selectOne("testMapper.test15", insertData.getId()); //"testMapper.test15" : select 구문에 해당 -> 여기서 얻은 정보를 checkData에 넣는다. 
//		if(checkData == null) { // 만약 select문 결과가 존재하지 않다면 해당 id는 없기 때문에 
//			int res11 = session.insert("testMapper.test11", insertData);  // 추가를 해준다.
//			System.out.println(res11 + "개의 행이 추가 되었습니다.");
//			session.commit();
//		}else {
//			System.out.println("동일한 ID가 존재합니다.");
//			session.rollback();
//		}
		// 시퀀스 객체를 사용하면 동일한 id 값이 저장되었는지 확인할 필요가 없다.
//		TestVO insertData = new TestVO();
//		insertData.setId(2); insertData.setName("test"); insertData.setToday(new java.sql.Date(new Date().getTime()));  
//		
//		int res11 = session.insert("testMapper.test11", insertData);  // 추가를 해준다.
//		session.commit();
//		System.out.println(res11 + "개의 행이 추가 되었습니다.");
//		
//		// UDATE문
		// update 방법1)
//		Map<String, Object> updateData = new HashMap<String, Object>();
//		updateData.put("id", 1);
//		updateData.put("name", "change");
//		int res12 = session.update("testMapper.test12", updateData);
//		System.out.println(res12 + " 개의 행이 업데이트 되었습니다.");
//		
//		update 방법2) 
//		TestVO objectData = new TestVO();
//		objectData.setId(1); objectData.setName("object");
//		int res13 = session.update("testMapper.test13", objectData);
//		System.out.println(res13 + " 개의 행이 업데이트 되었습니다.");
		
		// delete 문
//		int res14 = session.delete("testMapper.test14", 1);
//		System.out.println(res14+ "개의 행이 삭제 되었습니다.");
//		
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//		java.util.Date stDate = null;
//		java.util.Date edDate = null;
//		
//		try {
//			stDate = sdf.parse("1990/01/01");
//			edDate = sdf.parse("2000/12/31");
//		}catch(ParseException e) {
//			e.printStackTrace();
//		}
//		
//		EmpSelectVO dynamicData = new EmpSelectVO();
//		dynamicData.setSalary(10000);
//		dynamicData.setStartDate(new java.sql.Date(stDate.getTime()));
//		dynamicData.setEndDate(new java.sql.Date(edDate.getTime()));
		//dynamicData.setDeptId(80);
		// 만약 db 컬럼이 string타입이라면 이렇게 해준다. 
//		dynamicData.setStrStartDate("1990/01/01");
//		dynamicData.setStrEndDate("1990/01/01");
		// foreach반복문 사용하기 위해서 List 사용 (Map 사용해도 됨)
//		List<Integer> deptList = new ArrayList<Integer>();
//		deptList.add(80); deptList.add(90); deptList.add(100); 
//		dynamicData.setDeptIdList(deptList);
//		List<EmpVO> res15 = session.selectList("testMapper.test16", dynamicData);
//		System.out.println(res15.size() + "개 행 데이터가 조회되었습니다.");
//		
		
// 부서 조회
		// 결과가 하나일 수도 있고, 여러 개 일수도 있는 경우에는 List에 담기
		// 매개변수는 Map 또는 vo객체 2개 다 선택 가능
//		Map<String, Integer> mapParam = new HashMap<String, Integer>(); // 하나의 값만 전달 할 때
//		mapParam.put("deptId", 80);  // 키와 값의 쌍 -> 얘는 하나의 값을 전달할 때
		// 여러 개의 값을 전달할 때
//		Map<String, List<Integer>> mapParam = new HashMap<String , List<Integer>>();	// List를 값으로 넣은 것
//		List<Integer> deptList2 = new ArrayList<Integer>();
//		deptList2.add(80); deptList2.add(90); deptList2.add(100);
//		mapParam.put("deptList", deptList2);
//		// List의 Object가 Map 의 형태이다. List안에 Map이 있는 거 -> 반환되는 map의 키와 값이 List에 map의 형태로 들어가는 것
//		List<Map<String, Object>> res16 = session.selectList("exampleMapper.empOfDeptCount", mapParam);
//		
//		// 출력
//		if(res16.size() != 0 ) {
//				for(Map<String, Object> record: res16) {
//					System.out.println("총원 : " + record.get("TOTAL"));
//					System.out.println("부서명 : " + record.get("DEPT_NAME"));
//					System.out.println("부서코드 : " + record.get("DEPT_CODE"));
//				}
//		}else  {
//				System.out.println("해당 부서는 존재하지 않습니다.");
//		}
//		
//		TestVO insertData2 = new TestVO();
//		
//		insertData2.setName("test"); insertData2.setToday(new java.sql.Date(new Date().getTime()));
//		
//		int res17 = session.insert("testMapper.seqGetInsert", insertData2);
//		session.commit();
//		System.out.println(res17 +" 개의 행이  추가되었습니다. 자동 생성된 ID 는 + " + insertData2.getId() + "입니다.");
//		
		EmpComplexVO res18 = session.selectOne("testMapper.empComplexSelect", 100);
		System.out.println(res18);
		
		
	}
}
