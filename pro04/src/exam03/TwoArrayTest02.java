package exam03;

import java.util.Arrays;
import java.util.Random;

public class TwoArrayTest02 {

	public static void main(String[] args) {
		/*
		 *  1. 배열의 크기가 10인 정수 배열을 2개 생성 후 각 배열에 10~99 사이의 난수값을 초기화 시키고 출력한다.
		 */
		System.out.println("---------1번 문제-----------");
		
		// 배열의 크기가 10인 정수 배열 2개 생성
		int[] arr1 = new int[10];
		int[] arr2 = new int[10];
		
		// 각 배열에 10~99 사이의 난수값 초기화 -> 반복문 사용
		Random random = new Random();
		for(int i = 0; i < arr1.length; i++) {
			arr1[i] = random.nextInt(89) + 10;
			arr2[i] = random.nextInt(89) + 10;
		}
		
		// 출력
		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(arr2));
		
		System.out.println("---------2번 문제-----------");
		
		/*
		 * 2. 1번 문제에서 생성한 첫번째 배열과 두번째 배열의 합을 구하여 새로운 세번째 배열을 만들고 출력한다.
		 */
		int arr3[] = new int[arr1.length];	//arr1의 길이는 10
		
		for(int i = 0; i < arr1.length; i++) {
			arr3[i] = arr1[i] + arr2[i];
		}
		
		System.out.println(Arrays.toString(arr3));
		
		System.out.println("---------3번 문제-----------");
		
		/*
		 * 3. 2번 문제까지 진행하여 만들어진 3개의 배열을 이용하여 짝수값만 저장되어 있는 배열과 홀수값만 저장되어 있는 배열을
		 *    만들고 짝수 배열과 홀수 배열을 출력한다. 
		 */
		
		boolean bool = false;		//짝홀 구별 변수
		int arr4[] = null; 
		int arr5[] = null;		//짝수값 , 홀수값 배열
		
		//짝홀 구별을 해야 함
		for(int i = 0; i < arr1.length; i++) {
			System.out.print("arr1 " + i +"번째 : " + arr1[i] + "\n");
			System.out.print("arr2 " + i +"번째 : " + arr2[i] + "\n");
			System.out.print("arr3 " + i +"번째 : " + arr3[i] + "\n");
			for(int j = 0; j < arr1.length; j++) {
				if(arr1[i] % 2 == 0) {			//짝수
					continue;	//다시 for문으로
				}else if(arr1[i] % 2 != 0){		//홀수
				
				}
			
				if(arr2[i] % 2 == 0) {
				
				}else {
				
				}
				
			}
			
			
			
		}
		
		System.out.println(Arrays.toString(arr4));
		System.out.println(Arrays.toString(arr5));
		
		/*
		 * 4. 짝수/홀수 배열에 있는 값들 중 중복된 값이 있는 경우 하나의 값만 남겨 새로운 배열로 만들고 출력한다.
		 */
		
		
		
		
		/*
		 *  5. 짝수/홀수 배열에 있는 값을 작은 값부터 큰값 순으로 정렬된 배열을 만들고 출력한다.
		 *  
		 */
		
		
		
		
		/*
		 * 6. 짝수 / 홀수 배열로 나누어져 있는 것을 하나의 배열로 합쳐서 하나의 배열로 만들고 출력한다.
		 */
		
	}

}
