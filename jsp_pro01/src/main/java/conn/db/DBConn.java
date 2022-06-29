package conn.db;

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
}
