package exam06;

import java.util.Arrays;

import exam05.Person;

public class GradeList01 {
	private Grade01[] gArr = new Grade01[0];	//Grade01클래스 참조형 배열객체 생성 
	
	public GradeList01() {}
	
	public GradeList01(int size) {
		this.gArr = new Grade01[size];
	}
	
	public GradeList01(String ...subjects) {	//가변인자 -> 배열로 취급
		// new GradeList("과목1", "과목2", ...)
		this.gArr = new Grade01[subjects.length];
		for(int i = 0; i < subjects.length; i++) {
			this.gArr[i] = new Grade01(subjects[i]);
		}
	}
	public GradeList01(Grade01[] grade) {
//		this.gArr = grade;		//얕은복사로 하면 외부에서 값을 변경시킬 수 있음. 하지만 그걸 막으려면 깊은 복사해야 됨
		this.gArr = new Grade01[grade.length];
		for(int i = 0; i < grade.length; i++) {
			this.gArr[i] = new Grade01(grade[i].getName(), grade[i].getScore());
		}
	}
	
	public Grade01 get(int index) {
		return this.gArr[index];
	}
	
	public int length() {
		return this.gArr.length;
	}
	
	public String getName(int index) {
		return this.gArr[index].getName();
	}
	
	public double getScore(int index) {
		return this.gArr[index].getScore();
	}
	
	//추가
	public void add(String name) {
		this.gArr = Arrays.copyOf(this.gArr, length()+1);
		this.gArr[length() -1] = new Grade01(name);
	}
	public void add(String name, double score) {
		this.gArr = Arrays.copyOf(this.gArr, length()+1);
		this.gArr[length() -1] = new Grade01(name, score);
	}
	public void add(Grade01 grade) {
		this.gArr = Arrays.copyOf(this.gArr, length()+1);
		this.gArr[length() -1] = grade;
	}
	
	//수정
	//수학 과목을 수학1로 변경하자!
	public void update(String search, String change) {
		int idx = findIndex(search);
		this.gArr[idx].setName(change);
	}
	//수학의 배열 공간을 찾아서 수학을 수학1으로 바꾸고 점수를 89로 바꿔라 
	public void update(String search, String changeName, double changeScore) {
		int idx = findIndex(search);
		this.gArr[idx].setScore(changeScore);
		this.gArr[idx].setName(changeName);
	}
	//수학과목을 수학1로 변경하고 점수도 87로 변경해라
	public void update(String search, Grade01 change) {
		int idx = findIndex(search);
		this.gArr[idx] = change;
	}
	//수학 과목을 ["수학1",87]로 변경해라  []자체가 클래스라고 생각하면 됨
	public void update(int index, String change) {
		this.gArr[index].setName(change);
	}
	public void update(int index, Grade01 change) {
		this.gArr[index] = change;
	}
	
	//삭제
	
	
	//조회
	//이름가지고 점수를 찾을 수 있도록
	public double getScore(String name) {
		return 0;
	}
	
	//전체 평균 구하기
	public double getAvg() {
		return 0;
	}
	//총합 구하기
	public double getTotal() {
		return 0;
	}
	//과락 과목 찾기
	public String[] getUnder() {
		// 과락의 기준은 40점 미만
		return null;
	}
	public String[] getUnderScore(double score) {
		//과락의 기준을 외부 값으로 받아서 찾아내는 함수
		return null;
	}
	
	//최고 득점 과목
	public String getTop() {
		//최고 득점을 받은 과목 찾기
		return null;
	}
	public String[] getTop(int count) {
		//최고 득점을 받은 과목 count만큼 찾기
		return null;
	}
	//최저 득점 과목
	public String getBottom() {
		//최저 득점 받은 과목 찾기
		return null;
	}
	public String[] getBottom(int cunt) {
		return null;
	}
	
	//자주 쓰이는 코드 메소드로 빼놓음
	public int findIndex(String name) {
		int idx = -1;
		for(int i = 0; i < length(); i++) {
			Grade01 data = get(i);
			if(name.equals(data.getName())) {
				idx = i;
				break;
			}
		}
		return idx;
	}
	
	
	
	
}
