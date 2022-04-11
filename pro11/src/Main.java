import java.util.Scanner;

import game.card.Bawi;
import game.card.Bo;
import game.card.Gawi;
import game.card.Hand;
import game.player.ComPlayer;
import game.player.UserPlayer;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UserPlayer uPlay = new UserPlayer(null);
		ComPlayer cPlay = new ComPlayer();
		Hand userHand, comHand;
		
		System.out.println("가위 바위 보 게임 입니다.");
		System.out.println("이름을 입력해주세요 : ");
		
		System.out.println("계속 진행하려면 Enter 키를 입력하세요.");
		System.out.println(">>> ");
		
		sc.nextLine();
		
		// 메뉴를 만들어서 
		while(true) {
			System.out.print("가위 바위 보 중 하나를 입력하세요.\n");
			System.out.print("아무 값도 입력하지 않고 Enter 키를 누르거나 입력 값이 틀린 경우\n");
			System.out.print("임의의 값으로 진행 됩니다.\n");
			System.out.print("종료를 원하는 경우 \"종료\"라고 입력하세요.\n");
			System.out.print(">>> ");
			String pInput = sc.nextLine();
			
			uPlay.setCardHand(pInput);
			cPlay.randomCardHand();
			
			switch(uPlay.versus(cPlay.getHand())) {
				case -1:
					System.out.println("플레이어 패!");
					uRecord.addLose();
					break;
				case 0:
					System.out.println("무승부"); 
					uRecord.addDraw();
					break;
				case 1:
					System.out.println("플레이어 승!"); 
					uTecord.addWin();
					break;
			}
			
			switch(cPlay.versus(comHand, userHand)) {
				case -1:
					System.out.println("컴퓨터 패!"); break;
				case 0:
					System.out.println("무승부"); break;
				case 1:
					System.out.println("컴퓨터 승!"); break;
			}
			
		}
	}

}
