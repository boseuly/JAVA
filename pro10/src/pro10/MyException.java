package pro10;

import java.io.FileNotFoundException;

public class MyException {

	public void exceptionThrows() throws Exception{		// 
	// 현재 메서드를 싱행한 위치로 발생된 에러를 던지는 (throws)메서드
	exceptionRun();
	
}
	public void exceptionNonThrows() {
		try {
			exceptionRun();
		}catch(Exception e) {
			System.out.println("Exception을 처리하였습니다.");
		}
	}
	private void exceptionRun() throws UserDefineException{
		// 에러를 발생 시키기 위한 메소드
		System.out.println("에러 발생!");
		throw new UserDefineException("사용자 정의 에러가 발생하였습니다.");
	}
	
	
	
	
	
	
}