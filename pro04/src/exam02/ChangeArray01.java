package exam02;

public class ChangeArray01 {

	public static void main(String[] args) {
		/*
		 * 가변 길이 2차 배열
		 */
		int[][] arr1 = new int[3][];
		arr1[0] = new int[] {1,2,3,4,5,6,7};
		arr1[1] = new int[] {1,2,3};
		arr1[2] = new int[] {1,2,3,4,5};
		
		for(int i = 0; i < arr1.length; i++) {			//arr1.length인 것과
			for(int j =0; j < arr1[i].length; j++) {	//arr1[i].length인 것 잘 구분하기
				System.out.print(arr1[i][j] + "\t");
			}
			System.out.print("\n");
		}
		
	}

}
