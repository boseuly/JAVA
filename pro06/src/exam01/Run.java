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
		
		Lotto lot = new Lotto();
		lot.generate();
		System.out.println(Arrays.toString(lot.getNumbers()));
		
		lot.generate(1);
		System.out.println(Arrays.toString(lot.getNumbers()));
		
		lot.generate(10,20,30);
		System.out.println(Arrays.toString(lot.getNumbers()));
	
		
		
		
		ReportGrade rg = new ReportGrade("이보슬");
		System.out.println(rg.getName());
		
		String[] str = new String[] {"국어", "영어"};
		rg.setSubjects(str);
		System.out.println(Arrays.toString(rg.getSubjects()));
		
		double[] grades = new double[]{70.7, 46.8, 89};
		rg.setGrades(grades);
		System.out.println(Arrays.toString(rg.getGrades()));
		
		
	}
	
}
