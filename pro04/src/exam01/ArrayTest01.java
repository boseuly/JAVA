package exam01;

import java.util.Random;
import java.util.Scanner;

public class ArrayTest01 {

	public static void main(String[] args) {
		int computer;
		String name, player, sCom = "";
		int result = 0;
		int[] record = new int[3];
		
		Scanner sc= new Scanner(System.in);
		System.out.print("당신의 이름을 입력하세요 : ");
		name = sc.nextLine();
		
		Random random = new Random();
		
		for(;;) {
			System.out.print("가위바위보 입력 : ");
			player = sc.nextLine();
			computer = random.nextInt(3);
			
			if(computer == 0) {
				
			}
		}
		
		
	}

}
