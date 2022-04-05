package controller;

import java.util.Arrays;

import model.vo.Grade;
import model.vo.Student;

public class DatabaseManager implements ImplDatabaseManager {
	// 학생 정보를 추가, 수정, 삭제할 수 있는 기능이 정의되어 있는 매니저 클래스
	
	private Student[] datas;
	
	// 초기화 블록
	{
		datas = new Student[5];
		datas[0] = new Student("홍길동");	datas[1] = new Student("김도원");
		datas[2] = new Student("박수아");	datas[3] = new Student("최종희");
		datas[4] = new Student("이정우");
		
		for(int i = 0; i < datas.length; i++) {
			datas[i].setGrades(new Grade[] {
					new Grade("국어"), new Grade("영어"), new Grade("수학"), new Grade("과학")
			});
		}
	}
	
	@Override
	public Grade[] search(String name) {
		int idx = _findIndex(name);	// 이름을 찾기 위해서 
		if(idx == -1) {
			return null;
		}
		return datas[idx].getGrades();
	}

	@Override
	public boolean add(String name) {	// 정보를 추가해주기
		if(_isExisted(name)) {
			return false;
		}
		
		datas = Arrays.copyOf(datas, datas.length + 1);
		datas[datas.length - 1] = new Student(name);
		datas[datas.length - 1].setGrades(_initGrade());	// 과목 배열 추가해주는 건 따로 뺐음
		
		return true;
	}

	private Grade[] _initGrade() {		// 과목 배열 추가해주는 메소드
		Grade[] gArr = new Grade[] {
				new Grade("국어"), new Grade("영어"), new Grade("수학"), new Grade("과학")
		};
		return gArr;
	}

	@Override
	public Student modify(String name, String subject, int score) {	// 과목, 점수 수정
		int idx = _findIndex(name);		// 이름 위치 찾아주고
		Grade[] grades = datas[idx].getGrades();	// 해당 학생의 과목명배열 -> grades[]에 넣기
		for(int i = 0; i < grades.length; i++) {
			if(subject.equals(grades[i].getName())) {
				grades[i].setScore(score);
				return datas[idx];
			}
		}
		return null;
	}
	

	@Override
	public boolean remove(String name) {
		int idx = _findIndex(name);
		
		if(idx == -1) {
			return false;
		}
		
		Student[] temp = new Student[datas.length - 1];
		System.arraycopy(datas, 0, temp, 0, idx);
		System.arraycopy(datas, idx + 1, temp, idx, datas.length - (idx + 1));
		datas = temp;
		
		return true;
	}
	
	private boolean _isExisted(String name) {
		return _findIndex(name) != -1 ? true : false;	// 이름이 이미 존재하지 않으면 false
	}
	
	private int _findIndex(String name) {
		int idx = -1;		// 이름이 존재하지 않으면 -1
		
		for(int i = 0; i < datas.length; i++) {
			if(name.equals(datas[i].getName())) {
				idx = i;
				return idx;
			}
		}
		
		return idx;
	}

	public Student login(String username, String password) {
		
		return null;
	}
	
	
}
