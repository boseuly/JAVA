package exam01;

import java.util.Arrays;

public class Array07 {

	public static void main(String[] args) {
		
		/* 
		 * 반복문을 사용한 깊은 복사 작업을 자바에서 제공하는 기능으로 바꿔서 작업
		 */
		
		System.out.println("------반복문------");
		int[] arr1 = new int[] {1,2,3,4,5};
		
		
		//방법1) 반복문을 사용한 깊은 복사
		int[] arr2 = new int[arr1.length];
		for(int i = 0; i < arr1.length; i++) {
			arr2[i] = arr1[i];
			
		}
		
		System.out.println("--------arraycopy-------");
		
		//방법2) System.arraycopy를 이용한 깊은 복사 -> 인덱스부터 시작해서 복사할 수 있음 
		int[] arr3 = new int[arr1.length];
		System.arraycopy(arr1, 1, arr3, 0, arr1.length-1);
		
		//arr1[0] = 10;
		for(int i = 0; i < arr1.length; i++) {
			System.out.printf("arr1[%d] -> %d, arr3[%d] 0-> %d\n", i, arr1[i], i, arr3[i]);
		}
		//System.out.printf("arr1[0] -> %d, arr3[0] -> %d\n", arr1[0], arr3[0]);
		
		
		System.out.println("--------Arrays.copyOf-------");
		
		//방법3) Arrays.copyOf 사용한 깊은 복사 -> 길이를 늘리거나 줄여 복제를 하고 싶을 때
		int[] arr4 = Arrays.copyOf(arr1, arr1.length + 1);   // arr4의 길이를 +1 
		arr1[0] = 20;
		System.out.printf("arr1[0] -> %d, arr4[0] -> %d\n", arr1[0], arr4[0]);  
		System.out.printf("arr4.length -> %d\n", arr4.length);   //arr1은 길이가 5, arr4는 길이가 6
		
		System.out.println("--------clone-------");
		
		
		//방법4) .clone()을 사용한 깊은 복사 -> arr1이 가지고 있는 값을 그대로 복사하고 싶을 때(길이도 똑같이)
		int[] arr5 = arr1.clone();
		
		
		//Array치고 ctrl + space 누르면 import 목록 뜸
		
		arr1[0] = 30;
		System.out.printf("arr1[0] -> %d, arr5[0] -> %d\n", arr1[0], arr5[0]);
		
		
		
		
	}

}
