package exam03.pack2;

import exam03.pack1.AccessMain;

public class AccessTest2 {
	public static void main(String[] args) {
		// 다른 클래스, 다른 패키지에서 AccessMain클래스의
		// 멤버 변수에 접근 한 상황
		AccessMain m1 = new AccessMain();
		System.out.println(m1.v1);		// public 전체 접근 가능
		//System.out.println(m1.v2);		// protected 같은 클래스,패키지,후손 접근 가능 -> 패키지가 다름
		//System.out.println(m1.v3);		// default
		//System.out.println(m1.v4);		// private
	}
}
