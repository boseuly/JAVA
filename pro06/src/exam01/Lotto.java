package exam01;

import java.util.Arrays;
import java.util.Random;

public class Lotto {
	private int Min = 1;
	private int Max = 45;
	private int count = 6;
	private int numbers[];
	
	//기본생성자
	public Lotto() {}
	//매개변수 있는 생성자
	public Lotto(int min, int max, int count) {
		this.Min = min;
		this.Max = max;
		this.count = count;
	}
	//getter & setter
	public int getMin() {
		return Min;
	}
	
	public void setMin(int min) {
		this.Min = min;
	}
	
	public int getMax() {
		return Max;
	}
	
	public void setMax(int max) {
		this.Max = max;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int[] numbers() {
		return numbers;
	}
	//메소드
	public void generate() {
		numbers = new int[count];
		Random random = new Random();
		for(int i = 0; i < numbers.length; i++) {
			numbers[i] = random.nextInt(Max-1)+Min;
		}
		System.out.println(Arrays.toString(numbers));
	}
	
}
