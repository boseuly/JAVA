package model.vo;

// 학생 정보를 가지는 클래스
public class Student {
	/*
	 * 이름(name)과 성적배열(Grade)를 정의
	 * getter/setter도 작성
	 */
	private String name;	// 학생 이름
	private Grade[] grades;	// 성적(포함 관계)(점수, 등급도 포함)
	
	public Student(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Grade[] getGrades() {
		return grades;
	}
	public void setGrades(Grade[] grades) {
		this.grades = grades;
	}
	
	
	
	
}
