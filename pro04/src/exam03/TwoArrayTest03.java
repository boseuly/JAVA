package exam03;

import java.util.Arrays;
import java.util.Random;

public class TwoArrayTest03 {

	public static void main(String[] args) {
		/*
		 *  1. 배열의 크기가 10인 정수 배열을 2개 생성 후 각 배열에 10~99 사이의 난수값을 초기화 시키고 출력한다.
		 */
		int num;
		int[] arr1 = new int[10];
		int[] arr2 = new int[10];
		
		Random random = new Random();
		for(int i = 0; i < arr1.length; i++) {
			num = random.nextInt(89) + 10;
			arr1[i] = num;
			
			num = random.nextInt(89) + 10;
			arr2[i] = num;
		}
		System.out.println("1. 결과 확인");
		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(arr2));
		
		
		/*
		 * 2. 1번 문제에서 생성한 첫번째 배열과 두번째 배열의 합을 구하여 새로운 세번째 배열을 만들고 출력한다.
		 */
		
		int[] arr3 = new int[arr1.length];
		
		for(int i = 0; i < arr1.length; i++) {
			arr3[i] = arr1[i] + arr2[i];
		}
		System.out.println("2. 결과 확인");
		System.out.println(Arrays.toString(arr3));
		
		/*
		 * 3. 2번 문제까지 진행하여 만들어진 3개의 배열을 이용하여 짝수값만 저장되어 있는 배열과 홀수값만 저장되어 있는 배열을
		 *    만들고 짝수 배열과 홀수 배열을 출력한다. 
		 */
		
		
	
	/*	//방법 1) 짝수 count, 홀수 count 해줘서 배열 크기를 정하는 방법!
		
		int cnt1 = 0, cnt2 = 0;
		
		
		for(int i = 0; i < arr1.length; i++) {
			if(arr1[i] % 2 == 0) {		//arr1짝수
				cnt1++;
				
			}else {						//홀수
				cnt2++;
			}
			
			if(arr1[i] % 2 == 0) {		//arr2짝수
				cnt1++;
			}else {						//홀수
				cnt2++;
			}
			
			if(arr1[i] % 2 == 0) {		//arr3짝수
				cnt1++;
			}else {						//홀수
				cnt2++;
			}
			
		}
	
		int[] arr4 = new int[0];
		int[] arr5 = new int[0];
		int idx1 = 0, idx2 = 0;
		
		for(int i = 0; i < arr1.length; i++) {
			if(arr1[i] % 2 == 0) {		//arr1짝수
				arr4[idx1] = arr1[i];		// 인덱스 겹치면 안되니까 idx1따로 만들어서 해준 거
				idx1++;						// 값을 넣어준 다음에는 idx1++을 해서 다음 인덱스로 넘어가게 해줘야 됨
			}else {						//홀수
				arr5[idx1] = arr1[i];
				idx2++;
				
			}
			
			if(arr1[i] % 2 == 0) {		//arr2짝수
				arr4[idx1] = arr1[i];
				idx1++;
			}else {						//홀수
				arr5[idx1] = arr1[i];
				idx2++;
			}
			
			if(arr1[i] % 2 == 0) {		//arr3짝수
				arr4[idx1] = arr1[i];
				idx1++;
			}else {						//홀수
				arr5[idx1] = arr1[i];
				idx2++;
			}
			
		}
	*/
		// 방법2) count대신 동적배열 사용해주기
		int[] arr4 = new int[0];
		int[] arr5 = new int[0];
		int idx1 = 0, idx2 = 0;		//인덱스를 겹치지 않게 하기 위한 변수
		
