package exam01;

public class Sample01 {

	public static void main(String[] args) {
		
		/*
		 * 형변환(Casting) : 자료형을 변환하기 위해 사용
		 * <값 처리 원칙>
		 * - 같은 자료형만 대입을 할 수 있다.
		 * - 같은 자료형만 계산(연산)할 수 있다.
		 * - 계산(연산) 결과도 같은 종류의 값이 나와야 한다.
		 *
		 * 자료형 변환 종류(2가지)
		 * - 자동 형변환 : 자료형의 크기가 작은 자료형에서 큰 자료형으로 변환할 때에는 자동으로 이뤄진다.
		 * - 강제 형변환 : 자료형의 크기가 큰 자료형에서 작은 자료형으로 변환할 때에는 강제 변환시켜야 한다.
		 * 
		 * 강제 형변환 방법 
		 * - 자료형 변환할 값 앞에 변환 타입을 작성한다. -> "(자료형)값" 
		 * 
		 * 강제 형변환에서 주의사항
		 * - 데이터의 손실을 감수해야 한다.
		 * - boolean형은 형변환에서 제외, String 타입도 형변환에서 제외
		 */
		
		int i1 = 1000; //4byte
		byte b1;		//1byte
		short s1 = 10;
		char c1 = 'a';
		char c2 = 65;   //대문자 A 아스키 코드 값
		float f1 = 4.0f;
		
		b1 = (byte)i1;
		System.out.println(i1);  //1000
		System.out.println(b1);  //-24  데이터에 대한 손실이 일어남
		
		i1 = b1;  // 자동 형변환
		i1 = b1 + s1;  // 더 큰 자료형으로 자동 형변환이 이뤄진다. byte랑 short의 계산 결과는 무조건 int
		
		System.out.println(c1);
		System.out.println(c2);
		
		i1 = b1 + c1;
		
		int i2;
		i2 = (int)f1;  //float형을 int형에 강제 형변환
		i2 = 10+(int)f1;    		//int형으로 맞추는 법
		i2 = (int)((float)10+f1);	//float으로 10을 형변환하고 다시 int형으로 형변환
		
		
		
		
	}

}
