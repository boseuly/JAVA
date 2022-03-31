package exam01;

import java.util.Arrays;
import java.util.Scanner;

public class Sample01 {

	public static void main(String[] args) {
		// 문자열 생성
		String s1 = "문자열";
		String s2 = new String("문자열");
		
		char[] cArr = new char[] {'문', '자', '열'};
		String s3 = new String(cArr);
		
		byte[] bArr = new byte[] {65,66,67,68};
		String s4 = new String(bArr);
		
		
		// 문자열 관련 메서드
		boolean equal = s1.equals(s2);
		System.out.println("동일한 문자열 값 비교: " + equal);
		
		char c1 = s1.charAt(0);
		System.out.println("\"문자열\"문자에서 추출 : " + c1);
		
		s1 = "String(문자열) 관련 메서드";
		boolean contain = s1.contains("문자");	// 문자가 같은지를 확인 하는 게 아니라 해당 문자열이 포함되어 있는지 
		System.out.println("문자열에서 \"문자\" 포함 유무 : " + contain);
		
		int index = s1.indexOf("문자");
		System.out.println("문자열에서 \"문자\"가 포함되어 있으면 그 위치가 어디있는지 확인 : " + index);
		
		System.out.println(s1);
		s1 = s1.replace("메서드", "method");		// 메서드를 method로 수정해라
		System.out.println(s1);
		
		String[] sArr = s1.split(" ");			// 문자열 분리(공백을 이용할 때)
		System.out.println(Arrays.toString(sArr));
		
		s1 = "010-1234-5678";
		sArr = s1.split("-");
		System.out.println(Arrays.toString(sArr));
		
		s1 = String.join("/", sArr);			// 문자열을 결합(결합시킬 때마다 "/"를 이용해 결합)
		System.out.println(s1);
		
		// 전화번호를 입력받아서 출력할 때 전화번호의 뒷자리 4자리를 ****로 마스킹하시오.

		
		s1 = "   앞/뒤로 공백이 포함된 문자열    ";
		s1 = s1.trim();				// 앞뒤로 공백 지워주는 함수
		System.out.println(s1);
		
		s1 = s1.substring(2);		// 2인덱스부터 끝까지 잘라서 저장
		System.out.println(s1);
		
		s1 = s1.substring(3,6);		// 3인덱스부터 6인덱스까지 해당 문자열 잘라서 저장해주는 함수
		System.out.println(s1);
		
		s1 = "영문자 upper/lower";
		s1 = s1.toUpperCase();		// 모두 대문자로 변환
		System.out.println(s1);
		
		s1 = s1.toLowerCase();		// 모두 소문자로 변환
		System.out.println(s1);
		
		cArr = s1.toCharArray();	// 문자열을 문자 배열로 반환
		System.out.println(cArr);
		
		s1 = "010-1234-5678";
		boolean isPhone = s1.matches("\\d{3}-\\d{4}-\\d{4}");	// 정규표현식 -> 이메일, 패스워드, 아이디, .... 나중에 팀플할 때 형식 많이 사용해보기
		System.out.println(isPhone);  
		
		
		
		
	}

}
