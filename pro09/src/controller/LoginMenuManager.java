package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.vo.Student;
import model.vo.Teacher;

public class LoginMenuManager {
	private Scanner sc = new Scanner(System.in);
	private TeacherDatabaseManager tDB = new TeacherDatabaseManager();
	private DatabaseManager sDB = new DatabaseManager();
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
	
	public void main() throws Exception{
		StringBuilder menu = new StringBuilder();
		menu.append("1. 교사용 로그인\n");
		menu.append("2. 학생용 로그인\n");
		menu.append("3. 패스워드 초기화\n");
		menu.append("4. 종료\n");
		menu.append(">>> ");
		
		
		while(true) {
			int input = 0;
			while(true) {
				System.out.println(menu);
				
				if(sc.hasNextInt()) {
					input = sc.nextInt();  sc.nextLine();	
					break;
				}
				sc.nextLine();		// nextLine 안 써주면 무한루프에 빠짐
			}
			switch(input) {
			case 1: teacherLogin(); break;
			case 2: studentLogin(); break;
			case 3: resetPassword(); break;
			case 4: 
				System.out.println("프로그램을 종료 합니다.");
				System.exit(0);
			}
		}
	}
// 	이렇게해도 됨. 근데 좀더 api사용하는 거에 익숙해지만 위에 로직처럼 hasNectInt 사용해도됨 -> hasNextInt : 숫자가 존재하는지
//		while(true) {
//			System.out.print(menu);
//			
//			String input = sc.nextLine();
//			
//			if(input.equals("1")) {
//				teacherLogin();
//			} else if(input.equals("2")) {
//				studentLogin();
//			}else if(input.equals("3")) {	// 패스워드를 랜덤값으로 초기화해준다.
//				resetPassword();
//			} else if(input.equals("4")) {
//				System.out.println("프로그램을 종료 합니다.");
//				System.exit(0);
//			}
//		}
	
	// 패스워드 초기화 메소드
	private void resetPassword() {
		// 교사용 .. 학생용 ...
		// 1. 아이디(이름)만 입력하면 교사용에서 또는 학생용에서 동일한 계정을 찾아서 변경하게 한다. -> 교사용, 학생용 계정(이름)이 같을 수도 있다는 문제점이 있음
		// 2. 교사용, 학생용 먼저 선택 후 선택한 항목에 대해 동일한 계정을 찾아서 변경하게 한다.	
		System.out.print("1. 교사용 계정\n");
		System.out.print("2. 학생용 계정\n");
		System.out.print(">>> ");
		String type = sc.nextLine();
		
		System.out.print("이름 : ");
		String name = sc.nextLine();
		
		switch(type.charAt(0)) {	
		case '1':					// 문자로 변환 했기 때문에 ''를 사용한거임
			if(tDB.isExisted(name)) {
				teacherResetPassword(name);
			}
			break;
		case '2':
			if(sDB.isExisted(name)) {
				studentResetPassword(name);
			}
		}
	}
	// 
	private void teacherResetPassword(String name) {
		Teacher t = tDB.getTeacher(name);
		String password = t.resetPassword();	// 패스워드리셋해준다.
		System.out.println(password + " 로 초기화 되었습니다. 초기화 패스워드로 로그인 후 다시 패스워드를 변경하세요.");
	}
	private void studentResetPassword(String name) {
		Student s = sDB.getStudent(name);
		String password = s.resetPassword();
		System.out.println(password + " 로 초기화 되었습니다. 초기화 패스워드로 로그인 후 다시 패스워드를 변경하세요.");
	}
	// 교사용 로그인
	private void teacherLogin() throws Exception{
		System.out.print("교사명 : ");
		String username = sc.nextLine();
		
		Teacher loginAccount = null;
		
		// 비밀번호 3회 입력 제한.
		for(int i = 0; i < 3; i++) {
			System.out.print("비밀번호 : ");
			String password = sc.nextLine();
			loginAccount = tDB.login(username, password);	// 로그인한 계정
			if(loginAccount != null) {		// 로그인에 성공한 경우 for문에서 벗어나도록 break;
				break;
			}
		}
		
		if(loginAccount == null) {	// 비번을 계속 틀리면 loginAccount는 null값
			System.out.println("로그인에 실패하였습니다. 다시 시도하세요.");
		} else {			// 패스워드가 맞는 경우
			if(loginAccount.getLoginDate() == null) {
				System.out.println("환영합니다. 첫 로그인 입니다.");
			} else {
				Date now = new Date();
				System.out.println("최근 접속 시간은 " + loginAccount.getLoginDateFormat() + " 입니다.");	// 이전 접속 시간 출력
				System.out.println("현재 로그인 시간은 " + sFormat.format(now) + " 입니다.");// 지금 접속 시간 출력
				loginAccount.setLoginDate(now);	 	// 지금 시간을 Teacher클래스의 멤버변수인 loginDate에 저장
			}
			MenuManager tMenu = new MenuManager(loginAccount);	// 로그인 계정을 생성자를 통해 전달을 한다. (MenuManager 객체 생성)
			tMenu.main();		// MenuManager클래스의 main()메소드 호출
		}
		
		/*
		 * 로그인 검사 후 로그인이 성공하면
		 * 최근 로그인 시간과 현재 로그인 시간을 출력하고
		 * Teacher 객체에는 현재 로그인 시간을 loginDate 에
		 * 저장 후 MenuManager 를 실행한다.
		 * 
		 * 고명환 선생님이 접속하였습니다.
		 * 최근 접속 시간은 2022년 04월 04일 18시 30분 34초 입니다.
		 * 현재 로그인 시간은 2022년 04월 05일 09시 30분 30초 입니다.
		 * 
		 * 1. 성적 조회
		 * 2. 학생 정보 추가
		 * ...
		 * ...
		 * 
		 * MenuManager 의 프로그램 종료 메뉴는 로그아웃으로 바꾼다.
		 * (출력만 바꾼다.)
		 */
	}
	
	private void studentLogin() {
		System.out.print("학생명 : ");
		String username = sc.nextLine();
		
		Student loginAccount = null;
		
		// 비밀번호 3회 입력 제한.
		for(int i = 0; i < 3; i++) {
			System.out.print("비밀번호 : ");
			String password = sc.nextLine();
			loginAccount = sDB.login(username, password);
			if(loginAccount != null) {
				break;
			}
		}
		
		if(loginAccount == null) {
			System.out.println("로그인에 실패하였습니다. 다시 시도하세요.");
		} else {
			Date now = new Date();
			System.out.println("현재 접속 시간은 " + sFormat.format(now) + " 입니다.");

			MenuManager sMenu = new MenuManager(loginAccount);
			sMenu.main();
		}
	}
	
}
