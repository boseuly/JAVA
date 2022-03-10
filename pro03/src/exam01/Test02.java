package exam01;

import java.util.Scanner;

public class Test02 {

	public static void main(String[] args) {
		/*
		 * Scanner를 사용하여 사용자 입력을 받고 다음의 결과가 나오도록 하시오.
		 * 1. 사용자 입력을 통해 국어, 영어, 수학 점수를 입력받는다. (정수)
		 * 2. 입력 받은 정수 데이터의 값에 대해 총점과 평균을 구한다.
		 * 3. 평균 점수가 60점 이상인 경우에만 다음의 메시지를 출력한다.
		 * 		"축하합니다. 합격입니다."
		 * 		총점 : 213 점
		 * 		국어 : 75 점
		 * 		영어 : 70 점
		 *  	수학 : 68 점
		 * 		평균 : 71.0 점   //소수점 한자리까지   
		 * 4. 평균 점수가 60점 미만인 경우에는 다음의 메시지를 출력한다.
		 * 		"평균 점수가 미달하였습니다."
		 * 5. 국어, 영어, 수학 과목 중 40점 미만의 과목이 있는 경우 다음의 메시지를 출력한다.
		 * 		"과락된 과목이 있어 불합격 되었습니다."
		 */
		
		//선언부
		int kor, eng, math;   //평균과 총점을 구해야 하니까
		int total;            //총점
		double avg;			  //평균
		
		//초기화 //점수 입력 받기
		Scanner sc = new Scanner(System.in);
		System.out.print("국어점수를 입력하시오 : ");
		
		kor = sc.nextInt();
		System.out.print("영어점수를 입력하시오 : ");
		
		eng = sc.nextInt();
		System.out.print("수학점수를 입력하시오 : ");
		math = sc.nextInt();
		
		//연산 & 로직
		total = kor + eng + math;
		avg = total / 3;              //avg가 실수라 int는 자동 형변환 됨
		
	/*
		if(kor < 40 || eng < 40 || math < 40) {                       //공통적인 항목이니까 외부for문
			System.out.println("과락된 과목이 있어 불합격되었습니다.");
		}else {
			if(avg >= 60) {
				System.out.printf("축하합니다. 합격입니다.\n"
						+ "총점 : %d 점\n"
						+ "국어 : %d 점\n"
						+ "영어 : %d 점\n"
						+ "수학 : %d 점\n"
						+ "평균 : %.1f 점"
						, total, kor, eng, math, avg);
			}else {
				System.out.println("평균 점수가 미달하였습니다.");
			}
		
		}
	*/
		
	/*	
	 	<강사님이 푸신 방법>
	 	
		if(kor < 40 || eng < 40 || math < 40) {
			System.out.println("과락된 과목이 있어 불합격 되었습니다.");
		}else {
			if(avg >= 60) {
				System.out.println("축하합니다. 합격입니다.");
				System.out.printf(" 총점 : %d 점\n", total);
				System.out.printf(" 국어 : %d 점\n", kor);
				System.out.printf(" 영어 : %d 점\n", eng);
				System.out.printf(" 수학 : %d 점\n", math);
				System.out.printf(" 평균 : %.1f 점\n", avg);
			}else {
				System.out.println("평균 점수가 미달하였습니다.");
			}
		}
		
	*/
		
		//만약 과락된 과목이 무엇인지 알려주기 위해서는 ?? (추가문제)
		
		if(kor < 40 || eng < 40 || math < 40) {
			if(kor < 40) { 		 //얘는 중첩 if문 if-else if 사용하려 했는데 그렇게 하면 과락 과목이 여러개 나올 때 한 과목만 나옴!!
				System.out.println("국어 점수가 과락으로 불합격입니다.");
			}
			if(eng < 40) {
				System.out.println("영어 점수가 과락으로 불합격입니다.");
			}
			if(math < 40){
				System.out.println("수학 점수가 과락으로 불합격입니다.");
			}
		}else {
			if(avg >= 60) {
				System.out.println("축하합니다. 합격입니다.");
				System.out.printf(" 총점 : %d 점\n", total);
				System.out.printf(" 국어 : %d 점\n", kor);
				System.out.printf(" 영어 : %d 점\n", eng);
				System.out.printf(" 수학 : %d 점\n", math);
				System.out.printf(" 평균 : %.1f 점\n", avg);
			}else {
				System.out.println("평균 점수가 미달하였습니다.");
			}
		}
		
		
	}

}
