package exam03.pack1;
	
public class AccessMain extends AccessParent {		//AccessChild -> 부모클래스
	
	public int v1;
	protected int v2;
	int v3;
	private int v4;
	
	
	public void method() {
		// 해당클래스 내부에 있는 멤버변수 접근
		System.out.println(v1);		
		System.out.println(v2);		
		System.out.println(v3);
		System.out.println(v4);
		
		// 다른 클래스, 같은 패키지 그리고 AccessParent의 후손
		System.out.println(p1);		// public 가능
		System.out.println(p2);		// protected 가능
		System.out.println(p3);		// default 가능 (같은 패키지라서)
		//System.out.println(p4);		//private -> 같은 클래스 내에서만 사용 O
	}
}
