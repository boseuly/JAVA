package exam03;

import java.util.Arrays;
import java.util.Scanner;

public class TwoArrayTest01 {

	public static void main(String[] args) {
		/*
		 * 사용자가 입력한 영문자열에서 입력된 문자의 수를 구하는 코드를 작성
		 * 1. 영문자를 제외한 다른 문자는 무시한다.
		 * 2. 영문자의 대/소문자를 구분하지 않고 문자 수를 구한다.
		 *
		 * 예제
		 * 영문자 입력 : hello java programming
		 * 입력한 영문자 분포
		 * a(3), e(1), g(2), h(1), i(1),
		 * j(1), l(2), m(2), n(1), o(2),
		 * p(1), r(2), v(1)
		 * 
		 * 정적배열, 동적 배열로 다 해보기
		 */
		
		
		//int[] 배열에 a~z까지 0~25 배열방을 만들어준다.
		//아스키 코드 활용하기  a = 97     a-a = 0 따라서 a는 0번 인덱스  b-a = 1 따라서 b는 1번 인덱스
		//				  A = 65	 A-A = 0  -> A는 0번 인덱스   B-A = 1 -> B는 1번 인덱스
	
	/*
	 * 	참고) 아스키코드 확인하는 법
		
		for(int i = 'A'; i <= 'Z'; i++) {
			System.out.println();
		}
	*/	
		
		// 사용자 입력을 통해 문자열을 받아야 함
		Scanner sc = new Scanner(System.in);
		
		System.out.print("영문자 입력 : ");
		String data = sc.nextLine();
		
		// 알파벳 별 수를 세기위한 배열, 26개의 공간이 있는 배열
		int[] counting = new int[26];    // -> 0~25 방 생성
		 
		
		// 반복문 사용 -> 사용자가 입력한 문자열에서 문자 추출 -> .charAt(위치값)
		// 대소문자를 구분하지 않기 위한 방법
		for(int i = 0; i < data.length(); i++) {
			int indx = 0;
			char c = data.charAt(i);
			
			if(c >= 65 && c <= 90) {		//대문자인 경우	-> 이렇게 한 거 자체가 대소문자 구분을 안 한 게 됨 -> 둘다 결과값이 0,1,2,3 ... 
				indx = c - 65;
				counting[indx] += 1;
			}else if(c >= 97 && c <= 122) {   //소문자인 경우 -> 이렇게 한 거 자체가 대소문자 구분을 안 한 게 됨 -> 둘다 결과값이 0,1,2,3 ... 
				indx = c - 97;
				counting[indx] += 1;
			}
			
		}
		System.out.println(Arrays.toString(counting));
		
	}

}
