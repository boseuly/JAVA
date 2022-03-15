package exam01;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayTest03 {

	public static void main(String[] args) {
		/*
		 * 사용자로부터 임의의 정수 값을 입력 받고 입력받은 모든 정수의 합과 평균을 
		 * 구하는 코드를 작성한다.   (동적배열 활용하기)
		 * 
		 * -1 입력이 들어오면 종료라고 판별한다.
		 * 
		 * 예제
		 * 1번째 정수값 입력 : 7
		 * 2번째 정수값 입력 : 12
		 * 3번째 정수값 입력 : 24
		 * 4번째 정수값 입력 : -1
		 * 
		 * 총합 : 43
		 * 평균 : 14.3
		 * 
		 */
		Scanner sc = new Scanner(System.in);
	/*	
		int arr[] = null;
		int temp[] = null;
		
		
		temp = new int[arr.length+1];
		
		for(int i = 0; ;) {
			System.out.printf("%d 번째 정수값 입력 : " ,i);
			arr[i] = sc.nextInt();
			
			arr = temp;
			if(arr[i] == -1) {
				
				break;
				
			}
		}
	*/
		//이거 아직 다 못 적음,,
		//강사님 풀이
		int cnt = 1;			//몇 번째 정수값인지 확인용, 배열 인덱스에도 사용
		int total = 0;
		double avg;
		int[] arr1 = new int[0];
		
		
		while(true) {
			System.out.printf("%d번째 정수값 입력 : ", cnt);
			
			int num;
			if(sc.hasNextInt()) {
				num = sc.nextInt();  
				sc.nextLine();	//개행이 남으면 안 돼서 nextLine()을 통해 개행을 없애줘야 한다.
			}else {
				if(sc.nextLine().equals("exit")) {
					break;
				}else {
					System.out.print("정수 또는 exit를 입력하세요 : ");
					continue;
				}
			}
			
			
			//동적 배열 부분!!
			int[] temp = Arrays.copyOf(arr1, arr1.length + 1);	
			arr1 = temp;
			//
			
			
			System.out.println(Arrays.toString(arr1));
		}
		
		
		for(int i = 0; i < arr1.length; i++) {
			total += arr1[i];
		}
		
		avg = (double)total / arr1.length;
		
		System.out.println("총합 : " + total);
		System.out.println("평균 : " + avg);
		

	}

}
