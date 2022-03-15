package exam01;

import java.util.Random;

public class Array06 {

	public static void main(String[] args) {
		/*
		 * 가위 바위 보 게임에서 컴퓨터가 생성한 정수를 가위, 바위, 보 문자열로
		 * if문을 사용하여 치환했던 작업을 배열로 해결함
		 */
		
		
		Random rand = new Random();
		String[] convert = {"가위", "바위", "보"}; // 0번 인덱스: 가위, 1번 인덱스 : 바위, 2번 인덱스 : 보
		String sCom = "";
		int res = rand.nextInt(3);
		
		System.out.println("컴퓨터 : " + convert[res]);
		
	}

}
