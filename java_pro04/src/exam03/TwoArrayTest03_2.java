package exam03;

import java.util.Arrays;
import java.util.Random;

public class TwoArrayTest03_2 {

	public static void main(String[] args) {
		/*
		 * 1. 배열의 크기가 10 인 정수 배열을 2개 생성 후 각 배열에 10 ~ 99 사이의
		 *    난수 값으로 초기화 하고 출력.
		 */
		
		int[] arr1 = new int[10];
		int[] arr2 = new int[10];
		
		Random random = new Random();
		
		
		for(int i = 0; i < arr1.length; i++) {
			int num = random.nextInt(89) + 1;
			arr1[i] = num;
			
			num = random.nextInt(89) + 1;
			arr2[i] = num;
		}
		System.out.println("1번 문제");
		System.out.print("arr1 출력 : ");
		System.out.println(Arrays.toString(arr1));
		System.out.print("arr2 출력 : ");
		System.out.println(Arrays.toString(arr2));
		
		/*
		 * 2. 1번 문제에서 생성한 첫번째 배열과 두번째 배열의 합을 구하여 새로운 세번째 배열을 만들고 출력한다.
		 */
		
		int arr3[] = new int[arr1.length];
		
		for(int i = 0; i < arr1.length; i++) {
			arr3[i] = arr1[i] + arr2[i];
		}
		
		System.out.println("2번 문제");
		System.out.print("arr3 출력 : ");
		System.out.println(Arrays.toString(arr3));
		
		/*
		 * 3. 2번 문제까지 진행하여 만들어진 3개의 배열을 이용하여 짝수값만 저장되어 있는 배열과 홀수값만 저장되어 있는
		 *    배열을 만들고 짝수 배열과 홀수 배열을 출력한다.
		 */
		
		int arr4[] = new int[0];	//짝수 저장 배열
		int arr5[] = new int[0];	//홀수 저장 배열 
		int idx1 = 0, idx2 = 0;
		
		//arr1 짝홀 구별
		for(int i = 0; i < arr1.length; i++) {
			int temp[];
			if(arr1[i] % 2 == 0) {		//짝수인 경우
				temp = new int[arr4.length + 1];
				System.arraycopy(arr4, 0, temp, 0, arr4.length);
				arr4 = temp;		// arr4에 temp 객체 주소값 저장
				
				arr4[idx1] = arr1[i];	// arr1[i]값이 짝수 -> arr4[idx]에 대입
				idx1++;					// 다음 인덱스로 넘어가게 해야 하기 때문에
			}else {		//홀수인 경우 
				temp = new int[arr5.length + 1];
				System.arraycopy(arr5, 0, temp, 0, arr5.length);
				arr5 = temp;			// arr5에 temp 객체주소값 저장
				
				arr5[idx2] = arr1[i];
				idx2++;
			}
		}
		
		//arr2 짝홀 구별
		for(int i = 0; i < arr2.length; i++) {
			int temp[];
			if(arr2[i] % 2 == 0) {		//짝수인 경우
				temp = new int[arr4.length + 1];
				System.arraycopy(arr4, 0, temp, 0, arr4.length);
				arr4 = temp;		// arr4에 temp 객체 주소값 저장
				
				arr4[idx1] = arr2[i];	// arr1[i]값이 짝수 -> arr4[idx]에 대입
				idx1++;					// 다음 인덱스로 넘어가게 해야 하기 때문에
			}else {		//홀수인 경우 
				temp = new int[arr5.length + 1];
				System.arraycopy(arr5, 0, temp, 0, arr5.length);
				arr5 = temp;			// arr5에 temp 객체주소값 저장
				
				arr5[idx2] = arr2[i];
				idx2++;
			}
		}
		
		//arr3 짝홀 구별
		for(int i = 0; i < arr3.length; i++) {
			int temp[];
			if(arr3[i] % 2 == 0) {		//짝수인 경우
				temp = new int[arr4.length + 1];
				System.arraycopy(arr4, 0, temp, 0, arr4.length);
				arr4 = temp;		// arr4에 temp 객체 주소값 저장
				
				arr4[idx1] = arr3[i];	// arr1[i]값이 짝수 -> arr4[idx]에 대입
				idx1++;					// 다음 인덱스로 넘어가게 해야 하기 때문에
			}else {		//홀수인 경우 
				temp = new int[arr5.length + 1];
				System.arraycopy(arr5, 0, temp, 0, arr5.length);
				arr5 = temp;			// arr5에 temp 객체주소값 저장
				
				arr5[idx2] = arr3[i];
				idx2++;
			}
		}
		
		System.out.println("3번 문제");
		System.out.print("arr4 출력 : ");
		System.out.println(Arrays.toString(arr4));
		System.out.print("arr5 출력 : ");
		System.out.println(Arrays.toString(arr5));
	
		/*
		 * 4. 짝수/홀수 배열에 있는 값들중 중복된 값이 있는 경우 하나의 값만 남겨 새로운 배열로 만들고 출력한다.
		 */
		int arr6[] = new int[1];	// 중복 없앤 짝수 배열
		int arr7[] = new int[1];	// 중복 없앤 홀수 배열
		
		//첫번째 값은 그냥 넣어주기
		arr6[0] = arr4[0];		
		
		//짝수 중복 없애기 -> arr6 저장
		for(int i = 1; i < arr4.length; i++) {
			int temp = arr4[i];			//arr4[i]의 값을 temp에 임의로 저장
			boolean bool = false;		//중복된 값인지 아닌지 판별
			
			for(int j = 0; j < arr6.length; j++) {	//중복 확인
				if(arr6[j] == temp) {
					bool = true;			//중복된 경우
					break;
				}
			}
			if(!bool) {
				arr6 = Arrays.copyOf(arr6, arr6.length + 1);
				arr6[arr6.length - 1] = temp;
			}
			
		}
		
		//홀수 중복 없애기 -> arr7 저장
		arr7[0] = arr5[0];
		for(int i = 1; i < arr5.length; i++) {
			int temp = arr5[i];			//arr4[i]의 값을 temp에 임의로 저장
			boolean bool = false;		//중복된 값인지 아닌지 판별
			
			for(int j = 0; j < arr7.length; j++) {	//중복 확인
				if(arr7[j] == temp) {
					bool = true;			//중복된 경우
					break;
				}
			}
			if(!bool) {		// 중복되는 게 없다면
				arr7 = Arrays.copyOf(arr7, arr7.length + 1);		// 동적배열
				arr7[arr7.length - 1] = temp;
			}
			
		}
		
		System.out.println("4번 문제");
		System.out.print("arr6 출력 : ");
		System.out.println(Arrays.toString(arr6));
		System.out.print("arr7 출력 : ");
		System.out.println(Arrays.toString(arr7));
	

		/*
		 * 5. 짝수/홀수 배열에 있는 값을 작은값 부터 큰값 순으로 정렬된 배열을 만들고 출력한다.
		 */
		int arr8[] = arr6.clone();
		int arr9[] = arr7.clone();
		
		for(int i = 0; i < arr8.length - 1; i++	) {
			for(int j = i+1; j < arr8.length ; j++) {
				if(arr8[i] > arr8[j]) {	//arr6[i]가 arr6[j]보다 크면 자리를 switch시켜야 한다.
					int temp = arr8[i];
					arr8[i] = arr8[j];
					arr8[j] = temp;
				}
			}
		}
		
		for(int i = 0; i < arr9.length - 1; i++	) {
			for(int j = i+1; j < arr9.length ; j++) {
				if(arr9[i] > arr9[j]) {	//arr6[i]가 arr6[j]보다 크면 자리를 switch시켜야 한다.
					int temp = arr9[i];
					arr9[i] = arr9[j];
					arr9[j] = temp;
				}
			}
		}
		
		System.out.println("5번 문제");
		System.out.print("arr8 출력 : ");
		System.out.println(Arrays.toString(arr8));
		System.out.print("arr9 출력 : ");
		System.out.println(Arrays.toString(arr9));
		
		/*
		 * 6. 짝수/홀수 배열로 나누어져 있는 것을 하나의 배열로 합쳐서 하나의 배열로 만들고 출력한다.
		 */
		
		int arr10[] = new int[arr8.length + arr9.length];
		System.arraycopy(arr8, 0, arr10, 0, arr8.length);
		System.arraycopy(arr9, 0, arr10, arr8.length, arr9.length);
		
		System.out.println("6번 문제");
		System.out.print("arr10 출력 : ");
		System.out.println(Arrays.toString(arr10));
	}

}
