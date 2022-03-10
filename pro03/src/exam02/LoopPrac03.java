package exam02;

import java.util.Scanner;

public class LoopPrac03 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//선언부
		int num;
		int count = 0;
		
		// 원하는 범위의 값이 입력될 때까지 반복. 단, 입력 횟수를 제한
		// 제한된 입력 횟수를 넘긴 경우 -> 입력 횟수를 초과하였습니다. 메시지 출력
		//내가 푼 거
		for(int i = 0; i < 3; i++) {
			System.out.print("1~5 사이의 정수값 입력 : ");
			num = sc.nextInt();
			count++;            //i=0 -> count=1, i=1 -> count=2, i=2 -> count=3
			if(num >= 1 && num <= 5) {
				break;
			}
			if(count >= 3) {
				System.out.println("입력 횟수를 초과하였습니다.");
			}
			
		}
		
	/*
		<강사님이 푼 거>
		
		for(int i = 0; i < 3; i++) {
			System.out.print("1~5 사이의 정수값 입력 : ");
			num = sc.nextInt();
			if(num >= 1 && num <= 5) {
				break;
			}
			if(i == 2) {
				System.out.println("입력 횟수를 초과하였습니다.");
			}
		}
		
	*/	
	}

}
