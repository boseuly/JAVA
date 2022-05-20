package game.menu;

import java.util.Scanner;

import game.db.PenaltyDatabase;

public class SettingMenu {
	private Scanner sc = new Scanner(System.in);
	private PenaltyDatabase pDB;
	
	public SettingMenu() {				//	클래스 생성할 때 패널티랑 스캐너도 생성
		this.pDB = new PenaltyDatabase();	
		this.sc = new Scanner(System.in);
	}
	
	
	public void show() throws InterruptedException {
		String menu = "";
		menu += "<<<<< UP & DOWN >>>>>\n";
		menu += "                     \n";
		menu += "┌───────────────────┐\n";
		menu += "│ 1.Penalty Add     │\n";		// 벌칙 추가
		menu += "│ 2.Penalty Modify  │\n";		// 벌칙 수정
		menu += "│ 3.Pemalty Remove  │\n";		// 벌칙 삭제
		menu += "│ 4.Back            │\n";		// 이전으로
		menu += "└───────────────────┘\n";
		menu += ": ";
		
		while(true) {
			System.out.println(menu);
			String input = sc.nextLine();
			
			switch(input.charAt(0)) {
			case '1':
				addMenu();
				break;
			case '2':
				modifyMenu();
				break;
			case '3':
				removeMenu();
				break;
			case '4':
				System.out.println("이전 메뉴로 돌아갑니다.");
				Thread.sleep(500);
				return;
			default:
				System.out.println("잘못된 번로를 입력하였습니다. 다시 입력하세요.");
				System.out.println("Enter 키를 입력하면 다시 메뉴 화면이 나옵니다.");
				sc.nextLine();
			}
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			
		}
	}
		
	

	private void addMenu() {
		String menu = "";
		menu += "추가할 벌칙을 작성하세요. 작성을 중단하려면 exit 를 입력하세요.\n";
		menu += ": ";
		
		while(true) {
			System.out.println(menu);
			String input = sc.nextLine();
			
			if(input.equals("exit")) {
				System.out.println("추가 작업을 종료하고 이전 메뉴로 돌아 갑니다.");
				return;		// 이전 메뉴로 돌아가는 작업
			}
			pDB.add(input);
			System.out.println("입력한 벌칙을 저장하였습니다.");
			
			
			
		}
		
	}
	// 모든 프로그램은 pui 기반 gui는 덮어씌우기밖에 없다..
	// 파일에 저장된 문자들을 불러와서 배열로 가져옴 -> 배열 데이터 수정 -> 배열의 데이터로 덮어쓰기 -> 사용자에게 보여주기(이게 수정의 원리)
	private void modifyMenu() {
		int numberMax = penaltyListUp();		// 호출, 반환값은 int(패널티 배열 길이를 반환)
		
		System.out.println("위 목록 중에서 수정할 벌칙을 선택하세요.");
		System.out.print(": ");
		
		int number;
		while(true) {
			if(sc.hasNextInt()) {	// 숫자가 더 있다면
				number = sc.nextInt(); sc.nextLine();
				if(number > 0 && number <= numberMax) {
					break;
				}
				System.out.printf("입력 번호는 1 ~ %d까지 입니다.\n", numberMax);
				
			}else {					// 숫자가 없다면 
				System.out.println("다시 입력하세요.");
				sc.nextLine();
			}
			System.out.println(": ");
		}
		System.out.println("벌칙을 입력하세요.");		// 무슨 벌칙으로 수정할 건지 적기
		System.out.print(": ");
		String penalty = sc.nextLine();
		
		// 해당 number-1를 이 penalty로 수정해라
		pDB.modify(number-1, penalty);			// -1을 한 이유 : modify로 넘어갈 때 index에 해당하기 때문에 인덱스 범위 오류가 남 -> 길이와 위치의 차이 때문에
		System.out.println("입력한 벌칙으로 수정이 완료되었습니다.");	
		System.out.println("Enter 키를 누르면 메인 메뉴로 돌아갑니다.");
		sc.nextLine();
	}
	
	

	private void removeMenu() {		// 삭제, 수정 할때는 사용자에게 기존의 벌칙을 보여주기까지 해야 한다. -> 번호를 입력해서 삭제하도록
		int numberMax = penaltyListUp();		// 호출, 반환값은 int(패널티 배열 길이를 반환)
		
		System.out.println("위 목록 중에서 수정할 벌칙을 선택하세요.");
		System.out.print(": ");
		
		int number;
		while(true) {
			if(sc.hasNextInt()) {	// 숫자가 더 있다면
				number = sc.nextInt(); sc.nextLine();
				if(number > 0 && number <= numberMax) {
					break;
				}
				System.out.printf("입력 번호는 1 ~ %d까지 입니다.\n", numberMax);
				
			}else {					// 숫자가 없다면 
				System.out.println("다시 입력하세요.");
				sc.nextLine();
			}
			System.out.println(": ");
		}
		pDB.remove(number-1);
		System.out.println("선택한 벌칙 삭제가 완료되었습니다.");	
		System.out.println("Enter 키를 누르면 메인 메뉴로 돌아갑니다.");
		sc.nextLine();
		
	}
	
	
	// 벌칙 리스트 목록 가져오기
	private int penaltyListUp() {
		String[] penaltys = pDB.getList();				// 목록을 가져와서 String[]에 넣어라 -> 이건 얕은 복사(?)
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < penaltys.length; i++) {
			sb.append(String.format("%d. %s\n", i + 1, penaltys[i]));	// 숫자로 번호 매기기 위해서 StringBilder를 이용해서 값추가
			
		}
		System.out.print(sb.toString());
		return penaltys.length;		// 패널티 배열의 길이
	}
	

	
}
