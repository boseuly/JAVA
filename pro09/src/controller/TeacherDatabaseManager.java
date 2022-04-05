package controller;

import java.util.Date;

import model.vo.Teacher;

public class TeacherDatabaseManager {
	private Teacher[] datas;
	// 테스트용 초기 데이터, 나중에는 삭제할 것
	{	// 초기화 블럭
		datas = new Teacher[2];
		datas[0] = new Teacher("고명환", "1234");		
		datas[0].setLoginDate(new Date());			// 로그인기록
		datas[1] = new Teacher("김수덕", "1111");
	}
	
	public Teacher login(String name, String password) {
		// Teacher 배열에 존재하는 객체들 중 동일한
		// 이름, 패스워드를 사용하는 객체가 있는지 검사 후
		// 동일한 객체가 있으면 해당하는 Teacher 객체를 반환
		// 동일한 객체가 없으면 null 반환
		
		
		// 넘어온 이름과 패스워드를 찾아야 한다.
		for(int i = 0; i < datas.length; i++) {
			if(name.equals(datas[i].getName())&& password.equals(datas[i].getPassword())) {
				return datas[i];
			}
		}
		return null;
	}
	
}
