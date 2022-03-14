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
		
		//암호화가 되기 
		char origin[] = new char[sInput.length()];   //메서드 : 이건 문자열의 길이 구하는 것    //사용자가 입력한 문자열의 크기만큼 문자배열 만들어라
		
		for(int i = 0; i < origin.length ; i++) {
			origin[i] = sInput.charAt(i);
		}
		
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
		for(int i = 0; i <origin.length; i++) {
			res1 += origin[i];
			res2 += crypto[i];
		}
		System.out.println("암호화 전 : " + res1);
		System.out.println("암호화 후 : " +  res2);
		
	}

}
