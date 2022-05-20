package exam08;

import java.util.Random;

public class MethodSample {
	//반환형이 void인 경우에는 return 안 써도 됨, main매서드에서 System.out.println()
	public void method01() {
		System.out.println("반환 타입은 void이고 매개변수가 없는 메서드");
		
	}
	
	public int method02() {
		System.out.println("반환타입은 int(기본 자료형)이고 매개변수가 없는 메서드");
		return 0;
	}
	
	public int[] method03() {
		int[] res = new int[5];
		System.out.println("반환타입은 int[](배열)이고 매개변수가 없는 메서드");
		return res;
	}
	
	public String method04() {
		String res = new String();
		System.out.println("반환타입은 String(클래스/객체)이고 매개변수가 없는 메서드");
		return res;
	}
	
	public void method05(int[] arr) {
		System.out.println("반환 타입은 void이고 매개변수가 배열인 메서드");
		System.out.println(arr);		//매개변수를 통해 전달받은 값을 출력해보기, arr = arg1 -> 앝은 복사된 거라고 보면됨
		for(int i = 0; i < arr.length; i++) {
			System.out.println("arr[" + i + "] -> " + arr[i]);
		}
		arr[0] = 10;
	}
	//만약 int값을 두개 반환 받고 싶으면 int[] 배열을 만들어주면 됨
	public int[] method06(Random r) {
		System.out.println("반환타입은 void이고 매개변수가 클래스(객체)인 메서드");
		int x1 = r.nextInt(10);
		int x2 = r.nextInt(10);
		return new int[] {x1, x2};
	}
	public void method08(Random r) {
		System.out.println("반환타입은 void이고 매개변수가 클래스(객체)인 메서드");
		r.nextInt(10);
	}
	
	public void method07(int ... nums) {
		System.out.println("반환타입은 void이고 매개변수가 가변인자인 메서드");
		System.out.println(nums);
		for(int i = 0; i < nums.length; i++) {
			System.out.println("arr[" + i + "] -> " + nums[i]);
		}
	}
	
	
}
