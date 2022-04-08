package exception;

// 패스워드에 대해 영문자/숫자 조합으로 설정하게 만들기 위한 Unchecked Exception 생성 및 적용
public class PasswordUnValidException extends RuntimeException { 

	public PasswordUnValidException() {
		super();
	}

	public PasswordUnValidException(String message) {	// 시스템 오류를 해결하였습니다. -> 이런 메세지를 전달하기 위해서 매개변수 있는 생성자가 필요하다.
		super(message);
	}
	
}
