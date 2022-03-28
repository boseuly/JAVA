package exam04;

public class Main {

	public static void main(String[] args) {
		Premium p1 = new Premium();
		Premium p2 = new
				Premium();
		
		p1.setName("이보슬");		p1.setAge(24);	p1.setGender('W');
		p2.setName("이해슬");		p2.setAge(23);	p2.setGender('W');
		
		p1.sum(11200000);
		p1.sum(1000000);
		p1.discountPrice(3000000);
		System.out.println(p1);
		System.out.println(p2);
		System.out.println("<일반 고객일 경우>");
		p2.discountPrice(20000000);
		p2.sum(10000000);
		System.out.println("<프리미엄 고객일 경우>");
		p2.discountPrice(400000);
		System.out.println(p2);
	}

}
