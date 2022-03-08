package exam01;

import java.util.Scanner;

public class ConditionalPrac01 {

	public static void main(String[] args) {
		/*
		 *  조건문 
		 *  - 프로그램 코드의 실행을 제어하기 위한 제어문 중 하나이다.
		 *  - 조건에 따라 코드를 실행하거나 실행하지 않게 제어를 한다.
		 *  - 조건문의 종류 : if, if ... else, if... else if, switch 
		 */
		
		/*
		 * if 문
		 * if(조건식) {
		 *      실행문
		 *  }
		 *  성수역 3출 센터 목요일 16:30
		 */
		
		int num = 10;
		
		if(num >= 10) {
			System.out.println("num 변수에 저장된 값은 10보다 크거나 같습니다.");
		}
		
		/*
		 * if ... else 문
		 * 조건식의 결과가 거짓일 때 수행할 코드가 있는 경우 사용하면 됨
		 * 
		 * if(조건식) {
		 * 		참일 때 실행문
		 * }else {
		 * 		거짓일 때 실행문
		 * } 
		 *
		 */
		if(num % 2 == 0) {
			System.out.println("num 변수에 저장된 값은 짝수입니다.");
		}else {
			System.out.println("num 변수에 저장된 값은 홀수입니다.");
		}
		
		/*
		 * if ... else if문
		 * if 문에서 검사해야 하는 조건이 여러개 존재하는 경우 사용하면 된다.
		 * 
		 * if(조건식1){
		 * 		실행문
		 *  }else if(조건식2){
		 *  	실행문
		 *  }else if(조건식3){
		 *  	실행문
		 *  } ... {
		 *  	실행문
		 *  }else {
		 *  	실행문
		 *  }
		 *  
		 */
		
		System.out.println("---- <if else if문> ----");
		num = 48;
		if(num <= 19) {
			System.out.println("미성년자입니다.");
		}else if(num <= 29) {
			System.out.println("20대입니다.");
		}else if(num <= 39) {
			System.out.println("30대입니다.");
		}else if(num <= 49) {
			System.out.println("40대입니다.");
		}else if(num <= 59) {
			System.out.println("50대입니다.");
		}else {
			System.out.println("국민연금을 수령할 나이대입니다.");
		}
		// 주의사항 : 첫 번째 조건이 참이면 나머지 조건은 보지도 않고 넘어간다.
		
		/*
		 * 중첩 if 문
		 * 문장 안에 또 다른 문장이 중첩되어 작성되는 형태
		 * 
		 * if(조건식1){
		 * 		if(조건식2){
		 * 		  	실행문
		 * 		}
		 * }else{
		 * 		if(조건식3){
		 * 			실행문
		 * 		}else{
		 * 			실행문
		 * 		}
		 * }
		 * 
		 */
		System.out.println("---- <중첩if문> ----");
		num = 13;
		
		if(num <= 19) {
			if(num >= 13 && num <= 15) {
				System.out.println("중학생입니다.");
			}else if(num >= 16 && num <= 19){
				System.out.println("고등학생입니다.");
			}else {
				System.out.println("유아/초등학생입니다.");
			}
		}else if(num <= 29) {
			System.out.println("20대입니다.");
		}else if(num <= 39) {
			System.out.println("30대입니다.");
		}else if(num <= 49) {
			System.out.println("40대입니다.");
		}else if(num <= 59) {
			System.out.println("50대입니다.");
		}else {
			System.out.println("국민연금을 수령할 나이대입니다.");
		}
		
		
	}

}
