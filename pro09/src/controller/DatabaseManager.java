package controller;

import java.util.Arrays;

import model.vo.Grade;
import model.vo.Student;
import model.vo.Teacher;

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
		int idx = _findIndex(name);
		if(idx == -1) {
			return null;
		}
		return datas[idx].getGrades();
	}

	@Override
	public boolean add(String name) {
		if(_isExisted(name)) {
			return false;
		}
		
		datas = Arrays.copyOf(datas, datas.length + 1);
		datas[datas.length - 1] = new Student(name);
		datas[datas.length - 1].setGrades(_initGrade());
		
		return true;
	}

	private Grade[] _initGrade() {
		Grade[] gArr = new Grade[] {
				new Grade("국어"), new Grade("영어"), new Grade("수학"), new Grade("과학")
		};
		return gArr;
	}

	@Override
	public Student modify(String name, String subject, int score) {
		int idx = _findIndex(name);
		Grade[] grades = datas[idx].getGrades();
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
	public Student getStudent(String name) {
		return datas[_findIndex(name)];		// 해당 이름의 인덱스를 찾아주는 메소드
	}
	
	public boolean isExisted(String name) {		// 패스워드 초기화 할 때 외부에서 사용해야 돼서 public으로도 만들어줌
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
	
	public Student login(String name, String password) {
		for(int i = 0; i < datas.length; i++) {
			if(name.equals(datas[i].getName()) && password.equals(datas[i].getPassword())) {
				return datas[i];
			}
		}
		return null;
	}
}
