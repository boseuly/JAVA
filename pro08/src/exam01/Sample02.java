package exam01;

import java.util.Arrays;
import java.util.Scanner;

public class Sample02 {
	Scanner sc = new Scanner(System.in);
	public void ex01() {
		/* 첫 번째 문제
		 *  
		 *  사용자가 입력으로 정수값 입력을 받을 때 한번의 입력으로 1개 이상의 정수값을 
		 *  받을 수 있도록 한다. (nextLine())메서드를 사용해야 함)
		 *  
		 *  예) 정수값 입력 : 10 20 30 40 50 
		 *  
		 *     다음의 값을 입력하였습니다.
		 *     10, 20, 30, 40, 50
		 *     
		 */	
		
		
		System.out.print("1개 이상의 정수값을 입력하세요 : ");
		String s = sc.nextLine();		// 문자열로 저장 후
		String[] str = s.split(" ");	// 공백을 기준으로 분리하여 배열에 넣음
		System.out.println("다음의 값을 입력하였습니다.");
		System.out.println(Arrays.toString(str));
		
	}
	
	public void ex02() {
		/*	두 번째 문제
		 *  
		 *  사용자 입력으로 전화 번호를 입력 받을 때 xxx-xxxx-xxxx 형식으로 입력하지 않은 
		 *  경우 다시 입력하도록 한다.
		 *  
		 */
		
		String input = "";
		while(!input.matches("\\d{3}-\\d{4}-\\d{4}")){
			System.out.print("전화번호 입력 : ");
			input = sc.nextLine();
		}
		System.out.println("전화번호 입력을 확인하였습니다.");
	}
	
	public void ex03() {
		/* 세 번째 문제
		 * 
		 * 사용자 입력으로 전화번호를 입력 받고 출력할 때 전화번호 마지막 4자리 숫자는
		 * 다음의 문자로 마스키 처리하여 출력되도록 한다. (마스킹 문자 -> *)
		 *  	
		 */
		System.out.println("전화번호 입력 : ");
		String input = sc.nextLine();
		
//방법1	String masking = input.replace(input.substring(input.length() - 4, input.length()), "****");
//방법2	String masking = input.substring(0, input.length() - 4) + "****";
		
// 방법3
		String[] sArr = input.split("-");
		sArr[2] = "****";
		String masking = String.join("-", sArr);
		
		System.out.println(masking);
		
	}
	
	public void ex04() {
		/* 네 번째 문제
		 * 
		 * 사용자 입력으로 이메일 주소 형식을 입력 받을 때 xxxxx@gmail.com 또는 xxxxx@naver.com,
		 * xxxxx@nate.com 의 주소 형식만 받을 수 있도록 한다.(xxxxx는 5글자 제한 아님)
		 * 
		 */
		
		String[] emailDomain = new String[] {
				"gmail.com", "naver.com", "nate.com"
		};
		
		while(true) {
			System.out.print("이메일 주소 입력 : ");
			String input = sc.nextLine();
			
			// @가 포함된 이메일 주소 형식인지 확인
			String[] email = input.split("@");
			if(email.length == 2) {
				boolean isValiad = false;
				for(int i = 0; i < emailDomain.length; i++) {	// 도메인 주소랑 하나하나 비교를 해줌
					if(email[1].equals(emailDomain[i])) {	
						isValiad = true;
						break;
					}
				}
				if(isValiad) {
					System.out.println("이메일 주소를 확인하였습니다.");
					break;
				}else {
					System.out.println("허용된 이메일 주소 도메인이 아닙니다.");
				}
			}else {
				System.out.println("이메일 주소 형식이 아닙니다. 다시 입력하세요.");
			}
		}
	}
		

	
	
	
	public static void main(String[] args) {
		Sample02 sample = new Sample02();
//		sample.ex01();
		sample.ex03();
		
		
		
	}

}
