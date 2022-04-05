package controller;

import java.util.Scanner;

import model.vo.Grade;
import model.vo.Student;

// 학생용 메뉴
public class StudentMenuManager {
	private DatabaseManager db = new DatabaseManager();
	private Scanner sc = new Scanner(System.in);
	private Student loginAccount;
	
	public StudentMenuManager(Student loginAccount) {		// 메뉴메니저에서 했던 거랑 동일
		this.loginAccount = loginAccount;
	}
	// student 메뉴는 두 가지뿐
	public void main() {
		StringBuilder menu = new StringBuilder();
		menu.append("1. 성적 조회\n");		// searchMenu()
		menu.append("9. 로그아웃\n");	// System.exit(0);
		menu.append(">>> ");
		while(true) {
			System.out.print(menu);
			
			String input = sc.nextLine();
			
			if(input.equals("1")) {
				searchMenu();
			} else if(input.equals("9")) {
				System.out.println("로그아웃 합니다.");		// 로그아웃해주는 메소드 -> 로그아웃을 한 시점기록
				System.exit(0);
			}
		}
	}

	
	private void searchMenu() {
		Grade[] grades = db.search(loginAccount.getName());
		
		String result = _printGrades(loginAccount.getName(), grades);
		System.out.println(result);
		}
		
		
		
	}

	// 성적 출력해주는 메소드
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
