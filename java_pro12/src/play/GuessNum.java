package play;

import java.util.Random;
import java.util.Scanner;

public class GuessNum {
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();			// 임의의 정수값 만들기
	
	
	private int num = 0;				// 사용자가 낸 숫자 저장할 변수

	
	// play를 따로 만들어서 여기서 계속 프로그램이 진행되도록 한다.
	public void play() {
		int randomNum = rand.nextInt(100);	// 0~100까지의 숫자 저장
		System.out.println("UP & DOWN 게임 시작");
		System.out.print("임의의 숫자가 생성되었습니다.");
		while(true) {
			System.out.print("숫자 입력 : ");
			this.num = sc.nextInt();		// 사용자한테 숫자 입력 받기
			
		}
		
		
		
		
	}
		
	
	
	
	
	
}
