import java.util.Scanner;

import game.menu.SettingMenu;
import game.updown.Correct;
import game.updown.Fail;
import game.updown.GuessNum;
import game.updown.Result;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		/*
		 * 업앤다운
		 *     1. 임의의 숫자가 하나 정해진다.
		 *     2. 정해진 임의의 숫자를 맞춘다.
		 *        2-1. 정해진 숫자보다 낮은 숫자를 부르면 -> UP!
		 *        2-2. 정해진 숫자보다 높은 숫자를 부르면 -> DOWN!
		 *     3. 정해진 숫자를 맞추면 게임 종료
		 *        단, 정해진 횟수 안에서 맞추어야 한다.
		 */
		
		/*
		 * GuessNum 클래스
		 *     - 생성하면 바로 임의의 숫자를 만든다.
		 *     - 생성할 때 맞출 수 있는 횟수를 제한한다.
		 *     - 임의의 정수값을 전달하면 UP, DOWN 을 반환한다.
		 *     - 임의의 정수값과 GuessNum 클래스에 생성된 정수값이 동일하면 Correct 를 반환한다.
		 *     - 임의의 정수값이 전달되면 횟수를 차감한다.
		 *     - 모든 횟수가 차감되여 0 이 되면 Fail 을 반환하게 한다.
		 */
		
		// UP 클래스 : 클래스로만 생성하여 쓰도록 한다.
		// DOWN 클래스 : 클래스로만 생성하여 쓰도록 한다.
		// Correct 클래스 : 클래스로만 생성하여 쓰도록 한다.
		// Fail 클래스 : 클래스로만 생성하여 쓰도록 한다.
		Scanner sc = new Scanner(System.in);
		
		String mainMenu = "";
		mainMenu += "<<<<< UP & DOWN >>>>>\n";
		mainMenu += "                     \n";
		mainMenu += "┌───────────────────┐\n";
		mainMenu += "│ 1.Game Start!     │\n";
		mainMenu += "│ 2.Penalty Setting │\n";
		mainMenu += "│ 3.Exit            │\n";
		mainMenu += "└───────────────────┘\n";
		mainMenu += ": ";
		
		
		while(true) {
			System.out.println(mainMenu);		// 위에서 만든 메뉴 출력하기
			String input = sc.nextLine();		// 메뉴 입력 받기
			
			
			// switch 문은 int, char형만 가능 -> input은 문자열이니까 charAt을 이용해 한 글자만 추출한다. 
			switch(input.charAt(0)) {			// input은 String 이기 때문에 
			case '1':
				System.out.println("게임 진행을 위한 정보 로딩 중...");
				Thread.sleep(1000);		// sleep을 이용해서 사실 로딩 되는 척하기
				break;
			case '2':
				System.out.println("벌칙을 설정하기 위한 메뉴로 이동중...");
				Thread.sleep(1500);
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				SettingMenu sm = new SettingMenu();			// 이때 PenaltyDatabase 클래스 객체도 생성되어 파일이 같이 생성된다.
				sm.show();
				break;
			case '3':
				System.out.println("게임을 종료합니다.");
				System.exit(0);
			default :
				System.out.println("잘못된 번호를 입력하였습니다. 다시 입력하세요.");
				System.out.println("Enter 키를 입력하면 다시 메뉴 화면이 나옵니다.");
				sc.nextLine();
			}
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		}
		
		
		
		
		
		
		
		
//		
//		
//		while(true) {	// 게임이 반복되어야 하기 때문에 while, 
//			GuessNum game = new GuessNum();		// 게임이 새로 시작될 때 새로운 랜덤값이 만들어져야 하기 때문에 생성자도 while 안에
//			
//			while(game.remainCount()) {		// 만약 기회가 남았다면
//				System.out.print("임의의 숫자 입력 : ");
//				int num = sc.nextInt();
//				
//				Result res = game.guessing(num);
//				System.out.println(res);		// 반환형은 Result, 
//				
//				if(res instanceof Correct || res instanceof Fail) {
//					System.out.println("게임을 다시 시작합니다.");
//					break;
//				}
//			}
//		}
	}

}
