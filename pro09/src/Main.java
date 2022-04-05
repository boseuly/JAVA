import java.text.SimpleDateFormat;
import java.util.Date;

import controller.MenuManager;

public class Main {

	public static void main(String[] args) {
		/*
		 * 프로그램 시작 점.
		 */
		SimpleDateFormat sFormat = new SimpleDateFormat();	// 시간 알려주기위해
		MenuManager myMenu = new MenuManager();
		System.out.println(sFormat.format(new Date()));	// 현재 시각을 sFormat형식으로 출력
		myMenu.main();
		
		
		
		
	}

}
