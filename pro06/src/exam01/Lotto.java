package exam01;

import java.util.Arrays;
import java.util.Random;

public class Lotto {
	private int rangeMin = 1;	
	private int rangeMax = 45;
	private int count = 6;	//numbers배열의 크기
	private int numbers[];
	private Random random = new Random();	//반복해서 쓰이니까 멤버변수로 만들어둠
	//기본생성자
	public Lotto() {
		this.numbers = new int[count];		//이런 설정은 생성자에 넣어두기 
	}
	//매개변수 있는 생성자
	public Lotto(int min, int max, int count) {
		this.rangeMin = min;
		this.rangeMax = max;
		this.count = count;
		this.numbers = new int[count];		//여기서는 this() 할 수 없음 -> this()는 위에 써야 하는데 그렇게 되면 배열의 크기가 초기값인 6으로 생성되기 때문에
	}										//그 다음에 매개변수로 입력을 받음 -> 배열의 크기는 변할 수 없음
	//getter & setter
	public int getRangeMin() {
		return rangeMin;
	}
	
	public void setRangeMin(int min) {
		this.rangeMin = min;
	}
	
	public int getRangeMax() {
		return rangeMax;
	}
	
	public void setRangeMax(int max) {
		this.rangeMax = max;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int[] getNumbers() {
		return numbers;
	}
	
	//값을 넣기 위한 메소드
	public void generate(int ...nums) {		//nums는 배열임-> nums[0], nums[1]	... 인스턴스 생성할 때 매개변수(nums)의 값을 넣어줌
		int cnt = nums.length;				//cnt = nums의 길이
		
		for(int  i = 0; i < cnt; i++) {		//들어온 매개변수 값(numbers배열의 값)을 
			numbers[i] = nums[i];			//numbers[i]에 넣어줘야 한다.
		}
		
		//nums값을 numbers 배열에 넣었응까 나머지는 random으로 이어서 넣어주기
		//중복검사
		for(int i = cnt; i < count;) {	
			int num = this.random.nextInt(rangeMax) + rangeMin;
			if(!_isDuplicate(num)) {		//random값 num이 중복이 안 됐으면 
				numbers[i] = num;			//num값을 numbers[i]에 넣어라
				i++;						//중복될 경우를 대비해서 -> 중복인 경우에는 if문을 실행하지 않음 -> i++도 안 됨
			}
		}
	}
	//중복검사를 위해 필요한 메서드
	private boolean _isDuplicate(int number) {
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] == number) {		//중복된 경우
				return true;
			}
		}
		return false; 		//다 돌아봤는데 중복이 없는 경우
				
	}
	
}
