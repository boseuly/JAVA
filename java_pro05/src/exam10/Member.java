package exam10;

public class Member {
	private String memberld;
	private String memberPwd;
	private String memberName;
	private int age;
	private char gender;
	private String phone;
	private String email;
	
	public Member() {}
	
	//메소드
	public void changeName(String name) {
		memberName = name;
	}
	
	public void pringName() {
		System.out.println(memberName);
	}
	
}
