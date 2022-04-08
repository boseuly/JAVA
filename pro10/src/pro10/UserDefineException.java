package pro10;
//  사용자 정의 에러는 라이브러리 만들 때 사용한다. 
//	내가 만든 기능, 클래스, 메소드들을 다른 프로그래머가 사용하려고 할 때 -> 이건 거 잘못 됐다고 전달하기 위해서 사용자정의 에러를 사용한다.
//	사용자 정의 에러 만들고 throws로 던지고 -> try catch로 해결하고
//	지금 우리는 try ... catch ... / throws ... 를 제대로 알고 사용하는 게 우선
public class UserDefineException extends Exception {	
	public UserDefineException() {}
	public UserDefineException(String message) {
		super(message);
	}
}
