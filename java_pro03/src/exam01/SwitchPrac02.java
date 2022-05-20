package exam01;

import java.util.Scanner;

public class SwitchPrac02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("1~9까지 숫자 입력 : ");
		int m = sc.nextInt();
		
		// break 활용하기
		switch(m) {
		case 1:
		case 2:
		case 3:
			System.out.println("1, 2, 3 중 하나를 입력했습니다.");
			break;
			
		case 4:
		case 5:
		case 6:
			System.out.println("4, 5, 6 중 하나를 입력했습니다.");
			break;
		case 7:
		case 8:
		case 9:
			System.out.println("7, 8, 9 중 하나를 입력했습니다.");
					
				
		}
		
		
		
	}

}
