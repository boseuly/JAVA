package exam06;

import java.util.Arrays;

public class GradeList {
	/*
	 * Grade 객체를 배열로 만들어서 다루기 위한 객체
	 * 이 객체를 통해 데이터를 추가, 수정, 삭제, 조회
	 * 할 수 있게 한다.
	 */
	
	private Grade[] gArr = new Grade[0];
	
	public GradeList() {}
	
	public GradeList(int size) {
		this.gArr = new Grade[size];
	}
	public GradeList(String ...subjects) {
		this.gArr = new Grade[subjects.length];
		for(int i = 0; i < subjects.length; i++) {
			this.gArr[i] = new Grade(subjects[i]);
		}
	}
	
	public GradeList(Grade[] grade) {
		this.gArr = new Grade[grade.length];		//얕은 복사를 하면 외부에서도 값이 변함 -> 외부에서 변하지 못하도록 하려면 깊은 복사
		for(int i = 0; i < grade.length; i++) {
			this.gArr[i] = new Grade(grade[i].getName(), grade[i].getScore());
		}
	}
	
	//gArr[index] 라는 부분을 항상 쓰는 게 귀찮아서 만든 메소드
	public Grade get(int index) {
		return this.gArr[index];
	}
	
	public int length() {
		return this.gArr.length;
	}
	
	//좀더 쉽게 과목명을 가지고 오고 싶어서 만듦
	public String getName(int index) {		
		return this.gArr[index].getName();
	}
	public double getScore(int index) {	
		return this.gArr[index].getScore();
	}
	
	// 추가 메소드
	public void add(String name) {
		this.gArr = Arrays.copyOf(this.gArr, length() + 1);
		this.gArr[length() - 1] = new Grade(name);
	}
	
	public void add(String name, double score) {
		this.gArr = Arrays.copyOf(this.gArr, length() + 1);
		this.gArr[length() -1] = new Grade(name, score);
	}
	
	public void add(Grade grade) {
		this.gArr = Arrays.copyOf(this.gArr, length() + 1);
		this.gArr[length() - 1] = grade;
	}
	
	// 수정 메소드
	public void update(String search, String change) {	//과목을 찾아서 수정하겠다.
		int idx = findIndex(search);
		this.gArr[idx].setName(change);
	}
	
	public void update(String search, String changeName, double changeScore) {
		int idx = -1;
		for(int i = 0; i < length(); i++) {
			if(this.gArr[i].getName().equals(search)) {
				idx = i;
				break;
			}
		}
		this.gArr[idx].setScore(changeScore);
		this.gArr[idx].setName(changeName);
	}
	
	//인덱스로 찾아서 수정하기
	public void update(int index, String change) {
		this.gArr[index].setName(change);
	}
	public void update(int index, Grade change) {
		this.gArr[index] = change;
	}
	
	
	//삭제 메소드
	
	
	
	
	
	
	
	
	
	
	
	//조회 메소드
	//이름으로 점수 찾기
	public double getScore(String name) {	
		int idx = findIndex(name);
		return this.gArr[idx].getScore();
	}
	//전체 평균 구하기
	public double getAvg() {
		int count = this.gArr.length;	//길이만큼 나눠야 됨
		double avg = this.getSum() / count;
		return avg;
	}
	//전체 총합 구하기
	public double getSum() {
		double sum = 0;
		double[] arr = new double[length()];
		for(int i = 0; i < length(); i++) {
			arr[i] = this.gArr[i].getScore();
			sum += arr[i];
		}
		return sum;
	}
	
	//과락 과목 찾기
	public String[] getUnder() {
		String[] arr = new String[0];
		int count = 0;
		for(int i = 0; i < length(); i++) {
			if(this.gArr[i].getScore() < 40) {	//과락 기준점수보다 작으면
				arr = Arrays.copyOf(arr, arr.length + 1);	//동적배열 사용
				arr[count] = this.gArr[i].getName();		//과락 과목 배열에 넣기
				count++;
			}
		}
		return arr;
	}
	
	
	
	
	
	//중복되는 코드는 메소드로 빼준다.
	public int findIndex(String name) {
		int idx = -1;
		for(int i = 0; i < length(); i++) {		//같은 과목명을 찾는 for문
			if(this.gArr[i].getName().equals(name)) {	//과목명과 search명과 같다면
				idx = i;
				break;
			}
		}
		return idx;
	}
	
	
	
	
	
	
}
