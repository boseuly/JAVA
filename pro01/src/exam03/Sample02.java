package exam03;

public class Sample02 {

	public static void main(String[] args) {
		/*
		 *	지역 변수 사용할 때 주의사항
		 * 	지역 변수 사용할 때는 초기화가 되어 있어야 한다. 
		 */
		
		int i1;  //선언만 한 상태
		i1=1;
		System.out.println(i1);
		
		
		/*
		 *  상수 : 변하지 않는 값을 의미한다.
		 *  자바 프로그램에서는 한 번 초기화를 한 후에는 변경되지 않는 것을 의미
		 * 
		 *	형식 : final 자료형 변수명 = 값; 
		 */
		
		final int number;  //상수
		number = 2;
		
		/*
		 * 	변수명 명명 규칙
		 * 	1. 한글 사용 X
		 * 	2. 띄어쓰기 금지, 대신 _(언더바) 사용
		 * 	3. 숫자는 영문자 뒤에 적기
		 * 	4. 숫자는 영문자 뒤에 작성
		 *  5. 예약어를 사용하면 안 됨
		 *  6. 자바에서는 $ 특수문자도 사용할 수 있다.
		 *  
		 *  권고 사항
		 *  
		 *  상수명을 작명할 때 반드시 대문자로만 작명할 것.
		 * 
		 */
		int i = 1;
		int I = 1;
		
		String str = "기차" + 123 + 45 + "출발";
		System.out.println(str);
		
		String str1 = 123 + 456 + "기차" + "출발";
		System.out.println(str1);
		
		
		
	}

}
