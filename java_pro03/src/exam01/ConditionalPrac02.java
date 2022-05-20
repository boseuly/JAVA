package exam01;

import java.util.Scanner;

public class ConditionalPrac02 {

	public static void main(String[] args) {
		/*
		 * 1. 사용자 입력을 통해 정수값을 입력받아 다음의 문제를 해결하시오
		 * 
		 * 사용자 입력값의 범위를 설정하여 입력값의 범위를 초과하는 경우 주의 메시지를 출력한다.
		 * 입력값의 범위 : 1 ~ 99
		 * 입력값의 범위를 벗어난 경우 "1 ~ 99 사이의 값을 입력하시오." 라는 메시지를 출력
		 * 올바른 입력값의 범위를 입력한 경우 "정수값이 정상적으로 입력되었습니다." 라는 메시지를 출력
		 */
		
		// 1번 문제 내가 푼 거
		Scanner sc = new Scanner(System.in);
		System.out.print("1 ~ 99 사이의 정수값을 입력하시오 : ");
		int num1 = sc.nextInt();
		
		if(num1 > 0 && num1 < 100) {    //비교연산자가 먼저 이뤄지고 논리연산자 이뤄진다
			System.out.println("정수값이 정상적으로 입력되었습니다.");
		}else {
			System.out.println("1 ~ 99 사이의 값을 입력하시오.");
		}
		
		System.out.println();  //개행
		
		/* 
		 * 2. 0 ~ 100 사이의 정수값을 입력 받아 다음 조건에 해당하는 경우 적절한 메시지를 출력하도록 한다.
		 * 
		 * 입력된 정수 값이 0 ~ 39 사이의 값인 경우 "과락입니다." 메시지 출력
		 * 입력된 정수 값이 40 ~ 59 사이의 값인 경우 "E 등급입니다." 메시지 출력
		 * 입력된 정수 값이 60 ~ 69 사이의 값인 경우 "D 등급입니다." 메시지 출력
		 * 입력된 정수 값이 70 ~ 79 사이의 값인 경우 "C 등급입니다." 메시지 출력
		 * 입력된 정수 값이 80 ~ 89 사이의 값인 경우 "B 등급입니다." 메시지 출력
		 * 입력된 정수 값이 90 ~ 100 사이의 값인 경우 "A 등급입니다." 메시지 출력
		 */
		
		// 2번 문제 내가 푼 거
		Scanner sc1 = new Scanner(System.in);
		System.out.print("0 ~ 100 사이의 정수값을 입력하시오 : ");
		int num2 = sc1.nextInt();
		
		if(num2 >= 0 && num2 <= 100) {
			if(num2 <= 39) {
				System.out.println("과락입니다.");
			}else if(num2 <= 59) {
				System.out.println("E 등급입니다.");
			}else if(num2 <= 69) {
				System.out.println("D 등급입니다.");
			}else if(num2 <= 79) {
				System.out.println("C 등급입니다.");
			}else if(num2 <= 89) {
				System.out.println("B 등급입니다.");
			}else if(num2 <= 100) {
				System.out.println("A 등급입니다.");
			}
		}else {
			System.out.println("0 ~ 100 사이의 정수값을 입력하시오.");
		}
		
		// 다른 방법
		/*
		String result;
		int score;
		
		result = "";   // 지역변수임, 지역변수는 초기화 후 사용할 수 있음, 그래서 ""로 임의로 초기화
		
		System.out.print("점수를 입력하세요(0 ~ 100) : ");
		score = sc.nextInt();
		
		if(score <= 39){
			result = "과락입니다.";
		}else if(score <= 59){
			result = "E 등급입니다.";
		}else if(score <= 69){
			result = "D 등급입니다.";
		}else if(score <= 79){
			result = "C 등급입니다.";
		}else if(score <= 89){
			result = "B 등급입니다.";
		}else if(score <= 100){
			result = "A 등급입니다.";
		}
		System.out.println(result);
		
		*/
		
	}

}
