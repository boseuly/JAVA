package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.vo.Student;
import model.vo.Teacher;

public class LoginMenuManager {
	private Scanner sc = new Scanner(System.in);
	private TeacherDatabaseManager tDB = new TeacherDatabaseManager();
	private StudentMenuManager
	private DatabaseManager sDB = new DatabaseManager();
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
	
	
	public void main() {
		StringBuilder menu = new StringBuilder();
		menu.append("1. 교사용 로그인\n");
		menu.append("2. 학생용 로그인\n");
		menu.append("3. 종료\n");
		menu.append(">>> ");
		
		while(true) {
			System.out.print(menu);
			String input = sc.nextLine();
			if(input.equals("1")) {
				teacherLogin();
			}else if(input.equals("2")){
				studentLogin();
			}else if(input.equals("3")) {
				System.out.println("프로그램을 종료 합니다.");
				System.exit(0);
			}
		}
	}
		// 교사용 로그인
	private void teacherLogin() {
		System.out.print("교사명 : ");
		String username = sc.nextLine();	// 교사명 입력받기
		
		Teacher loginAccount = null;		// 로그인계정
	
		for(int i = 0; i < 3; i++) {		// 비밀번호 입력은 3번까지 허용
			System.out.println("비밀번호 : ");
			String password = sc.nextLine();		// 비번 저장
			loginAccount = tDB.login(username, password);
			if(loginAccount != null) {		// 비회원이거나 아이디, 비번을 잘못쳐서 null값이 나온 거임
				break;
			}
		}
		if(loginAccount == null) {		// 아이디, 비번을 잘못 친 경우 
			System.out.println("로그인에 실패하였습니다. 다시 시도하세요.");
		}else {		// 로그인에 성공한 경우(첫 로그인, 이전에 로그인 이력이 있는 경우로 나뉨) 
			
			if(loginAccount.getLoginDate() == null) {		// 로그인 기록이 한 번도 없다면
				System.out.println("환영합니다. 첫 로그인 입니다.");	// 첫로그인
			}else {			// 로그인 이력이 있는 경우
				Date now = new Date();
				System.out.println("최근 접속 시간은 " + loginAccount.getLoginDateFormat() + " 입니다.");
				System.out.println("현재 로그인 시간은 " + sFormat.format(now));	// 이렇게 하면 현재시간을 형식에 맞게 출력하는 거임
				loginAccount.setLoginDate(now);
			}
			MenuManager tMenu = new MenuManager(loginAccount);	// 로그인 한 계정에 대한 정보를 전달
			tMenu.main();   // MenuManager 실행
		}
		
		/*
		 * 로그인 검사 후 로그인이 성공하면 
		 * 최근 로그인 시간과 현재 로그인 시간을 출력하고
		 * Teacher 객체에는 현재 로그인 시간을 loginDate에
		 * 저장 후 MenuManager를 실행한다.
		 * 
		 * 고명환 선생님이 접속하였습니다.
		 * 최근 접속 시간은 2022월 04월 04일 18시 30분 34초
		 * 현재 로그인 시간은 2022년 04월 05일 09시 30분 30초 입니다.
		 * 
		 * 1. 성적 조회
		 * 2. 학생 정보 추가
		 * ...
		 */
		
		
		
		
	}
		// 학생용 로그인
	private void studentLogin() {
		System.out.print("학생명 입력 : ");
		String username = sc.nextLine();		// 학생명 저장
		Student loginAccount = null;		// 로그인계정
		
		for(int i = 0; i < 3; i++) {		// 비밀번호 입력은 3번까지 허용
			System.out.println("비밀번호 : ");
			String password = sc.nextLine();		// 비번 저장
			loginAccount = sDB.login(username, password);
			if(loginAccount != null) {		// 비회원이거나 아이디, 비번을 잘못쳐서 null값이 나온 거임
				break;
			}
		}
		
		if(loginAccount == null) {		// 아이디, 비번을 잘못 친 경우 
			System.out.println("로그인에 실패하였습니다. 다시 시도하세요.");
		}else {		// 로그인에 성공한 경우(첫 로그인, 이전에 로그인 이력이 있는 경우로 나뉨) 
			
			if(loginAccount.getLoginDate() == null) {		// 로그인 기록이 한 번도 없다면
				System.out.println("환영합니다. 첫 로그인 입니다.");	// 첫로그인
			}else {			// 로그인 이력이 있는 경우
				Date now = new Date();
				System.out.println("최근 접속 시간은 " + loginAccount.getLoginDateFormat() + " 입니다.");
				System.out.println("현재 로그인 시간은 " + sFormat.format(now));	// 이렇게 하면 현재시간을 형식에 맞게 출력하는 거임
				loginAccount.setLoginDate(now);
			}
			MenuManager tMenu = new MenuManager(loginAccount);	// 로그인 한 계정에 대한 정보를 전달
			tMenu.main();   // MenuManager 실행
		}
	}
}
