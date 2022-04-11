package controller;

import java.util.Date;

import model.vo.Student;
import model.vo.Teacher;

public class TeacherDatabaseManager {
	private Teacher[] datas;
	
	/* 메모리(휘발성)
	 * 내가 만든 프로그램을 가준으로
	 * 내 프로그램에서 하드디스크에 저장하는 거 -> 출력
	 * 하드디스크에서 내 프로그램으로 불러오는 거 -> 입력 
	 *
	 * 내가 패스워드를 수정했는데 프로그램이 꺼지면 그 정보가 아예 초기화됨
	 * 그러지 않기 위해서는 더이상 메모리에 넣는 게 아니라 파일로 하드디스크에 저장을 해야 한다. (이게 출력)
	 */
	
	
	// 테스트용 초기 데이터 나중에는 삭제할 것임.
	{
		datas = new Teacher[2];
		datas[0] = new Teacher("이보슬", "1234");
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
