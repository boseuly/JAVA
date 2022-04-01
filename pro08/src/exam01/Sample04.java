package exam01;

import java.util.StringTokenizer;

public class Sample04 {

	public static void main(String[] args) {
		/*
		 * StringTokenizer
		 * - String 클래스에서 제공하는 split() 메서드와 유사한 기능을 제공하는 클래스(split()보다 성능 좋음)
		 * - 특정기호를 구분자로 분리가된 문자열들을 토큰이라고 한다.
		 * 
		 */
		String phone = "010-1234-4567";
		StringTokenizer st1 = new StringTokenizer(phone, "-");
		
		System.out.println(st1.countTokens());	// 토큰 개수
		
		while(st1.hasMoreTokens()) {		// 분리가 되는 토큰이 더 있으면
			System.out.println(st1.nextToken());	// 다음 토큰을 내놔라
		}

		
	}

}
