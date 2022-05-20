package exam02;

public class TestLoop01 {

	public static void main(String[] args) {
	
		// 1~99까지 반복문을 사용하여 반복출력 하시오. (단, 초기값 설정은 i = 1로 한다.)
		
		for(int i = 1; i < 100; i++ ) {
			System.out.println(i);
		}
		
		System.out.println("-------------");
		// 1~99까지 반복문을 사용하여 반복출력 하시오. (단, 초기값 설정은 i = 0로 한다.)
	
		for(int i = 0; i < 100; i++) {
			System.out.println(i);
		}
		System.out.println("-------------");
	
		
		//1~99까지 반복문을 사용하여 반복출력 하시오. (단, 짝수에 해당하는 값만 출력하시오.)
		for(int i = 1; i<100; i++) {
			if(i % 2 == 0) {               //몫이랑 나머지 헷갈려서 i / 2 == 0이라고 설정해서 오답..
				System.out.println(i);
			}
		
		}
		
		System.out.println("-------------");
		//1~99까지 반복문을 사용하여 반복출력 하시오. (단, 짝수에 해당하는 값만 출력하시오.)
		for(int i = 2; i<100; i+=2) {
			System.out.println(i);
		}
		
		
		
		
	}

}
