package model.vo;

import java.util.Date;
import java.util.Random;

import exception.PasswordUnValidException;

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
	
	public void setPassword(String password){	// 패스워드를 설정할 때 영문자/숫자 조합으로 설정하기 위해 
		boolean numExisted = false;
		boolean lowerExisted = false;
		boolean upperExisted = false;
		
//		for(int i = 0; i < password.length(); i++) {		// 
//			if(password.charAt(i) >= '0' && password.charAt(i) <= '9') {	
//				numExisted =true;
//			}
//			if(password.charAt(i) >= 'a' && password.charAt(i) <= 'z') {
//				lowerExisted = true;
//			}
//			if(password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
//				upperExisted = true;
//			}
//			
//		}	
//		if(!(numExisted && (lowerExisted || upperExisted))) {
//			throw new PasswordUnvalidException("패스워드에는 숫자/영문자 조합으로 만들어야 합니다.");	
//		}
		
		if(!(password.matches("[0-9a-zA-Z]{4,12}") 		// []안에는 문자에 대한 범위를 적는 거임(0-9 -> 0~9까지의 숫자, a-z ->a~z까지,  ), {}안에는 몇자를 적을 건지
				&& !password.matches("[0-9]{4,12}")) 	// [^0-9]{4,12} -> ^표시는 제외를 의미(0~9숫자가 안 들어가야 함) -> 그럼 password앞에 !없애도 됨 
				&& !password.matches("[a-zA-Z]{4,12}")) // ^이[]앞에 붙으면 []안에 내용으로 시작해야 한다는 뜻, []$는 이걸로 끝나야 한다는 뜻. ^[0-9]{4,12}$ 요런식으로 사용
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
