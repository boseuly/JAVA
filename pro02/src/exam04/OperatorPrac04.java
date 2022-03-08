package exam04;

import java.util.Scanner;

public class OperatorPrac04 {

	public static void main(String[] args) {
		/*
		 * 섭씨 화씨 변환
		 * - 사용자 입력으로 섭씨 값을 받는다.(단, 정수값만 입력받는다.)
		 * - 입력받은 섭씨를 화씨로 변환하여 출력(실수 결과로 출력한다.)
		 * 
		 * 섭씨 -> 화씨 변환공식
		 * (섭씨 * (9 / 5)) + 32 = 화씨
		 */
		
		
		//내가 짠 코드
		Scanner sc = new Scanner(System.in);
		System.out.print("섭씨를 입력하시오 : ");
		int num1 = sc.nextInt();
		
		double num2 = ((double)num1 * 9/5) + 32;   //double num2 = ((double)num1 * 9/5) + 32; 식도 가능
		System.out.printf("화씨는 %.2f℉ 입니다.", num2);
		
		
		System.out.println();  //개행

		//강사님 코드
		int n1;
		double n2;
		
		System.out.print("℃를 ℉로 변환합니다.\n온도(℃)를 입력하세요 : ");
		n1 = sc.nextInt();
		
		n2 = (n1 * 9.0 / 5.0) + 32;
		
		System.out.printf("화씨 변환 값 : %.2f℉", n2);
		
		
	}

}
