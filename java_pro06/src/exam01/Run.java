package exam01;

import java.util.Arrays;

import exam02.ReportGrade;

public class Run {

	public static void main(String[] args) {
//		Lotto l = new Lotto();
//		l.generate();
//		System.out.println("-----수정후-----");
//		l.setMin(4);
//		l.setMax(67);
//		l.setCount(8);
//		l.generate();
//		
		Grade g = new Grade("국어");
		g.setScore(80.4);
		System.out.println(g.getName());
		System.out.println(g.getScore());
		System.out.println(g.getRank());
		System.out.println("------구분선-----");
		Grade g1 = new Grade("영어", 90.6);
		System.out.println(g1.getRank());
		
		System.out.println("------구분선-----");
		Lotto lot = new Lotto();
		lot.generate();
		System.out.println(Arrays.toString(lot.getNumbers()));
		
		lot.generate(1);
		System.out.println(Arrays.toString(lot.getNumbers()));
		
		lot.generate(10,20,30);
		System.out.println(Arrays.toString(lot.getNumbers()));
	
		System.out.println("------구분선-----");
			
		/*
		 * - 점수를 설정 할 때에는 어떤 과목에 대한 점수인지를 알 수 있도록 setter의
		 *   매개변수를 조정하여 사용하도록 한다.
		 * 
		 * - 과목 배열의 인덱스와 점수 배열의 인덱스는 서로 동일한 인덱스간에 
		 *   연계되는 형태이다. 국어 과목의 인덱스가 0번이면 해당 과목의 점수는 0번에
		 *   저장되어 있는 방식이다.
		 * 
		 * - 위에 제시한 멤버변수 및 메서드 외에 추가로 필요한 멤버변수 및 메서드가
		 *   있는 경우 추가해도 됨
		 * 
		 * 
		 */
		String[] str = new String[] {"국어", "영어", "수학"};
		ReportGrade rg = new ReportGrade("이보슬", str);
		System.out.println(Arrays.toString(rg.getSubjects()));
		rg.setGrades(90.0, 80.0, 95.4);
		System.out.println(Arrays.toString(rg.getGrades()));
		rg.printMethod(0);	// 과목, 점수 한번에 출력해주는 메소드 만들어봄
		System.out.println("------구분선-----");
		
		
		
	}
	
}
