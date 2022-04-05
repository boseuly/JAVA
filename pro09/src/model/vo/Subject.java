package model.vo;

// 과목 정보를 가지는 클래스
public class Subject {

	/*
	 * 과목명(name)과 getter/setter만 정의
	 */
	private String name;	// 과목명
	
	public Subject(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
