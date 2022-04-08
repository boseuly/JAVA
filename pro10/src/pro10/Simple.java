package pro10;

public class Simple {

	public static void main(String[] args) {
		MyException m = new MyException();
		System.out.println("에러 발생 전");
		try {
			m.exceptionThrows();
		}catch(Exception e) {
			e.printStackTrace();   // 에러를 처리해줘야 하는 부분인데 딱히 처리할 게 없다면 printStackTrace 사용, 어떤 에러가 나왔는지 알려준다.
			
		}
		
		m.exceptionNonThrows();	// 에러 처리해주는 메소드
		
		System.out.println("에러 발생 후 처리 완료");
		
		
		
	}

}