		for(int i = 0; i < arr1.length; i++) {
			int[] temp;
			
			if(arr1[i] % 2 == 0) {		//arr1짝수
				temp = new int[arr4.length + 1];
				System.arraycopy(arr4, 0, temp, 0, arr4.length);
				arr4 = temp;
				
				arr4[idx1] = arr1[i];		// 인덱스 겹치면 안되니까 idx1따로 만들어서 해준 거
				idx1++;						// 값을 넣어준 다음에는 idx1++을 해서 다음 인덱스로 넘어가게 해줘야 됨
			}else {						//홀수
				temp = new int[arr5.length + 1];
				System.arraycopy(arr5, 0, temp, 0, arr5.length);
				arr5 = temp;
				
				arr5[idx2] = arr1[i];
				idx2++;
				
			}
			
			if(arr2[i] % 2 == 0) {		//arr2짝수
				temp = new int[arr4.length + 1];
				System.arraycopy(arr4, 0, temp, 0, arr4.length);
				arr4 = temp;
				
				arr4[idx1] = arr2[i];
				idx1++;
			}else {						//홀수
				temp = new int[arr5.length + 1];
				System.arraycopy(arr5, 0, temp, 0, arr5.length);
				arr5 = temp;
				
				arr5[idx2] = arr2[i];
				idx2++;
			}
			
			if(arr3[i] % 2 == 0) {		//arr3짝수
				temp = new int[arr4.length + 1];
				System.arraycopy(arr4, 0, temp, 0, arr4.length);
				arr4 = temp;
				
				arr4[idx1] = arr3[i];
				idx1++;
			}else {						//홀수
				temp = new int[arr5.length + 1];
				System.arraycopy(arr5, 0, temp, 0, arr5.length);
				arr5 = temp;
				
				arr5[idx2] = arr3[i];
				idx2++;
			}
			
		}
		
		System.out.println("3. 결과 확인");
		System.out.println(Arrays.toString(arr4));
		System.out.println(Arrays.toString(arr5));
		
		
		/*
		 * 4. 짝수/홀수 배열에 있는 값들 중 중복된 값이 있는 경우 하나의 값만 남겨 새로운 배열로 만들고 출력한다.
		 */
		
	/*	방법1) 
	 * 
		int	arr6[] = new int[0];		//짝수 배열 arr4
		int arr7[] = new int[0];		//홀수 배열 arr5
		int idx3 = 0, idx4= 0;
		
		// 짝수 배열에 대한 중복 제거
		for(int i = 0; i < arr4.length; i++) {
			for(int j = i; j < arr4.length; j++) {		//이미 앞에서 확인을 했기 때문에 이전 j는 0부터 시작 안하고 i부터 해도 됨
				if(i != j) {		//자기자신은 확인 할 필요가 없기 때문에
					if(arr4[j] != -1) {				// 이미 중복으로 표시한 위치에 대해서도 중복 검사할 필요가 없기 때문에
						if(arr4[i] == arr4[j]) {
							arr4[j] = -1;		//중복된 거니까 arr6[j]의 값을 -1로 바꿔버림
						}
					}
				}
			}
			
			int[] temp;
			
			if(arr4[i] != -1) {
				temp = new int[arr6.length + 1];
				System.arraycopy(arr6, 0, temp, 0, arr6.length);
				arr6 = temp;
				
				arr6[idx3] = arr4[i];
				idx3++;
			}
			
		}
		
		//홀수 배열에 대한 중복 제거
		for(int i = 0; i < arr5.length; i++) {
			for(int j = i; j < arr5.length; j++) {		//이미 앞에서 확인을 했기 때문에 이전 j는 0부터 시작 안하고 i부터 해도 됨
				if(i != j) {		//자기자신은 확인 할 필요가 없기 때문에
					if(arr5[j] != -1) {				// 이미 중복으로 표시한 위치에 대해서도 중복 검사할 필요가 없기 때문에
						if(arr5[i] == arr5[j]) {
							arr5[j] = -1;		//중복된 거니까 arr6[j]의 값을 -1로 바꿔버림
						}
					}
				}
			}
			
			int[] temp;
			
			if(arr5[i] != -1) {		// -1이 아니라면 -> 중복이 되지 않았다면
				temp = new int[arr7.length + 1];
				System.arraycopy(arr7, 0, temp, 0, arr7.length);
				arr7 = temp;
				
				arr7[idx4] = arr5[i];
				idx4++;
			}
			
		}
		
		System.out.println("4. 결과 확인");
		System.out.println(Arrays.toString(arr6));
		System.out.println(Arrays.toString(arr7));
		
	*/	
		//방법2) 
		
