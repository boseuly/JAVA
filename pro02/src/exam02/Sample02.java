package exam02;

public class Sample02 {

	public static void main(String[] args) {
		
		/*
		 * format활용
		 */
	
		// 오른쪽 정렬 (표만들 때 이런 정렬을 쓰면 좋음)
		System.out.printf("%6s | %5s\n", "name", "age");  //10글자, 5글자를 출력할 수 있는 자리를 만든다.
		System.out.printf("%6s | %5d\n", "Hong", 32);
		System.out.printf("%6s | %5d\n", "Kim", 27);
		System.out.printf("%6s | %5d\n", "Park", 29);
		
		System.out.println("---------------------------------------");
		
		//왼쪽 정렬
		System.out.printf("%-5s | %-5s\n", "name", "age");
		System.out.printf("%-5s | %-5d\n", "Hong", 42);
		System.out.printf("%-5s | %-5d\n", "Kim", 31);
		System.out.printf("%-5s | %-5d\n", "Park", 30);
		
		System.out.println("---------------------------------------");
		
		// 소수자리 포함시키기
		System.out.printf("%-5s | %-5s\n", "name", "age");
		System.out.printf("%-5s | %-5.1f\n", "Hong", 42.0);
		System.out.printf("%-5s | %-5.1f\n", "Kim", 31.2);
		System.out.printf("%-5s | %-5.1f\n", "Park", 30.3);
		
		System.out.println("---------------------------------------");
		
		// 한글을 사용할 때 문제점
		System.out.printf("%6s | %5s\n", "name", "age");   //한글은 한 자에 2자리를 차지
		System.out.printf("%6s | %5.1f\n", "홍길동", 42.0);
		System.out.printf("%6s | %5.1f\n", "김건", 31.2);
		System.out.printf("%6s | %5.1f\n", "박지우", 30.3);
		
		
		
	}

}
