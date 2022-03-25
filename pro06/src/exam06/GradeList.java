package exam06;

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
	
	
	
}
