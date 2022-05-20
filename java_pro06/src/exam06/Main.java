package exam06;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		Grade g1 = new Grade("국어");
		g1.setScore(95.0);
		Grade g2 = new Grade("영어",80.4);
		
		System.out.println(g1.getName());
		System.out.println(g1.getScore());
		System.out.println(g2.getName());
		System.out.println(g2.getScore());
		
		System.out.println("-------------------");
		
		Grade[] gArr = new Grade[4];
		gArr[0] = g1;
		gArr[1] = g2;
		gArr[2] = new Grade("수학", 13.4);
		gArr[3] = new Grade("과학", 30);
		
		System.out.println(gArr[0].getName());
		System.out.println(gArr[0].getScore());
		
		System.out.println("-------------------");
		
		GradeList gList = new GradeList(gArr);
		
		System.out.println(gList.getName(0) +":" + gList.getScore(0));
		System.out.println(gList.getName(1) +":" + gList.getScore(1));
		System.out.println(gList.getName(2) +":" + gList.getScore(2));
		System.out.println(gList.getName(3) +":" + gList.getScore(3));
		System.out.println(gList.getAvg());
		System.out.println(gList.length());
		System.out.println();
		
		System.out.println("----------------------");
		
		System.out.println(Arrays.toString(gList.getUnder()));
		System.out.println("-----------------------");
		
		System.out.println(Arrays.toString(gList.getUnderScore(50)));
		
	}

}
