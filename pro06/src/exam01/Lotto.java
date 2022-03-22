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
	//메소드	//중복 검사도 해야 됨
	public void generate() {
		numbers = new int[count];
		int temp[] = new int[count];	//중복검사할 때 필요함
		
		Random random = new Random();
		numbers[0] = random.nextInt(Max-1)+Min;
		temp[0] = numbers[0];
		
		for(int i = 1; i < numbers.length; i++) {
			numbers[i] = random.nextInt(Max-1)+Min;
			for(int j = 0; j < i-1; j++) {
				if(numbers[i] == temp[j]) {
					
					break;		//만약 같다면?? 
				}else {
					temp[j] = numbers[i];
				}
			}
			
			
		}
		
	}
	//매개변수로 들어간 수가 꼭 로또 번호로 들어가 있어야 한다. 
	public void generate(int n1) {
		numbers = new int[count-1];
		Random random = new Random();
		for(int  i = 0; i < numbers.length; i++) {
			numbers[i] = random.nextInt(Max-1) + Min;
		}
		int temp[] = new int[numbers.length + 1 ];
		System.arraycopy(numbers, 0, temp, 0, numbers.length);
		numbers = temp;
		
	}
	
	
}
