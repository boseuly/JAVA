package exam01;

import java.util.Scanner;

public class SwitchPrac01 {

	public static void main(String[] args) {
	/*	
		<switch문>
		
		- if문은 주로 값의 범위에 따라 특정 코드를 실행하도록 하기 위한 용도로 사용하지만
		  switch문은 주로 식의 하나의 결과값에 해당하는 경우 특정 코드를 실행하기 위한 용도로 사용한다.
		- 주로 메뉴 번호를 선택하고 특정 메뉴에 대한 코드를 실행 시킬 때 사용
		- switch는 if문과 다르게 조건이 만족한다고 해서 조건에 해당하는 코드만 수행하고 종료되지 않음
		  조건이 만족한 case부터 순차적으로 모든 case의 코드를 실행한다 -> break; 를 걸어줘야 한다
		
		<switch문 사용 형식>
		
		switch(식){
			case 식에 대한 결과값 1:
				결과값1에 해당하는 경우 실행할 코드
				break;
			
			case 식에 대한 결과값 2:
				결과값2에 해당하는 경우 실행할 코드
				break;
			
			case ...:
				break;
				
			default:
				위에 나열한 case에 해당하지 않는 경우 실행할 코드
				if문의 else처럼 선택옵션이다.(필요 없으면 굳이 안 써도 됨)
		
	*/	
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("다음 나열된 메뉴 중 하나의 번호를 입력하세요.");
		System.out.print("1. 조회\n2. 추가\n3. 수정\n4. 삭제\n9. 종료\n ");
		System.out.print(": ");
		int menu = sc.nextInt();
		
		switch(menu) {  //  보통()안에는 '조건식'이 들어가야 한다. 이번은 설명을 위해 값을 바로 넣음
		case 1:
			System.out.println("데이터 조회 메뉴를 선택했습니다.");
			break;
		case 2:
			System.out.println("데이터 추가 메뉴를 선택했습니다.");
			break;
		case 3:
			System.out.println("데이터 수정 메뉴를 선택했습니다.");
			break;
		case 4:
			System.out.println("데이터 삭제 메뉴를 선택했습니다.");
			break;
		case 9:
			System.out.println("프로그램을 종료합니다.");
		default:
			System.out.println("메뉴 번호를 잘못 입력하였습니다.");
		}
		
		
	}

}
