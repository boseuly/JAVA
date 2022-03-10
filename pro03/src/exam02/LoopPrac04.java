package exam02;

import java.util.Scanner;

public class LoopPrac04 {
	public static void main(String[] agrs) {
		Scanner sc = new Scanner(System.in);
		
		//선언부
		int menu;
		boolean existed = false;   // switch문에서 break는 switch만 break걸어주기 때문에 boolean을 이용해 for문을 break 해준다.
		
		System.out.println("다음의 메뉴 번호 중 하나를 입력하시오.");
		for(int i = 0; i<3; i++) {
			System.out.println("1. 조회");
			System.out.println("2. 추가");
			System.out.println("3. 수정");
			System.out.println("4. 삭제");
			System.out.println("9. 종료");
			System.out.println(": ");
			menu = sc.nextInt();
			switch(menu) {
			case 1: 
				System.out.println("조회 메뉴를 선택했습니다.");
				existed = true;
				break; //break는 가장 인접한 구문에 break가 걸린다 -> 여기서는 switch가 가장 가까움, for문은 break 안 걸어줌
			case 2: 
				System.out.println("추가 메뉴를 선택했습니다.");
				existed = true;
				break;
			case 3: 
				System.out.println("수정 메뉴를 선택했습니다.");
				existed = true;
				break;
			case 4: 
				System.out.println("삭제 메뉴를 선택했습니다.");
				existed = true;
				break;
			case 9: 
				System.out.println("프로그램을 종료합니다.");
				existed = true;
				break;
			default:
				System.out.println("잘못된 메뉴 번호입니다. 다시 입력하세요.");
			}
			if(existed) break;   // existed == true 라고 해도 되긴 하는데 굳이 쓸 필요 없음 //{} 생략은 한줄인 경우에만 가능
			
		}
		
	}
}
