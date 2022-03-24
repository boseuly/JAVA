package exam02;

import java.util.Arrays;

public class Run {

	public static void main(String[] args) {
		ReportGrade01 report = new ReportGrade01("홍길");
		
		double[] grades = new double[] {78.5, 79.4, 68.9, 89.2, 94.7};
		String[] subjects = new String[] {"국어", "영어", "수학", "사회","체육"};
		report.setGrades(grades);
		report.setSubjects(subjects);
		
		report.setGrades("수학", 94.7);
		System.out.println(Arrays.toString(report.getGrades()));
		
		//getgrades에 과목명 입력하면 그에 해당하는 점수가 출력 되는 메소드 만들어보기
		System.out.println(report.getGrade("사회"));
		report.addSubject("과학", 89.0);
		System.out.println(report.getGrade("과학"));
		
	}

}
