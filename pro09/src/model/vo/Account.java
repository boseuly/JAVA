package model.vo;

import java.util.Date;
import java.util.Random;

// 회원 정보	(Student, Teacher 클래스가 Account상속) -> 이렇게 하면 MenuManager에서 둘을 한꺼번에 다룰 수 있음(StudentMenuManager은 삭제해도 됨)
public abstract class Account {
	
	private String name;
	private String password;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	// 패스워드 변경
	public boolean changePassword(String curPass, String changePass) {
		// 추상메소드
		if(curPass.equals(this.getPassword())) {		// 기존의 패스워드 동일하면
			this.setPassword(changePass);
			return true;
			
		}
		return false;	// 만약 다르다면 false 반환
	}
		
	// 패스워드를 초기화한다.(영문자 6자를 임의로 생성하여 초기화한다.)
	// 학생용 계정은 STD_ 접두사가 붙어서 생성되게 한다.
	// 선생님용 계정은 TCH_ 접두사가 붙어서 생성되게 한다.
	public  String resetPassword() {
		Random r = new Random();
		String newPass = "";
		
		for(int i = 0; i< 6; i++) {		// -> 6자리 패스워드를 만들어줄 거니까 6번을 
			if(r.nextBoolean()) {	
				newPass += (char)(r.nextInt(26) + 65);
			}else {
				newPass += (char)(r.nextInt(26) + 97);
			}
		}
		setPassword(newPass);	// prefix를 뺀 이유 -> student, teacher의 접두사가 서로 달라서 이건 빼줘함(자식에서 보완하기)
		return newPass;		// STD_접두사는 굳이 반환을 하지 않아도 됨
	}
/*
 * StudentMenuManager를 삭제하고 MenuManager 하나로 교사용 / 학생용 
 * 메뉴가 나오고 기능이 동작할 수 있게 변경한다. -> ManuManager에서 if문을 통해 해결할 수 있음
 * 
 * 패스워드 변경 기능을 구현
 * - 로그인을 한 계정에 대해서 변경을 할 수 있게 한다.
 * 
 * 패스워드 초기화 기능을 구현
 * - 로그인 전에 특정 계정에 대한 패스워드 초기화를 할 수 있게 한다.
 * - 학생용 계정은 STD_접두사가 붙어서 생성되게 한다.
 * - 선생님용 계정은 TCH_접두사가 붙어서 생성되게 한다.
 * - 접두사 제외 영문제 6자의 임의 문자열도 추가 생성한다.
 * - 초기화된 패스워드를 사용자에게 알리기 위해 출력을 할 때 접두사는 제외하고 출력한다.
 * 
 */
	
}
