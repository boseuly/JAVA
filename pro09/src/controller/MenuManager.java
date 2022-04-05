package controller;

import java.util.Scanner;

import model.vo.Grade;

// 학생 성적 관리의 메뉴를 관리하기 위한 매니저 클래스
public class MenuManager {
	private Scanner sc = new Scanner(System.in);
	private DatabaseManager db = new DatabaseManager();
	
	// 메뉴메소드
	public void main() {		// 이 메소드를 통해 출력
		StringBuilder menu = new StringBuilder();
		menu.append("1. 성적 조회\n");			// searchMenu()
		menu.append("2. 학생 정보 추가\n");		// studentAddMenu()
		menu.append("3. 성적 정보 수정\n");		// subjectModifyMenu()
		menu.append("4. 학생 정보 삭제\n");		// studentDeleteMenu()
		menu.append("9. 프로그램 종료\n");		//	System.exit(0);
		menu.append(">>> ");
		
		
		while(true) {
			System.out.println(menu);    	// 메뉴 출력
			String input = sc.nextLine();	// 입력을 받아서
			_clear();
			if(input.equals("1")) {	
				searchMenu();
			}else if(input.equals("2")) {
				studentAddMenu();
			}else if(input.equals("3")) {
				subjectModifyMenu();
			}else if(input.equals("4")) {
				studentDeleteMenu();
			}else if(input.equals("9")) {
				System.out.println("프로그램이 종료됩니다.");
				System.exit(0);  // 프로그램 종료
			}
			_clear();
		}
	}
	
	// 성적 조회
	public void searchMenu() {
		String name = "";
		Grade[] datas = null;	
		
		while(true) {
			System.out.println("학생 이름 입력 : ");
			name = sc.nextLine();		// 찾는 이름
			datas = db.search(name);	// 조회하는 메소드 호출(return값은 Grade[])
			
			if(datas == null) {		// 해당 학생이 존재하지 않는다면
				System.out.println("해당 학생은 존재하지 않습니다. 다시 입력하세요.");
			}else {					// 해당 학생이 존재한다면
				break;
			}
		}
		String result = _printGrades(name, datas);	// 학생데이터 정리해둔 출력 메서드 _printGrades
		System.out.println(result);				// 학생 데이터 출력
		System.out.println("[[엔터키를 누르면 메뉴로 이동합니다.]]");
	}
	
	private String _printGrades(String name, Grade[] datas) {
		String hLine = "---------------------\n";
		StringBuilder sb = new StringBuilder();	// StringBuilder sb를 만들어서 문자열을 계속 추가해주는 거야
		String[] scores = new String[datas.length];	// 점수
		String[] levels = new String[datas.length];	
		String[] subjects = new String[datas.length];	// 과목
		double avg = 0;
		
		// Wrapper 클래스 -> 기본타입의 데이터를 객체로 표현해야 하는 경우에도 쓰임(기본 자료 타입을 객체로 다루기 위해서 사용)
		for(int i = 0; i < datas.length; i++) {	// Wrapper 클래스를 통해서 숫자를 문자화(문자열을 기본 타입값으로 변환할 때)
			scores[i] = Double.valueOf(datas[i].getScore()).toString();	// 과목별 점수를 하나씩 배열에 추가
			levels[i] = Character.valueOf(datas[i].getLevel()).toString();	// 과목별 등급을 하나씩 배열에 추가
			subjects[i] = datas[i].getName();	// 과목명을 하나씩 배열에 추가
			avg += datas[i].getScore();
			// 숫자를 join 하기 위해서는 문자열 리스트로 바꾼 다음에 join 해야 하기 때문에 굳이 이렇게 한 거임
		}
		avg /= datas.length;
		
		sb.append(hLine);
		sb.append(String.format("이름 : %s\n",  name));
		sb.append(hLine);
		
		sb.append(String.join("\t", subjects) + "\n");
		sb.append(String.join("점\t", scores) + "\n");	// 숫자 리스트를 join 하기 위해서 -> 문자열 리스트로 바꾼 다음 join해야 됨
		sb.append(String.join("등급\t", levels) + "\n");	// 
		sb.append(hLine);
		sb.append(String.format("평균 : %.2f 점\n", avg));
		sb.append(hLine);
		
		return sb.toString();
	}
	// 학생 정보 추가
	public void studentAddMenu() {
		String name;
		while(true) {
			System.out.println("학생 이름 입력 : ");
			name = sc.nextLine();
			
			if(!db.existed(name)) {	// false면 학생이 있음
				System.out.println("해당 학생은 이미 존재하는 정보 입니다.");
			}else {		// 만약 해당 학생이 존재한다면
				
				
				break;
			}
		}
		if(db.add(name)) {
			System.out.printf("%s 학생의 정보가 추가되었습니다.", name);
		}
		
	}
	
	// 성적 정보 수정
	public void subjectModifyMenu() {
		System.out.println("성적 정보 수정 메뉴 실행!");
	}
	
	// 학생 정보 삭제
	public void studentDeleteMenu() {
		System.out.println("학생 정보 삭제 메뉴 실행!");
	}
	
	// 메뉴 보기 좋게 하기 위한 메소드
		private void _clear() {
			for(int i = 0; i< 20; i++) {
				System.out.println("\n");
			}
			
		}
	
}
