package model.vo;

import java.util.Random;

// 학생 정보를 가지는 클래스
public class Student extends Account {	// 이름과 패스워드는 Account에서 관리
	private Grade[] grades;
	
	public Student(String name) {
		setName(name);
		setPassword("1111");
	}

	public Grade[] getGrades() {
		return grades;
	}

	public void setGrades(Grade[] grades) {
		this.grades = grades;
	}
	
	// 패스워드를 초기화해줄 건데 대소문자 랜덤으로 초기화를 시켜주기 위한 메소드
	@Override
	public String resetPassword() {
		String prefix = "STD_";
		// 대소문자 영문자 구분하여 임의로 생성...	
		// ASCII 코드를 활용
		// 대문자 65 ~ 90
		// 소문자 97 ~ 122
		String newPass = super.resetPassword();
		setPassword(prefix + newPass);
		return newPass;		// STD_접두사는 굳이 반환을 하지 않아도 됨
	}
	
}
