package exam02;

public class ReportGrade {
	private String name;		//학생명
	private String subjects[];	//과목명이 저장되는 멤버변수
	private double grades[];	//점수가 저장되는 멤버변수
	
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
	public void setSubjects(String ...strings) {	
		for(int i = 0; i < strings.length; i++) {
			this.subjects[i] = strings[i];
		}
	}
	
	
	//grades(점수)의 getter & setter
	public double[] getGrades() {
		return grades;
	}
	public void setGrades(double ...ds) {	//점수를 가변인자로 받기
		this.grades = ds;
	}
	
	
}
