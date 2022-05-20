package exam02;

public class LoopPrac02 {

	public static void main(String[] args) {
		
		
		//값을 감소시키면서 반복
		for(int i = 99; i> 0; i--) {
			System.out.println(i);
		}
		
		//문자를 1씩 증가시키면서 반복 문자에는 대응하는 문자코드(정수코드)가 있기 때문에 가능
		for(char c = 'A'; c <= 'Z'; c++) {
			System.out.printf("%c <-> %d\n", c, (int)c);
		}
		
		for(char c = 'a'; c <= 'z'; c++) {
			System.out.printf("%c <-> %d\n", c, (int)c);
		}
		
		for(char c = '가'; c <= '힣'; c++) {
			System.out.printf("%c <-> %d\n", c, (int)c);
		}
		
		for(char c = 'ㅏ'; c <= 'ㅣ'; c++) {
			System.out.printf("%c <-> %d\n", c, (int)c);
		}
		
		
	}

}
