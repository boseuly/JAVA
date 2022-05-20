package exam01;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ArrayTest02 {

	public static void main(String[] args) {
		/*
		 * 배열의 크기가 5인 정수 배열을 생성하고 해당 배열에 1~19까지의 정수 값을 
		 * 임의로 생성하여 초기화 하는 작업을 수행하도록 한다.
		 * 단, 배열에 초기화된 값은 중복되어서는 안 된다.
		 */
		
		//선언부
		int arr[] = new int[5];   //일단 5개의 공간만 만들어놓음
		int ran;
		boolean duplicate;
		
		//로직 & 연산
		Random random = new Random();
		   // 1~19 중 랜덤값을 ran에 저장
		
		for(int i = 0; i < arr.length; ) {      //i++을 나중에 하는 이유 => duplicate
			duplicate = false;					//duplicate는 다시 false로 바꿔놔야 됨!! 여기 주의!!!
			ran = random.nextInt(19) + 1;		// +1을 해주는 이유 -> random.nextInt(19) -> 0~18까지의 값이 나옴 
												// -> 1~19까지의 값을 나오게 하기 위해서 +1씩 한 것
			for(int j = 0; j < i; j++) {    	//i보다 작을 때까지만 반복시켜준다.
				if(arr[j] == ran) {             //ran값이랑 arr배열 값이랑 비교해줌
					duplicate = true;			//duplicate를 true로 설정해주기
					break;						//멈추고 다음 걸 실행해라 
				}              
			}
			if(!duplicate) {					//duplicate가 true가 아니라면 -> 만약 duplicate = true면 실행 X
				arr[i] = ran;					//arr[i] = ran; 을 실행해라
				i++;
			}
			System.out.println(Arrays.toString(arr));   //출력할 때 반복문보다는 Arrays.toString 사용하면 한줄로 끝낼 수 있음
		}

		/*
		 * 사용자로부터 임의의 정수 값을 입력 받고 입력 받은 모든 정수의 합과 평균을 
		 * 구하는 코드를 작성한다.
		 * 사용자로부터 임의의 정수 값을 입력 받기 전에 총 몇 개의 정수값을 입력 받을지
		 * 확인 후 작업한다.
		 * 
		 * 예시
		 * 총 입력 횟수 : 3
		 * 1번째 정수값 입력 : 7
		 * 2번째 정수값 입력 : 12
		 * 3번째 정수값 입력 : 24
		 * 
		 * 총 합 : 43
		 * 평 균 : 14.3
		 * 
		 */
		int total = 0;
		double avg = 0.0;
		
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("총 입력 횟수 : ");
		int count = sc.nextInt();
		int[] arr1 = new int[count];		//count만큼의 크기를 만든다
		
		
		for(int i = 0; i < arr1.length; i++) {
			System.out.printf("%d번째 정수값 입력 : " , i + 1);
			arr1[i] = sc.nextInt();
			total += arr1[i];
		}
		
		avg = (double)total / arr1.length;
		
		System.out.println("총 합 : " + total);
		System.out.println("평 균 : " + avg);
		
		
	}

}
