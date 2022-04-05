package controller;

import java.util.Arrays;

import model.vo.Grade;
import model.vo.Student;

// 학생 정보를 추가, 수정, 삭제할 수 있는 기능이 정의 되어 있는 매니저 클래스 
public class DatabaseManager implements ImplDatabaseManager{
	private Student[] datas;		// 학생의 정보들
	
	// 초기데이터(초기화 블럭) -> 초기화된 값이 있어야 찾거나 수정 할 수 있으니까 초기화해둔 거임
	{	
		datas = new Student[5];
		datas[0] = new Student("홍길동");	datas[1] = new Student("김도수");	
		datas[2] = new Student("엄석대"); datas[3] = new Student("고길동");	
		datas[4] = new Student("김짱돌");	
		
		for(int i = 0; i < datas.length; i++) {
			datas[i].setGrades(new Grade[] {
					new Grade("국어"), new Grade("영어"), new Grade("수학"), new Grade("과학")
			});
		}
	}
	
	
	// 찾기 메소드
	@Override
	public Grade[] search(String name) {	// 학생 이름을 찾아서 성적, 등급 등을 MenuManager에 전달
		// 우선 해당 학생 인덱스를 찾는다. 
		int idx = _findIndex(name);		// 이름으로 인덱스를 찾아서 저장 -> 해당 학생의 Grade를 가져오기 위해
		// 위치를 찾았으면 해당 학생의 데이터를 반환해주면 된다.
		if(idx == -1) {		// 해당 학생이 없으면
			return null;	// null값 반환
		}
		return datas[idx].getGrades();
	}
	
	// 찾는 학생이 없다면 true, 있으면 false
	public boolean existed(String name) {
		return _findIndex(name) == -1? true : false;
	}
	
	// 해당 학생의 위치를 알려주는 메소드
	private int _findIndex(String name) {
		int idx = -1;		// 이름을 찾아야 돼
		for(int i = 0; i < datas.length; i++) {	// datas의 길이만큼(datas는 학생들 인원 수라고 생각하면 됨)
			if(name.equals(datas[i].getName())){		// 만약 name과 datas[i].getName 같으면 idx = i
				 idx = i;
				return idx;
			}
		}
		return idx;	// 인덱스 번호를 반환
	}
	// 추가 메소드
	@Override
	public boolean add(String name) {
		int idx = _findIndex(name);
		if(idx == -1) {
			return false;
		}
		datas = Arrays.copyOf(datas, datas.length + 1);	// 공간 하나를 더 추가해준다.
		datas[datas.length -1] = new Student(name);		// 늘어난 공간에 이름을 추가해준다.
		datas[datas.length -1].setGrades(_initGrade());	// 해당 공간의 Grades를 
		return false;
	}
	private Grade[] _initGrade() {
		Grade[] gArr = new Grade[] {new Grade("국어"), new Grade("영어"), new Grade("수학"), new Grade("과학")
		};	// 과목을 gArr배열에 넣기
		
		
		return null;
	}

	// 수정 메소드
	@Override
	public Student modify(String name, String subject, int score) {
		// TODO Auto-generated method stub
		return null;
	}
	// 삭제 메소드
	@Override
	public boolean remove(String name) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
