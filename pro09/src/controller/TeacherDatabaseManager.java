package controller;

import java.util.Date;

import model.vo.Student;
import model.vo.Teacher;

public class TeacherDatabaseManager {
	private Teacher[] datas;
	
	// 테스트용 초기 데이터 나중에는 삭제할 것임.
	{
		datas = new Teacher[2];
		datas[0] = new Teacher("고명환", "1234");
		datas[0].setLoginDate(new Date());
		datas[1] = new Teacher("김수덕", "1111");
	}
	
	public Teacher login(String name, String password) {
		// Teacher 배열에 존재하는 객체들 중 동일한
		// 이름, 패스워드를 사용하는 객체가 있는지 검사 후
		// 동일한 객체가 있으면 해당하는 Teacher 객체를 반환
		// 동일한 객체가 없으면 null 반환.
		for(int i = 0; i < datas.length; i++) {
			if(name.equals(datas[i].getName()) && password.equals(datas[i].getPassword())) {
				return datas[i];
			}
		}
		return null;
	}
	
	public Teacher getTeacher(String name) {
		return datas[_findIndex(name)];		// 해당 이름의 인덱스를 찾아주는 메소드
	}
	public boolean isExisted(String name) {
		return _isExisted(name);
	}
	
	private boolean _isExisted(String name) {
		return _findIndex(name) != -1 ? true : false;
	}
	
	private int _findIndex(String name) {
		int idx = -1;
		
		for(int i = 0; i < datas.length; i++) {
			if(name.equals(datas[i].getName())) {
				idx = i;
				return idx;
			}
		}
		
		return idx;
	}
	
}
