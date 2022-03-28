package exam01;

public class Child extends Parent {
	// 부모 생성자가 매개변수 있는 생성자라면 부모의 매개변수 있는 생성자를 자식생성자에 넣어야 됨
	// 이 방법보다 쉬운 방법은 부모클래스에 기본 생성자를 만드는 것이다.
	public Child() {	 // 부모클래스에 기본 생성자를 안 만들면 이렇게 됨
		super(1);
	}
	
	public void printNumber() {	//number는 부모의 멤버변수임
		setNumber(2);
		System.out.println(getNumber());
	}
	/*
	 *  오버라이딩
	 *  부모클래스의 메소드를 추가/재정의 할 수 있음
	 */
	 
	@Override	// 오버라이딩이 가능한 메서드인지 확인 해준다.
	public void setNumber(int number) {
		System.out.println("자식의 setNumber 메서드 동작 시작");
		super.setNumber(number);  // 부모 메서드를 호출함 -> 부모 메서드 실행 뒤
		System.out.println("자식의 setNumber 메서드 동작 끝");	// 이 문구 실행
	}
	
}
