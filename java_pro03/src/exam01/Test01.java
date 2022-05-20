package exam01;

import java.util.Scanner;

public class Test01 {

	public static void main(String[] args) {
		/* 
		 *  환율 계산 프로그램 
		 *                원달러 환율 값 입력 : 1235.00 
		 * 
		 *                달러를 원화로 계산하겠습니까? 아니면 원화를 달러로 계산하겠습니까?   //여기서 if문 사용
		 ​*                (1:달러->원화, 2:원화->달러)                            //만약 달러 -> (실행문1),   만약 원화 -> (실행문2)
		 * 
		 *                달러값 입력 : 100 
		​ *                100 달러는 123500 원 입니다. 
		 * 
		 *                원화값 입력 : 100000 
		 ​*                100000 원은 약 80.97 달러 입니다. 
	     */
		double rate;
		int menuNumber;
		int doller, won;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("원달러 환율 값 입력 : ");
		rate = sc.nextDouble();                   //환율값이 소수점 자리까지 나올 수 있으니까 double
		
		System.out.println("달러를 원화로 계산하겠습니까? 아니면 원화를 달러로 계산하겠습니까?");
		System.out.print("(1: 달러 -> 원화, 2: 원화 -> 달러) -> ");
		menuNumber = sc.nextInt();
		
		if(menuNumber == 1) {
			// 달러 -> 원화
			System.out.print("달러값 입력 : ");
			doller = sc.nextInt();
			won = (int)(doller * rate);   //강제형변환을 할 때는 결과값에 문제가 생기는지 확인하고 변환하기
			System.out.printf("%d 달러는 %d 원입니다.", doller, won);
		}else if(menuNumber == 2) {
			System.out.print("원화값 입력 : ");
			won = sc.nextInt();
			System.out.printf("%d 원은 약 %.2f 달러입니다.", won, won / rate);  //얘는 소수점까지 출력을 해야해서 그냥 직접 계산함
		}
		
	}

}
