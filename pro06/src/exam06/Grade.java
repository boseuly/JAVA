package exam06;

public class Grade {
	/*
	 * 과목에 대한 성적 점수 정보를 가지는 객체
	 */
	
	private String name;	//과목 이름
	private double score;	//점수
	
	
	
	public Grade(String name) {
		this(name,0.0);		//아래 생성자를 이용
	}
	public Grade(String name, double score) {
		this.name = name;
		this.score = score;
	}
	
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getScore() {
		return this.score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	
}
