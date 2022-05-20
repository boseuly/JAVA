package exam02;

public class Main {

	public static void main(String[] args) {
		Person p = new Person();
		p.setName("이름");
		p.setGender('성');
		p.setAge(0);
		System.out.println(p);
		
		Person p1 = new Person();
		Person p2 = new Person();
		
		p1.setName("김정수");		p1.setAge(20);	p1.setGender('W');
		p2.setName("김정수");		p2.setAge(20);	p2.setGender('W');
		
		
		System.out.println(p1.equals(p2));
		
		
		
		
		Student s = new Student();
		s.setName("홍길이");
		s.setAge(18);
		s.setGender('W');
		s.setClassLevel(2);
		s.setClassRoom(6);
		
		Teacher t = new Teacher();
		t.setName("박지성");
		t.setAge(40);
		t.setGender('W');
		t.setClassLevel(2);
		t.setClassRoom(6);
		t.setSubject("수학");
		
	
	}

}
