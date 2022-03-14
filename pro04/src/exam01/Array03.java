package exam01;

public class Array03 {

	public static void main(String[] args) {
		String[] arr1 = {"1", "2", "3", "4"};
		
		for(int i = 0; i < 4; i++) {
			System.out.print(arr1[i] + "\t");
		}
		System.out.print("\n");
		System.out.println(arr1[0].length());  
		
		
		//문자열배열 선언만 하고 출력 -> null
		String[] arr2 = new String[5];   //초기화 안 하고 선언(공간만 만들어줌)만 함  -> null값이 나온다(저장되어 있는 데이터가 없음을 의미)
		for(int i = 0; i < 5; i++) {
			System.out.print(arr2[i] + "\t");
		}
		System.out.println();
	    //System.out.println(arr2[0].length());    // 초기화를 안 하면 -> 에러메시지 java.lang.NullPointerException
		
		//정수배열 선언만 하고 출력 -> 0
		int[] arr3 = new int[5];		
		for(int i = 0; i < 5; i++) {
			System.out.print(arr3[i]+"\t");
		}
		System.out.println();
		
		double[] arr4 = new double[5];
		for(int i = 0; i < 5; i++) {
			System.out.print(arr4[i] + "\t");
		}
		
		
		
	}

}
