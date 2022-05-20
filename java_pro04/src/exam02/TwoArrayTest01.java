package exam02;

import java.util.Random;

public class TwoArrayTest01 {

	public static void main(String[] args) {
		/*
		 * 1~16 까지의 정수를 4X4 배열에 순차적으로 저장하고 표 형식으로 출력하시오.
		 */
		int[][] arr1 = new int[4][4];
		int num = 1;		//+1씩 해주기 위해
		
		for(int i = 0; i < arr1.length; i++) {
			for(int j = 0; j < arr1[i].length; j++) {
				arr1[i][j] = num++;
			}
			System.out.print("\n");
		}
		
		for(int i = 0; i < arr1.length; i++) {
			for(int j = 0; j < arr1[i].length; j++) {
				System.out.print(arr1[i][j] + "\t");
			}
			System.out.print("\n");
		}
		System.out.println("--------------------------------------");
		
		
		/*
		 * 1~49 사이의 임의의 정수를 5X6 배열에 순차적으로 저장하고 표 형식으로 출력하시오. 
		 */
		
		int[][] arr2 = new int[5][6];
		Random random = new Random();
		
		for(int i = 0; i < arr2.length; i++) {
			for(int j = 0; j < arr2[i].length; j++) {
				int ran = random.nextInt(49) + 1;    //random.nextInt(49)  -> 0~48까지
				arr2[i][j] = ran;
			}
		}
			
		for(int i = 0; i < arr2.length; i++) {
			for(int j = 0; j < arr2[i].length; j++) {
				System.out.print(arr2[i][j] + "\t");
			}
			System.out.println();
		}
		
		System.out.println("---------------------------");
		/*
		 * 1~49 사이의 임의의 정수를 5XN 배열에 순차적으로 저장하고 표 형식으로 출력하시오.
		 * (N의 경우 3~6 사이의 임의의 크기로 만들어 저장되도록 한다. -> 가변 길이 2차배열)
		 */
		int[][] arr3 = new int[5][];
		   
		
		for(int i = 0; i < arr3.length; i++) {
			arr3[i] = new int[random.nextInt(4) + 3];
			
			for(int j = 0; j < arr3[i].length; j++) {
				arr3[i][j] = random.nextInt(49) +1;
			}
		}
		for(int i = 0; i < arr3.length; i++) {
			for(int j = 0; j < arr3[i].length; j++) {
				System.out.print(arr3[i][j] + "\t");
			}
			System.out.print("\n");
		}	
			
		
		
	}

}
