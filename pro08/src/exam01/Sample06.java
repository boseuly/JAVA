package exam01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Sample06 {

	public static void main(String[] args) {
		/* 날짜 관련 클래스
		 * Date 클래스
		 * - 시스템으로부터 현재 날짜, 시간 정보를 가져와서 사용할 수 있게 만들어진 클래스
		 * 
		 */
		
		Date date = new Date();
		System.out.println(date);
		
		System.out.println(String.format("%1$tY/%1$tm/%1$td %1$tH:%1$tM:%1$tS", date));	// 1번 위치에 있는 데이터를 가지고 년월일 시간분초
		System.out.println(String.format("%tY/%tm/%td %tH:%tM:%tS", date,date,date,date,date,date));
									//     년도/ 월/ 일  시간: 분 : 초
		
		System.out.println(date.getTime());     // 1970년도 1월 1일 00시 00분 00초를 기준으로 1000밀리세컨즈 지났음 -> 가끔 사용된다고 함 
		
		/*
		 * Calendar 클래스
		 * - new 생성자를 통해 직접 객체를 만들 수 없다.
		 */
		
		Calendar c = Calendar.getInstance();
		System.out.println(c.get(Calendar.YEAR));
		System.out.println(c.get(Calendar.MONTH)+1);
		System.out.println(c.get(Calendar.DATE));
		System.out.println(c.get(Calendar.HOUR));
		System.out.println(c.get(Calendar.MINUTE));
		System.out.println(c.get(Calendar.SECOND));
		
		c.add(Calendar.YEAR, 1);	// 날짜를 더하는 방식
		System.out.println(String.format("%1$tY/%1$tm/%1$td %1$tH:%1$tM:%1$tS", c.getTime()));
		
		c.add(Calendar.MONDAY, 13);
		System.out.println(String.format("%1$tY/%1$tm/%1$td %1$tH:%1$tM:%1$tS", c.getTime()));
		
		c.add(Calendar.DATE, 60);
		System.out.println(String.format("%1$tY/%1$tm/%1$td %1$tH:%1$tM:%1$tS", c.getTime()));
		
		c = Calendar.getInstance();
		
		System.out.println(String.format("오늘은 %1$tY/%1$tm/%1$td %1$tH:%1$tM:%1$tS", c.getTime()));
		int i = c.getActualMaximum(Calendar.DATE);
		System.out.println(String.format("%tm 월의 마지막 일은 %d 일", c.getTime(), i));
		
		c.add(Calendar.MONTH,1);
		i = c.getActualMaximum(Calendar.DATE);	// 현재 월의 최대 일수
		System.out.println(String.format("%tm 월의 마지막 일은 %d 일", c.getTime(), i));
	
		/*
		 * GregorianCalendar 클래스
		 * - Calendar 클래스를 상속하여 사용하는 클래스
		 *  
		 */
		GregorianCalendar c2 = new GregorianCalendar();
		
		System.out.println(c2.get(Calendar.YEAR));
		System.out.println(c2.get(Calendar.MONTH));
		System.out.println(c2.get(Calendar.DATE));
		System.out.println(c2.get(Calendar.HOUR));
		System.out.println(c2.get(Calendar.MINUTE));
		System.out.println(c2.get(Calendar.SECOND));
		
		c2.add(Calendar.YEAR, 1);
		System.out.println(String.format("%1$tY/%1$tm/%1$td %1$tH:%1$tM:%1$tS", c2.getTime()));
		
		c2.add(Calendar.MONTH, 13);
		System.out.println(String.format("%1$tY/%1$tm/%1$td %1$tH:%1$tM:%1$tS", c2.getTime()));
		
		c2.add(Calendar.DATE, 1);
		System.out.println(String.format("%1$tY/%1$tm/%1$td %1$tH:%1$tM:%1$tS", c2.getTime()));
		
		c2 = new GregorianCalendar();
		System.out.println(c2.isLeapYear(c2.get(Calendar.YEAR)));	// 윤년 확인
		
		
		/*
		 * SimpleDateFormat 클래스
		 * - 날짜 포멧 형식을 자유롭게 만들기 위해 사용하는 클래스 (String.format보다 쓰기 쉽게)
		 */
		
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");	// 날짜 출력 형식을 정해놓음
		String now = dFormat.format(new Date());		// 정해 놓은 날짜 형식(dFormat)을 format으로 하여 Date 객체 저장
		System.out.println(now);
		
		dFormat.applyPattern("yyyy년 MM월 dd일 HH시 mm분 ss초 .SSS");	// 패턴을 바꾸고 싶으면 객체 생성할 필요 없이 applyPattern 사용하면 됨
		now = dFormat.format(new Date());		
		System.out.println(now);
		
		dFormat.applyPattern("yyyy년 MM월 dd일 a hh시 mm분 ss초 .SSS");	// 오전 오후 쓰고 싶으면 a 적어주면 됨
		now = dFormat.format(new Date());		
		System.out.println(now);
		
		dFormat.applyPattern("yyyy년 MM월 dd일 hh시 mm분 ss초 .SSS");
		now = dFormat.format(new Date());		
		System.out.println(now);
		
		// 위에서는 계속 날짜를 문자열로 바꿔주는 것이었는데 이건 문자열 받아서 날짜로 바꿔준다.
		dFormat.applyPattern("yyyy년 MM월 dd일");
		try {
			Date d2 = dFormat.parse("2022년 10월 10일");	// 문자열을 날짜로 변환
			System.out.println(d2);
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
		TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));
		GregorianCalendar cd1 = new GregorianCalendar();
		System.out.println(cd1.getTime());
		
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
		System.out.println(cd1.getTime());
		
	}

}
