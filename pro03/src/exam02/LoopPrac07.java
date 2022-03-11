package exam02;

import java.util.StringTokenizer;

public class LoopPrac07 {

	public static void main(String[] args) {
		/*
		 * 문자열에서 문자만 추출하는 방법
		 *    문자열변수명.charAt(위치값) : 문자열에서 위치값에 해당하는 문자를 추출
		 *    
		 * 문자열의 길이를 알아내는 방법
		 * 	  문자열변수명/length() : 문자열의 문자수를 알려준다.
		 */
		String s = "Hello Java Programming";
		char c = s.charAt(1);
		System.out.println(c);
		System.out.println("문자열 길이 : " + s.length());
		
		/*
		 * 다음의 문자열에서 대문자의 수와 소문자의 수를 구하시오.
		 * 어려움 대문자가 나오면 count++ 까지는 되는데 대문자구별을 못함
		 * char랑 아스키코드 비교할 때 ""에 넣어서 계속 오류남.. ''사용해야 됨
		 */
		s = "Hello Java Programming";
		char ch;
		int c1 = 0;
		int c2 = 0;
		
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i)>= 'A' && s.charAt(i) <= 'Z'){
				c1++;
			}else if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z'){
				c2++;
			}
		}
		System.out.printf("대문자 수 : %d\n소문자 수 : %d\n", c1, c2);
		
		
		/*
		 * 다음의 문자열에서 단어의 수를 구하시오(단어는 공백을 기준으로 구분하여 보면 됨)
		 * 
		 */
	/*	
		s = "Hello Java Programming";
		int c3 = 1;
		
		for(int i = 0; i < s.length(); i++) {
			ch = s.charAt(i);
			if(ch == ' ') {
				c3++;
			}
		}
		System.out.println("단어 수 : "+c3);
		
		
	*/
		//이거 복습 꼭하기
		
		boolean sStart = false;
		boolean sEnd = false;
		int wordCount = 0;
		
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) >= 'A' && s.charAt(i) <= 'z') {
				sStart = true;        //단어가 시작했다는 기록
			}else if(s.charAt(i)>='a' && s.charAt(i) <= 'Z') {
				sStart = true;        //단어가 시작했다는 기록
			}else if(s.charAt(i) == ' ') {
				sEnd = true;          //공백까지 마쳤다는 기록  //단어가 끝났다는 기록 
			}
			if(sStart && sEnd) {      //문자 시작, 공백(끝) 둘다 true 라면 
				wordCount++;		  //단어가 하나 추가됨	
				sStart = false; sEnd = false;     //여기서 초기화 시켜줘야 함 그래야 다음 단어를 셀 수 있음
				continue;
			}
			if(i == s.length() - 1) {    //위치(index)는 0부터 시작하고 길이(length)는 1부터 시작하기때문에 길이 -1 해야 맞음 
				if(sStart) {
					wordCount++;
				}
			}
		}
		System.out.println("단어 수 : "+ wordCount);
		
	}

}
