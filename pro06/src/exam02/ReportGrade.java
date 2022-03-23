package exam02;

public class ReportGrade {
	private String name;
	private String subjects[];
	private double grades[];
	
	//매개변수 있는 생성자
	public ReportGrade(String name) {
		this.name = name;
		
	}
	public ReportGrade(String name, String[] subjects) {
		this.name = name;
		this.subjects = subjects;
	}
	
	//name의 getter & setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	//subjects(과목)의 getter & setter
	public String[] getSubjects() {
		return subjects;
	}
	public void setSubjects(String[] subjects) {	
		this.subjects = subjects;
	}
	
	
	//grades(점수)의 getter & setter
	public double[] getGrades() {
		return grades;
	}
	public void setGrades(double[] grades) {
		this.grades = grades;
		
	}
	
}
