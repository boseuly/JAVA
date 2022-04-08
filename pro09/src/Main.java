import java.text.SimpleDateFormat;
import java.util.Date;

import controller.LoginMenuManager;
import controller.MenuManager;

public class Main {

	public static void main(String[] args) throws Exception {	// 프로그램 시작은 항상 main메소드에서!
		/*
		 * 프로그램 시작 점 -> 시작점을 기점으로 하나하나 분석해보기 
		 */
		SimpleDateFormat sFormat = new SimpleDateFormat("현재 시각 : yyyy년 MM월 dd일 a hh시 mm분 ss초"); // 내가 원하는 형식으로 날짜 포맷
		LoginMenuManager loginMenu = new LoginMenuManager();	// 메소드를 사용하기 위한 객체 생성
		
		System.out.println(sFormat.format(new Date()));			// 위에서 지정한 날짜 format형식으로 출력
		loginMenu.main();		// loginMenu의 main메소드 호출
		
		
		
		/*
		 * 어디에서 호출을 하고 어디에 로직을 짜놨는지 기억을 해야 한다.
		 * 호출을 하면 해당 메소드가 실행된다. 반환값이 있으면 값을 전달하고 없으면 반환값 없이 호출한 자리로 돌아온다.
		 */
	}

}
