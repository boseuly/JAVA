package exam07;

public class Constructor {
	public int num1;
	public int num2;
	public int num3;
	public int num4;
	
	//생성자와 메소드 비슷하지만 차이점 존재
	//메소드 -> void 즉, 반환형이 존재 O
	public void method() {
		
	}
	
	//기본생성자 -> void 존재 X
	//매개변수 있는 생성자가 없는 경우 JVM이 자동 생성
	//매개변수가 있는 생성자가 있는 경우 JVM이 자동으로 생성해주지 않음 -> 직접 만들어줘야 함
	public Constructor(){
		
	}
	
	//매개변수 있는 생성자
	public Constructor(int num1) {
		//전달 받은 매개변수 값을 이용하여 초기화
		this.num1 = num1;
	}
	// this() -> 이전에 사용한 생성자 재사용
	public Constructor(int num1, int num2) {
		this(num1);
		this.num2 = num2;
	}
	public Constructor(int num1, int num2, int num3) {
		this(num1, num2);
		this.num3 = num3;
	}
	public Constructor(int num1, int num2, int num3, int num4) {
		this(num1, num2, num3);
		this.num4 = num4;
	}
	
	
	
	public Constructor(byte b) {
		System.out.println("byte");
	}
	public Constructor(char c) {			//매개변수에 char형이 들어오면 char가 출력됨
		System.out.println("char");
	}
	public Constructor(short s) {
		System.out.println("short");
	}
	public Constructor(long l) {
		System.out.println("long");
	}
	
	
	
	
	
}
