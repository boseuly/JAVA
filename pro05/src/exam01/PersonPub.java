package exam01;

public class PersonPub {
	
	// 추상화된 내용들을 클래스로 만들어냄
	private String pNo;
	private String name;
	private char gender;
	private String address;
	private String phone;
	private int age;
	
	public void method() {
		
	}
	
}
	// default 접근제한자 -> 다른 패키지에서 사용 불가, 웬만하면 public으로 만들어주기
class PersonDef {
	
	// 추상화된 내용들을 클래스로 만들어냄
	private String pNo;
	private String name;
	private char gender;
	private String address;
	private String phone;
	private int age;
	
	public void method() {
		
	}
	
	
}