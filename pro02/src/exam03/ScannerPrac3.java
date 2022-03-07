package exam03;

import java.util.Scanner;

public class ScannerPrac3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("첫 번째 정수값 입력 : ");
		int num1 = sc.nextInt();
		
		System.out.print("두 번째 정수값 입력 : ");
		int num2 = sc.nextInt();
		
		int result = num1 + num2;   //num1,2를 사용자에게 입력 받아 더하기
		
		System.out.printf("결과 : %d", result);
	}

}
