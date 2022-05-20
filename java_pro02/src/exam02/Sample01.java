package exam02;

public class Sample01 {

	public static void main(String[] args) {
		System.out.print("안녕하세요.");
		System.out.print("반갑습니다.");
		
		System.out.println("안녕하세요.");
		System.out.println("안녕하세요.");
		
		// 출력형식 format
		int i = 30;
		System.out.println("당신은 현재 " + i + "대입니다.");
		System.out.printf("당신은 현재 %d대입니다.\n", i);     //\n : escape문자, 개행
		System.out.printf("당신은 현재 %o대입니다.\n", i);
		System.out.printf("당신은 현재 %x대입니다.\n", i);
		System.out.printf("당신은 현재 %c대입니다.\n", i);
		
		double d = 1234.5678;
		System.out.printf("%f\n",d);
		System.out.printf("%.2f\n", d);
		System.out.printf("%e\n", d);
		System.out.printf("%g\n", d);
		System.out.printf("%A\n", d);
		
		//boolean형은 출력 형식을 변환할 필요가 없음/ boolean형은 null데이터만 false로 출력
		
		
		
		
	}

}
