package exam09;

public class Main {

	public static void main(String[] args) {
		/*
		 * 인터페이스
		 * - 추상 클래스의 변형체
		 * 
		 * - 상수형 필드와 추상 메서드만 작성할 수 있다.
		 * 	 인터페이스에 작성된 모든 메서드는 abstract 키워드를 생략할 수 있다.
		 * 	 인터페이스에 작성된 모든 멤버 변수는 public static final 생략되어 있다.
		 * 
		 * - 메서드의 통일성(일관성)을 부여하기 위해 사용한다.
		 * - 인터페이스를 상속하는 클래스는 implements 를 사용
		 * - 인터페이스는 다중 상속이 가능하다.
		 * - 인터페이스로 객체 생성은 할 수 없지만, 참조 타입으로는 사용할 수 있다.(업캐스팅, 다운캐스팅)
		 */
		
		// Shape s = new Shape();	-> 인터페이스는 이렇게 객체 생성 불가능
		
		// 참조변수에 자식 다른 객체 넣는 건 가능 
		Shape s1 = new Circle();
		Shape s2 = new Square();
		
	}

}
