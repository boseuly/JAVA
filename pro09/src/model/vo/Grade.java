package model.vo;

// 과목에 대한 성적 정보를 가지는 클래스
public class Grade extends Subject {		// subject에 과목명(name)
	/*
	 * 점수(score)와 등급(level)을 정의
	 * getter/setter 도 작성
	 * 
	 */
	private double score;	// 점수
	private char level = 'F';		// 등급
	
	
	
	public char levelArrMethod(double score) {
		if(score <= 100 && score >= 90) {
			this.level = 'A';
		}else if(score >= 80) {
			this.level = 'B';
		}else if(score >= 70) {
			this.level = 'C';
		}else if(score >= 60) {
			this.level = 'D';
		}else if(score >= 50) {
			this.level = 'E';
		}else if(score >= 40) {
			this.level = 'F';
		}
		return this.level;
	}
	
	
	public Grade(String name) {
		super(name);
		this.level = levelArrMethod(score);
	}
	public Grade(double score) {
		super();
		this.score = score;
	}
	
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public char getLevel() {
		return level;
	}
	public void setLevel(char level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Grade [name()=" + getName() + ", score=" + score + ", level=" + level + "]";
	}
	
	
}
