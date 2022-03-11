package exam02;

import java.util.Random;
import java.util.Scanner;

public class Random01 {

	public static void main(String[] args) {
		/*
		 * 난수값(랜덤값) 생성
		 * 	import java.util.Random; 임포트한 후 다음의 코드 작성
		 */
	/*	
		Random random = new Random();
		
		for(int i = 0; i < 10; i++) {
			System.out.print(random.nextInt(10) + "\t");  //0~9사이의 난수값 생성;
		}
		
	*/	
		
		//내가 푼 건데 완성도가 낮음
	/*	
		//선언부 
		Random random = new Random();
		String name;
		String pak; //사용자가 낸 거
		int ran;    //컴퓨터 랜덤값
		
		
		
		//로직 & 연산
		Scanner sc = new Scanner(System.in);
		System.out.print("당신의 이름을 입력해주세요 : ");
		name = sc.nextLine();
		
		
		for( ; ; ) {     //이렇게 하면 무한반복
			System.out.print("가위바위보 : ");
			pak = sc.nextLine();
			ran = random.nextInt(2);
			
			if(pak.equals("exit")) {  //exit equals 사용해서 시스템 멈추기
				break;
			}
			
			if(ran == 0) {
				System.out.println("컴퓨터 : 바위");
			}else if(ran == 1) {
				System.out.println("컴퓨터 : 가위");
			}else if(ran == 2) {
				System.out.println("컴퓨터 : 보");
			}else
				System.out.println("잘못 입력하셨습니다.");
			
		}
	*/	
		
		//강사님 풀이
		
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		
		String name, player, sCom = "";
		int computer;
		int result = 0;		// 이기면 1, 지면 -1, 비기면 0
		int wCnt = 0, dCnt = 0, lCnt = 0;
		
		System.out.print("당신의 이름을 입력하세요 : ");
		name = sc.nextLine();
		
		for(;;) {
			System.out.print("가위 바위 보 입력 : ");
			player = sc.nextLine();
			computer = random.nextInt(3);
			
			if(computer == 0) {
				sCom = "가위";
			} else if(computer == 1) {
				sCom = "바위";
			} else if(computer == 2) {
				sCom = "보";
			}
			if(player.equals("exit")) {
				System.out.printf("%d전 %d승 %d패", wCnt + dCnt + lCnt, wCnt,dCnt,lCnt);
			}
			
			if(player.equals("exit")) {
				break;
			} else if(player.equals("가위")) {
				if(computer == 0) {
					result = 0;		dCnt++;
				} else if(computer == 1) {
					result = -1;	lCnt++;
				} else if(computer == 2) {
					result = 1;		wCnt++;
				}
			} else if(player.equals("바위")) {
				if(computer == 0) {
					result = 1;		wCnt++;
				} else if(computer == 1) {
					result = 0;		dCnt++;
				} else if(computer == 2) {
					result = -1;	lCnt++;
				}
			} else if(player.equals("보")) {
				if(computer == 0) {
					result = -1;	lCnt++;
				} else if(computer == 1) {
					result = 1;		wCnt++;
				} else if(computer == 2) {
					result = 0;		dCnt++;
				}
			} else {
				System.out.println("잘못 입력하였습니다.");
				continue;
			}
			
			System.out.printf("컴퓨터 : %s\n%s : %s\n", sCom, name, player);
			switch(result) {
				case -1:
					System.out.println("졌습니다.");
					break;
				case 0:
					System.out.println("비겼습니다.");
					break;
				case 1:
					System.out.println("이겼습니다.");
			}
		}
		
		
		
	}

}
