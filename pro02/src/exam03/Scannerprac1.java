package exam03;

import java.util.Scanner;

public class Scannerprac1 {

	public static void main(String[] args) {
		/*
		 * 사용자 입력을 받기 위한 Sanner 르래스
		 * - 기본적으로 모든 사용자 입력 데이터는 문자열이다.
		 */
		
		//Scanner 객체 생성
		Scanner sc = new Scanner(System.in);
		
		//정수값 입력받기
		System.out.print("정수를 입력하시오 : ");
		int iInput = sc.nextInt();   //nextInt -> 정수만 입력할 수 있음
		System.out.println("사용자 입력값 : " + iInput);
		System.out.printf("사용자 입력값 : %d\n", iInput);
		
		//실수값 입력받기
		System.out.print("실수를 입력하시오 : ");
		double dInput = sc.nextDouble();
		System.out.println("사용자 입력값 : " + dInput);
		System.out.printf("사용자 입력값 : %f\n", dInput);
		
		
	}

}
