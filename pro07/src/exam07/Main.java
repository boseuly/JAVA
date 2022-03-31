package exam07;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
	
		Employee e1 = new Staff("김사원", 28);
		Employee e2 = new AssistantManager("박대리", 32);
		Employee e3 = new DepartmentManager("이과장", 38);
		Employee e4 = new DeputyGeneralManager("차차장", 43);
		Employee e5 = new Director("과부장", 48);
		
		Employee[] empArr = new Employee[5];
		empArr[0] = e1; empArr[1] = e2; empArr[2] = e3;
		empArr[3] = e4;	empArr[4] = e5;
		
		
		int year = 2022;
		for(int i = 0; i < 30; i++) {	// i는 개월을 의미 (30개월동안)
			int month = (i + 1) % 12 == 0 ? 12 : (i + 1) % 12;	// 나머지를 활용해서 달을 구해줌
			for(int idx = 0; idx < empArr.length; idx++) {
				
				System.out.printf("%d년 %d월 급여명세\n", year + (i + 1)/ 12, (i + 1) % 12);
				System.out.printf("이름 : %s\n", empArr[idx].getName());
				empArr[idx].payMonth();
//				bonus메소드에서 직급에 따른 보너스 날짜를 설정 해뒀다면
				empArr[idx].bonus(month);	// 보너스
				
				
//				bonus메소드에서 따로 설정 안 해두면 이런 식으로 사용자가 불편하게 사용해야 됨
//				if(empArr[idx] instanceof Staff || empArr[idx] instanceof AssistantManager) {	// 직급마다 보너스 받는 시기가 달라서 구분해주는 과정
//					switch(month) {		
//					case 6: case 12:
//						empArr[idx].bonus();
//					}
//				}else if(empArr[idx] instanceof DepartmentManager || empArr[idx] instanceof DeputyGeneralManager) {
//					switch(month) {
//					case 4: case 8: case 12:
//						empArr[idx].bonus();
//					}
//				}else if(empArr[idx] instanceof Director) {
//					switch(month) {
//					case 1:
//						empArr[idx].bonus();
//					}
//				}
				System.out.println("=============================");
			}
			Random rand = new Random();
			
			// 법인카드 사용
			if((rand.nextInt(9) + 1) % 4 == 0) {
				int loc = rand.nextInt(4) + 1;
				System.out.printf("%s(이)가 ", empArr[loc].getName());
				((AssistantManager)empArr[loc]).corpCard(1000);	// Employee에는 없는 메소드이기때문에 다운캐스팅 필수
			}
				
		}
		
	}

}
