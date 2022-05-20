package exam03;

import java.util.Scanner;

public class ScannerPrac2 {

	public static void main(String[] args) {
		// 사용자가 입력한 한 줄 데이터를 처리하기 위한 sc.nextLine()
		
		Scanner sc = new Scanner(System.in);
		System.out.print("문자열을 입력하시오 : ");
		String sInput = sc.next();  //next의 특징 : 공백 앞에 문장만 출력한다. 뒤에 문자열은 출력 X
		
		System.out.println("입력 문자열 : " + sInput );
		
		
		
		// 띄어쓰기가 포함된 긴 문자열인 경우에는 nextLine 사용
		Scanner sc1 = new Scanner(System.in);
		
		System.out.print("문자열을 입력하시오 : ");
		String sInput1 = sc1.nextLine();
		System.out.println("입력 문자열 : " + sInput1);
		
	}

}
