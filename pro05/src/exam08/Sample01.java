package exam08;

import java.util.Random;

public class Sample01 {

	public static void main(String[] args) {
		MethodSample m = new MethodSample();
		m.method01();   //메서드를 호출 -> 메서드 실행 
		// System.out.println(m.method01()); -> 반환형이 void이기때문에 여기서 출력구문 사용 불가!!
	
		int r1 = m.method02();		//반환타입이 있는 건 객체를 만들어서 객체에 return값을 넣을 수 있다.
		int[] r2 = m.method03();
		String r3 = m.method04();
		
		int[] arg1 = new int[] {1,2,3};	// 배열 하나 만들기
		System.out.println(arg1);		// 배열 값을 출력하기 
		m.method05(arg1);				// 매개변수 arr에 {1,2,3}을 넣기
		
		Random ran = new Random();	// 매개변수가 Random타입이기 때문에 Random타입인 ran객체 생성
		m.method06(ran);			// 그렇게 만든 ran을 method06의 매개변수에 대입
		
		m.method07();
		m.method07(1,2,3,4);	
		m.method07(1,2,3,4,6);
		m.method07(1,2,3,4,5,6,6,7,8);
	}

}
