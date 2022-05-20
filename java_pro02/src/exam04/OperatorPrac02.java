package exam04;

public class OperatorPrac02 {

	public static void main(String[] args) {
		//산술 연산자 
		// +, -, *, /(나누기), %(나머지)
		int num1, num2;
		
		num1 = 10; num2 = 3;
		// 정수형 변수를 가지고 계산을 하면 결과도 정수
		System.out.println("--------------- 산술연산자 -----------");
		System.out.printf("%d + %d = %d\n", num1, num2, num1 + num2);
		System.out.printf("%d - %d = %d\n", num1, num2, num1 - num2);
		System.out.printf("%d * %d = %d\n", num1, num2, num1 * num2);
		System.out.printf("%d / %d = %f\n", num1, num2, (double)num1 / num2);  //형변환 필요
		System.out.printf("%d %% %d = %d\n", num1, num2, num1 % num2); 
		// %를 나머지 연산 기호로 사용하고 싶지만 의미가 중복된다. -> 이럴 때 escape 문자 필요 -> %%
		
		//비교 연산자
		// ==(같다), !=(다르다), <(왼쪽 피연산자 값이 작다), >(왼쪽 피연산자 값이 크다)
		// <=(왼쪽 피연산자 값이 작거나 같다), >=(왼쪽 피연산자 값이 크거나 같다)
		// 비교 연산의 결과는 true 또는 false의 논리 값이다.
		
		System.out.println("--------------- 비교연산자 -----------");
		
		num2 = 10;  //num2만 값 변경해보기
		System.out.printf("%d == %d -> %b\n", num1, num2, num1 == num2);
		System.out.printf("%d != %d -> %b\n", num1, num2, num1 != num2);
		System.out.printf("%d < %d -> %b\n", num1, num2, num1 < num2);
		System.out.printf("%d > %d -> %b\n", num1, num2, num1 > num2);
		System.out.printf("%d <= %d -> %b\n", num1, num2, num1 <= num2);
		System.out.printf("%d >= %d -> %b\n", num1, num2, num1 >= num2);
	
		//논리 연산자
		// &&(and 연산), ||(or 연산)
		// true, false 논리 값을 가지고 연산을 수행해야 한다.
		System.out.println("-------- <and논리연산자> -------");
		System.out.printf("%b && %b -> %b\n", true, true, true && true);
		System.out.printf("%b && %b -> %b\n", true, false, true && false);
		System.out.printf("%b && %b -> %b\n", false, true, false && true);
		System.out.printf("%b && %b -> %b\n", false, false, false && false);
		
		System.out.println("-------- <or논리연산자> -------");
		System.out.printf("%b || %b -> %b\n", true, true, true || true);
		System.out.printf("%b || %b -> %b\n", true, false, true || false);
		System.out.printf("%b || %b -> %b\n", false, true, false || true);
		System.out.printf("%b || %b -> %b\n", false, false, false || false);
		
		
	}

}
