package exam03;

public class Main {

	public static void main(String[] args) {
		
		Subject s1 = new Subject("국어");
		Subject s2 = new Subject("영어", 78.3);
		
		System.out.println(s1.getName());
		System.out.println(s2.getName());
		
		System.out.println(s1.getGrade());
		System.out.println(s2.getGrade());
		System.out.println(s2.getGrade().getPoint());
		
		
	}

}
