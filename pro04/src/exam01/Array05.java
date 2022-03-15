package exam01;

import java.util.Scanner;

public class Array05 {

	public static void main(String[] args) {
		/*
		 * 카이사르 암호 만들기
		 * 예를 들어 abc를 입력 -> def  -> 이런식으로 3칸씩 밀려나가 출력
		 * 
		 * - 사용자에게 영단어 입력 받기
		 * - 입력받은 영단어는 문자 배열로 생성한다.
		 * - 생성된 문자 배열에 대한 암호화된 배열을 저장하기 위해 배열을 새로 만든다.
		 * - 문자 쉬프트는 3으로 하고 만약 'z' 문자에 대한 쉬프가 필요한 경우 'a'로 넘어가게 해야 한다. 
		 */
		char arr1[] = null;
		
		Scanner sc= new Scanner(System.in);
		System.out.print("영단어를 입력하시오 : ");
		String sInput= sc.nextLine();
		
		//방법1) 암호화
		char origin[] = new char[sInput.length()];   //메서드 : 이건 문자열의 길이 구하는 것  //사용자가 입력한 문자열의 크기만큼 문자배열 만들어라
		
		for(int i = 0; i < origin.length ; i++) {
			origin[i] = sInput.charAt(i);			//문자열의 i번째 문자를 char로 변환해서 저장
		}
		
		//방법2) .tocharArray 메서드를 사용하여 문자열을 문자배열로 변환
		origin = sInput.toCharArray();	
		
		char[] crypto = new char[origin.length];   //배열 길이 속성 : 이건 배열의 길이 구하는 것
		for(int i = 0; i <crypto.length; i++) {
			if(origin[i] + 3 > 'z') {
				crypto[i] = (char)(origin[i] + 3 - 26);   //넘어가도 배열에 추가해주기 위해 -26을 해준 것
			}else {
				crypto[i] = (char)(origin[i]+3);
			}
		}
		
		String res1 = "";
		String res2 = "";
		// 방법1) 문자 배열을 문자열로 만들어서 출력하기 위해 반복문을 사용
		for(int i = 0; i <origin.length; i++) {
			res1 += origin[i];
			res2 += crypto[i];
		}
		// 방법2) new 연산으로 문자열 생성을 할 때 문자 배열을 넣는다.
		res1 = new String(origin);
		res2 = new String(crypto);
		
		// 방법3) String.vauleOf(문자배열)메서드를 사용한다.
		res1 = String.valueOf(origin);
		res2 = String.valueOf(crypto);
		// 세가지 방법 중 편한대로 
		
		System.out.println("암호화 전 : " + res1);
		System.out.println("암호화 후 : " +  res2);
		
	}

}
