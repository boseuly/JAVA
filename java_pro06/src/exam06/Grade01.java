package exam06;

public class Grade01 {
	private String name;
	private double score;
	
	public Grade01(String name) {
		this.name = name;
		this.score = 0.0;
	}
	public Grade01(String name, double score) {
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
