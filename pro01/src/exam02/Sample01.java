package exam02;
//import : 자바의 기본 기능 외의 추가 기능을 사용하기 위해 작성
import java.util.*;

public class Sample01 {
	
	// 멤버 변수
	private int number = 0;
	private String name = "hyun";
	
	// 생성자 : 클래스명과 동일한 이름을 사용하는 메서드를 생성자라고 한다.(생성자는 리턴 타입이 없다.)
	// 자바에서는 클래스를 사용할 때 반드시 객체로 생성하여 사용해야한다. 이때 객체를 생성할 때 필요한 것이 생성자
	public Sample01() {
		// 기본생성자는 클래스에 작성을 하지 않아도 기본적으로 생성되어 사용됨
	}
	
	
	public void method() {      // 멤버 메서드 : 메인 메서드에서 벗어나서 작성되는 메소드
		// 프로그램 동작을 위한 실행 코드가 이곳에 정의된다.
		
		//지역변수 : 특정 지역 {}안에서만 사용되는 변수
		int i = 0;
		String s = "문자열";
	}
	
	
	// 메인 메서드(함수) : 자바 프로그램에서 프로그램을 실행하기 위한 시작점
	public static void main(String[] args) {
		
	}
}
