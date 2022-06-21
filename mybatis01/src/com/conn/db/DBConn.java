package com.conn.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBConn {
	//  mybatis를 사용할 수 있도록 만드는 구문 
	public static SqlSession getSqlSession() {
		SqlSession sess = null;
		String config = "resources/mybatis-config.xml";  // mybatis-config에 대한 경로 설정 -> 경로만 매치시켜줄 수 있으면 이름, 파일 위치 상관 없음 단, 경로를 잘 지정해야 한다.
		try {
			// inputstream 으로 읽어들이기
			InputStream is = Resources.getResourceAsStream(config);
			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);  // sessionFactoryBuilder 를 통해 SessionFactory를 만들기
			sess = ssf.openSession();  // sessionFactory 로 
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return sess;
	}
	public static void main(String[] args) {
		// 기본
//		SqlSession session = DBConn.getSqlSession();
//		String result = session.selectOne("testMapper.test"); // 여기에 mapper.xml에 있는 태그의 namespace.id 를 적어주면 된다. 내가 실행하고자 하는 태그의 id를 넣어주는 것
//		System.out.println(result);
		
		// 검색결과가 여러개인 경우에는 selectList 사용하기
		SqlSession session = DBConn.getSqlSession();
		List<Object> result = session.selectList("testMapper.test");
		System.out.println(result);
	}
}
