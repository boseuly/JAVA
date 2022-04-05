package controller;

import java.util.Date;
import java.util.Scanner;

import model.vo.Grade;
import model.vo.Student;
import model.vo.Teacher;

public class MenuManager {
	// 학생 성적 관리의 메뉴를 관리하기 위한 매니저 클래스

	private Scanner sc = new Scanner(System.in);
	private DatabaseManager db = new DatabaseManager();
	private Teacher loginAccount;
	
	public MenuManager(Teacher loginAccount) {
		this.loginAccount = loginAccount;
	}
	public void main() {
		StringBuilder menu = new StringBuilder();
		menu.append("1. 성적 조회\n");		// searchMenu()
		menu.append("2. 학생 정보 추가\n");	// studentAddMenu()
		menu.append("3. 성적 정보 수정\n");	// subjectModifyMenu()
		menu.append("4. 학생 정보 삭제\n");	// studentDeleteMenu()
		menu.append("9. 로그아웃\n");	// System.exit(0);
		menu.append(">>> ");
		while(true) {
			System.out.print(menu);
			
			String input = sc.nextLine();
			
			_clear();
			if(input.equals("1")) {
				searchMenu();
			} else if(input.equals("2")) {
				studentAddMenu();
			} else if(input.equals("3")) {
				subjectModifyMenu();
			} else if(input.equals("4")) {
				studentDeleteMenu();
			} else if(input.equals("9")) {
				logout();		// 로그아웃해주는 메소드 -> 로그아웃을 한 시점기록
			}
			_clear();
		}
	}
	
	private void logout() {
		loginAccount.setLoginDate(new Date());
		System.out.println(loginAccount.getName() + "님이 로그아웃 하였습니다.");
	}
	private void _clear() {
		for(int i = 0; i < 20; i++) {
			System.out.print("\n");
		}
	}
	// 검색 메소드
	public void searchMenu() {
		/*
		 * --------------------
		 * 이름 : 홍길동
		 * --------------------
		 * 국어    영어    수학    과학
		 *  xx점    xx점    xx점    xx점
		 * x등급   x등급   x등급   x등급
		 * --------------------
		 * 평균 : xx.xx 점
		 */
		String name = "";
		Grade[] datas = null;
		
		while(true) {
			System.out.print("학생 이름 입력 : ");
			name = sc.nextLine();
			datas = db.search(name);
			
			if(datas == null) {
				System.out.println("해당 학생은 존재하지 않습니다. 다시 입력하세요.");
			} else {
				break;				
			}
		}
		
		String result = _printGrades(name, datas);
		System.out.println(result);
		System.out.print("[[엔터키를 입력하세요]]");	sc.nextLine();
	}
	
	// 학생 추가 메소드
	public void studentAddMenu() {
		String name = "";
		while(true) {
			System.out.print("학생 이름 입력 : ");
			name = sc.nextLine();
			
			if(db.add(name)) {
				System.out.printf("%s 학생의 정보가 추가되었습니다.\n", name);
				System.out.print("[[엔터키를 입력하세요]]");	sc.nextLine();
				break;
			} else {
				System.out.println("해당 학생은 이미 존재하는 정보 입니다.");
			}
		}
	}
	
	// 수정 메소드
	public void subjectModifyMenu() {
		String name = "";
		Grade[] datas = null;		// 과목명을 넣기 위해서
		while(true) {				// 수정 메소드의 while	
			while(true) {			// 위에 있는 search 메소드 가져다옴
				System.out.print("학생 이름 입력 : ");
				name = sc.nextLine();
				datas = db.search(name);		// 해당 학생의 getGrades를 반환
				
				if(datas == null) {
					System.out.println("해당 학생은 존재하지 않습니다. 다시 입력하세요.");
				} else {		// 해당 학생이 존재하면 더이상 search 그만하겠다.
					break;				
				}
			}
			
			String result = _printGrades(name, datas);	// datas : 과목명 배열
			System.out.println(result);		// 과목, 점수, 등급 출력
			System.out.print("[[엔터키를 입력하세요]]");	sc.nextLine();
			
			System.out.println("출력 된 과목 순으로 점수를 입력하세요.(공백을 구분자로 사용하여 입력)");
			System.out.println("예) 90 80 70 ...");
			System.out.print(": ");
			
			String[] scoreArr = sc.nextLine().split(" ");	// 공백을 기준으로 분리
			int[] score = new int[0];
			if(scoreArr.length == datas.length) {	// 점수와 과목 길이가 같아야 한다.	// 분리가 됐을 때 과목 4개로 분리되어야 한다.
				score = new int[scoreArr.length];	
				for(int i = 0; i < scoreArr.length; i++) {
					Integer.parseInt(scoreArr[i]);	
				}
				Student std;
				for(int i = 0; i < datas.length;i++) {
					std = db.modify(name, datas[i].getName(), score[i]);
					System.out.printf("%s 학생의 %s 과목 점수가 수정되었습니다\n", std.getName(), datas[i].getName());
				}
				System.out.print("[[엔터키를 입력하세요]]");	 sc.nextLine();	
				break;
			}else {			// 만약 입력한 점수 개수와 과목 개수가 다르면 오류가 나니까 if문에 추가로 설정을 해준다.
				System.out.println("점수 입력 수량이 잘못되었습니다." + datas.length + "개 과목 수만큼 입력하세요.");
			}
		}
	}
	
	// 삭제 메소드
	public void studentDeleteMenu() {
		String name = "";
		while(true) {
			System.out.print("학생 이름 입력 : ");
			name = sc.nextLine();
			
			if(db.remove(name)) {
				System.out.println("학생 정보를 삭제하였습니다.");
				System.out.print("[[엔터키를 입력하세요]]");	sc.nextLine();
				break;
			} else {
				System.out.println("해당 학생은 존재하지 않습니다. 다시 입력하세요.");
			}
		}
	}
	
	private String _printGrades(String name, Grade[] datas) {
		String hLine = "--------------------\n";
		StringBuilder sb = new StringBuilder();
		String[] scores = new String[datas.length];	// 과목명 배열 길이만큼
		String[] levels = new String[datas.length];
		String[] subjects = new String[datas.length];
		double avg = 0;
		
		for(int i = 0; i < datas.length; i++) {
			scores[i] = Double.valueOf(datas[i].getScore()).toString();
			levels[i] = Character.valueOf(datas[i].getLevel()).toString();
			subjects[i] = datas[i].getName();
			avg += datas[i].getScore();
		}
		avg /= datas.length;
		
		sb.append(hLine);
		sb.append(String.format("이름 : %s\n", name));
		sb.append(hLine);
		sb.append(String.join("\t", subjects) + "\n");
		sb.append(String.join("점\t", scores) + "점\n");
		sb.append(String.join("등급\t", levels) + "등급\n");
		sb.append(hLine);
		sb.append(String.format("평균 : %.2f 점\n", avg));
		sb.append(hLine);
		
		return sb.toString();
	}
}
