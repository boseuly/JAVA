package exam01;

import java.util.Arrays;

public class Array08 {

	public static void main(String[] args) {
		/*
		 * 동적 배열 
		 * - 기존의 배열은 크기가 한 번 정해지면 크기를 늘리거나 줄일 수 없다.
		 * - 동적배열은 원한 배열의 크기를 늘리거나 줄일 수 있도록 기존 배열을 조작해준다.
		 * - 최초 배열의 크기보다 크거나 작은 배열을 새로 만들고 새로 생성된 배열에 기존 배열의 값을 복사하는 형식으로 작업 
		 * 
		 */
		int[] arr1 = new int[3];
		arr1[0] = 10;   arr1[1] = 20;   arr1[2] = 30;
			
		
		//방법1) for문을 이용한 크기 늘리기
		int[] temp = new int[arr1.length + 1];
		for(int i = 0; i < arr1.length; i++) {
			temp[i] = arr1[i];
		}
		
		//방법2) Arrays.copyOf이용해서 크기 늘리기
		temp = Arrays.copyOf(arr1, arr1.length + 1);
		arr1 = temp;		//계속 arr1이라는 이름을 쓰기 위해 얕은 복사까지 해준다.
		
		//방법3) System.arraycopy사용해서 늘리기
		temp = new int[arr1.length + 1];
		System.arraycopy(arr1, 0, temp, 0, arr1.length);		
		arr1 = temp;
		
		
		System.out.println(Arrays.toString(arr1));
		
		
		
	}

}
