package exam02;

import java.util.Arrays;

public class TwoArray01 {

	public static void main(String[] args) {
		/*
		 * 2차 배열
		 * - 배열 안에 배열을 만들어서 사용하는 형태
		 * - 표(테이블) 형식의 데이터를 사용할 때 많이 사용됨
		 */
		
		
	/*	
		//초기화 방법1)
		int[][] arr1 = new int[3][3];
		
		arr1[0][0] = 1;   arr1[0][1] = 2; arr1[0][2] = 3;
		arr1[1][0] = 4;  arr1[1][1] = 5; arr1[1][2] = 6;
		arr1[2][0] = 7;   arr1[2][1] = 8; arr1[2][2] = 9;
	*/	
		//초기화 방법2) 초기값 바로 넣어주기
		int[][] arr1 = new int[][] {{1,2,3}, {4,5,6},{7,8,9}};
		
		
		//배열을 출력하기 위해서는 Arrays.toString사용!!!!
		for(int i = 0; i< arr1.length; i++) {			
			System.out.println(Arrays.toString(arr1[i]));	//Arrays.toString 기능을 안 사용했으면 중첩반복문 사용해야 됨
		}
	
		
	/*	중첩 반복문을 사용해서 출력한 경우
	 
		for(int i = 0; i < arr1.length;i++) {
			for(int j =0; j<arr1.length; j++) {
				System.out.print(arr1[i][j] + "\t");
			}
		}
	*/
		
		
		// 참고로 이렇게 하면 실제값을 참조하고 있는 참조배열의 주소값이 나옴
		System.out.println(Arrays.toString(arr1));   
		
		
	}

}
