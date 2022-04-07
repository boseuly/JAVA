package model.vo;

// 과목에 대한 성적 정보를 가지는 클래스
public class Grade extends Subject {
	/*
	 * 점수(score)와 등급(level) 을 정의
	 * getter/setter 도 작성
	 */
	private double score;
	private char level;
	private char[] levelArr = new char[] {
			'F', 'F', 'F', 'F', 'E', 'E', 'D', 'C', 'B', 'A'
	};
	
	public Grade(String name) {
		super(name);
		this.level = 'F';
	}
	
	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		this.score = score;
		this.level = levelArr[(int)(score / 10)];
	}
	
	public char getLevel() {
		return level;
	}
	
	public void setLevel(char level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Grade [name=" + getName() + ", score=" + score + ", level=" + level + "]";
	}
	
}