		int	arr6[] = new int[1];		//짝수 배열 arr4
		int arr7[] = new int[1];		//홀수 배열 arr5
		int idx3 = 0, idx4 = 0;
		
		//짝수
		
		//0번 인덱스는 중복 확인할 필요가 없으니까 그냥 미리 적어두기
		arr6[0] = arr4[0];
		// 나머지는 for문을 통해 중복 확인해줘야 함
		for(int i = 1; i < arr4.length; i++) {		//0번 인덱스는 이미 값을 저장함 -> i=1 시작
			int temp = arr4[i];						//임시로 저장해둔 값
			boolean dup = false;					//중복값이 있으면 true로 변환시킨 뒤 break
			
			//중복된 값이 있는지 확인해주는 for문
			for(int j = 0; j < arr6.length; j++) {	//arr6에서 중복값을 찾는 거니까 arr6의 길이만큼 둘러보면 됨
				if(arr6[j] == temp) {
					dup = true;
					break;							// 중복되는 경우에는 break해서 내부for문을 중단시키고 for문을 벗어남 
				}									// -> dup = true가 돼서 값을 arr6에 저장하지 않음
			}
			
			if(!dup) {								//dup가 flase 즉, 중복이 없으면 배열 크기 늘리고 저장
				arr7 = Arrays.copyOf(arr6, arr6.length + 1);
				arr6[arr6.length - 1] = temp;		//arr6의 맨 끝 배열 값에 temp값 대입하기
			}
			
		}
		
		//홀수
		arr7[0] = arr5[0];
		for(int i = 1; i < arr5.length; i++) {
			int temp = arr5[i];
			boolean dup = false;
			
			for(int j = 0; j < arr7.length; j++) {
				if(arr7[j] == temp) {
					dup = true;
					break;
				}
			}
			if(!dup) {			
				arr7 = Arrays.copyOf(arr7, arr7.length + 1);		//동적배열
				arr7[arr7.length - 1] = temp;
			}
			
		}
		
		/*
		 *  5. 짝수/홀수 배열에 있는 값을 작은 값부터 큰값 순으로 정렬된 배열을 만들고 출력한다.
		 */
		
		int[] arr8 = arr6.clone();
		int[] arr9 = arr7.clone();
		
		//arr8 정렬
		for(int i = 0; i < arr8.length - 1; i++) {
			for(int j = i + 1; j < arr8.length; j++	) {  
				if(arr8[i] > arr8[j]) {
					//switch과정	
					int temp = arr8[i];		//임시변수에 잠시 arr8[i]의 값을 저장하고
					arr8[i] = arr8[j];		//arr[i]인덱스에 arr[j]인덱스의 값으로 저장
					arr8[j] = temp;			//다시 arr8[j]에 temp 값을 대입
					//
				}
			}
		}
		
		//arr9 정렬
		for(int i = 0; i < arr9.length - 1; i++) {
			for(int j = i + 1; j < arr9.length; j++	) {  
				if(arr9[i] > arr9[j]) {
					//switch과정	
					int temp = arr9[i];		//임시변수에 잠시 arr8[i]의 값을 저장하고
					arr9[i] = arr9[j];		//arr[i]인덱스에 arr[j]인덱스의 값으로 저장
					arr9[j] = temp;			//다시 arr8[j]에 temp 값을 대입
					//
				}
			}
		}
		
		System.out.println("5. 결과 확인");
		System.out.println(Arrays.toString(arr8));
		System.out.println(Arrays.toString(arr9));
		
		
		/*
		 * 6. 짝수 / 홀수 배열로 나누어져 있는 것을 하나의 배열로 합쳐서 하나의 배열로 만들고 출력한다.
		 */
		
		int[] arr10 = new int[arr8.length + arr9.length];		//arr8과 arr9의 길이합
		System.arraycopy(arr8, 0, arr10, 0, arr8.length);		//arr8의 0~length만큼을 arr10의 0번 인덱스부터 복사
		System.arraycopy(arr9, 0, arr10, arr8.length, arr9.length);	//arr9의 0~length만큼을 arr10의 arr8의 길이만큼의 인덱스부터 복사
		
		System.out.println("6. 결과 확인");
		System.out.println(Arrays.toString(arr10));
		
	}

}
