package exam02;

public class LoopPrac06 {

	public static void main(String[] args) {
		/*
		 * while문 형식
		 * 
		 * while(조건식){
		 * 	  반복 수행할 코드 작성
		 * }
		 * 
		 * for문과 다르게 초기식, 증감식에 대한 형식 없지만 일정 횟수 반복을 위해서는
		 * for문과 동일하게 초기식, 증감식 작성이 필요하다.
		 * 
		 */
		
		int i = 0;
		while(i < 5) {
			System.out.println("i -> " + i );
			i += 1;
		}
		
		
		/*
		 * do-while문 형식
		 * 
		 * do{
		 * 	반복 수행할 코드 작성
		 * } while(조건식);
		 * 일단 최초 1회는 무조건 반복 진행, 그 후에는 조건식의 결과 참일 때만 진행
		 */
		
		do {
			System.out.println("i -> " + i);
			i += 1;
		}while(i<5);
		
		/*
		 * break문
		 * - 반복문을 강제로 종료시키기 위해 사용하는 구문
		 * 
		 * continue문
		 * - 반복문 안에서 다음 실행코드의 진행을 막고 다시 반복문의 처음으로 돌려보내주는 구문
		 * 
		 */
		
		i = 0;
		while(i < 10) {
			i += 1;              //증감식이 continue보다 아래에 있으면 무한루프에 빠질 수도 있음
			if(i % 3 == 0) {
				System.out.println("i -> " + i);
			}else {
				continue; 
			}
			System.out.println("continue가 되면 이 출력은 동작 안 함");
		}

		
		
	}

}
