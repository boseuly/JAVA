package exam02;

import java.util.Arrays;

public class ReportGrade01 {
	private String name;		//학생명
	private String subjects[];	//과목명이 저장되는 멤버변수
	private double grades[];	//점수가 저장되는 멤버변수
	
	//매개변수 있는 생성자
	public ReportGrade01(String name) {
		this.name = name;
	}
	public ReportGrade01(String name, String[] subjects) {
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
	public void setSubjects(String[] subject) {	
		this.subjects = subject;
		
	}
	
	
	//grades(점수)의 getter & setter
	public double[] getGrades() {
		return grades;
	}
	
	//setter 매개변수는 멤버변수랑 다른 자료형이어도 됨
	public void setGrades(double[] grades) {	//점수를 가변인자로 받기
		this.grades = grades;
	}
	
	// 해당 과목에 대한 성적을 입력하기
	public void setGrades(String subject, double grade) {
		/*
		 * 국어 라는 subject 매개변수가 들어오면 "국어"가 몇 번 인덱스에 
		 * 있는지 찾아야 한다. 그럼 그 인덱스 번호에 있는 grades[] 의 값을 
		 * 매개변수 grade로 수정해준다.
		 * 
		 */
		int idx = -1;		//만약 초기값을 0으로 설정하면 해당 과목을 찾을 수 없을 때 idx = 0이 돼서 0번 인덱스가 나오게 됨
		for(int i = 0; i < this.subjects.length; i++) {
			if(this.subjects[i].equals(subject)) {
				idx = i;
				break;		//해당 과목을 찾으면 더이상 반복할 필요가 없으니까 break;
			}
		}
		this.grades[idx] = grade;
	}
	
	//해당 과목의 점수만 가져오고 싶을 때
	public double getGrade(String subject) {
		int idx = -1;		//만약 초기값을 0으로 설정하면 해당 과목을 찾을 수 없을 때 idx = 0이 돼서 0번 인덱스가 나오게 됨
		for(int i = 0; i < this.subjects.length; i++) {
			if(this.subjects[i].equals(subject)) {
				idx = i;
				break;		//해당 과목을 찾으면 더이상 반복할 필요가 없으니까 break;
			}
		}
		return this.grades[idx];
	
	}
	
	//동적 배열을 사용하여 새로운 과목과 점수를 추가
	public void addSubject(String subject) {
		// 점수는 0점으로 추가
		this.addSubject(subject, 0);
		
	}
	public void addSubject(String subject, double grade) {
		//점수는 grade 값으로 추가 
		
		this.subjects = Arrays.copyOf(subjects, subjects.length+1);
		this.grades = Arrays.copyOf(grades, this.grades.length+1);
		
		this.subjects[this.subjects.length - 1] = subject;		//추가된 공간에 매개변수 넣기
		this.grades[this.grades.length - 1] = grade;		//점수는 grade로 추가  //인덱스랑 길이랑 헷갈리지 말기 
		//[this.grades.length + 1]이 아니라 -1이 맞음(길이를 인덱스에 넣는 거니까) 이해 안 가면 그림 그려보기 
		
	}
	
	
	//오히려 과목을 뺴기
	
	//평균을 구하는 메소드
	
	//총합 구하는 메소드
	
	
}
