package exam01;

public class Array02 {

	public static void main(String[] args) {
		/*
		 * 배열의 크기가 10인 배열을 선언하고 10~1까지의 값으로 
		 * 초기화 하도록 한다.
		 */
		
		int arr[] = new int[10];
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = 10 - i;
		}
		
		
		for(int i = 0 ; i < arr.length;	i++) {
			System.out.print(arr[i] + "\t");
		}
		System.out.println();
		
	/*  방법1
	 * 
		int[] arr1 = new int[10];
		int init = 10;
		
		for(int i = 0; i < 10; i++) {
			arr1[i] = init--;
		}
		
		
		방법2
		
		for(int i = 9; i >= 0; i--){
			arr1[i] = 10 - i;
	*/	
		
		
		/*
		 * 배열의 크기가 10인 배열을 선언하고 2부터 시작하여 짝수값에 해당하는 
		 * 값을 초기화 하도록 한다.
		 */
		
		int arr1[] = new int[10];   //선언
		
		for(int i = 2; i< arr.length; i += 2) {
			arr[i] = i; 
		}
		
		for(int i = 2; i < arr.length; i += 2) {
			System.out.print(arr[i] + "\t");
			
		}
		System.out.println();
		
	/*	방법1
	 * 
		int[] arr2 = new int[10];
		init = 2;
		
		for(int i = 0; i < 10; i++) {
			arr2[i] = init;
			init += 2;
		}
	*/	
		
		/*
		 * 배열의 크기가 5인 실수 배열을 선언하고 1부터 0.5씩 증가된 값으로
		 * 초기화 하도록 한다.
		 */
		double sum = 1.0;
		
		double arr2[] = new double[5];
		
		for(int i = 0; i < arr2.length; i++) {
			arr2[i] = sum;
			sum += 0.5;
			
		}
		
		for(int i = 0; i < arr2.length; i++) {
			System.out.print(arr2[i] + "\t");
		}
		System.out.println();
		
		/*
		 * 배열의 크기가 5인 문자 배열을 선언하고 'A' ~ 'E'까지의 문자값으로
		 * 초기화 하도록 한다.
		 */
		
		char[] arr4 = new char[5];
		
		for(int i = 0; i < arr4.length; i++) {
			arr4[i] = (char)('A' + i);
		}
		for(int i = 0; i < arr4.length; i++) {
			System.out.print(arr4[i] + "\t");
		}
		System.out.println();
		
		
		
		
		
	}

}
