package game.updown;

import java.util.Random;

public class GuessNum {
	
	private Random rand = new Random();
	private int guess;
	private int guessLimit;
	
	public GuessNum() {
		this.guess = rand.nextInt(100) + 1;	// 랜덤 숫자를 생성
		this.guessLimit = 10;		// 생성자를 생성할 때 제한을 걸어둠
	}
	
	public GuessNum(int rangeMax, int limit) {
		this.guess = rand.nextInt(rangeMax) + 1;
		this.guessLimit = limit;		
	}
	
	public GuessNum(int rangeMin, int rangeMax, int limit) {
		this.guess = rand.nextInt(rangeMax - rangeMin) + rangeMin;
		this.guessLimit = limit;
	}
	
	public Result guessing(int number) {
		Result res = new Fail();	// 기본값을 Fail로 설정함
		this.guessLimit--;		// 기회 한 번 깎기 
		if(remainCount()) {		// 만약 기회가 남았다면
			if(number > this.guess) {	// 입력값이 랜덤값보다 크다면
				res = new DOWN();		// Down 클래스 생성	
			} else if(number < this.guess) {	// 입력값이 랜덤값보다 작다면
				res = new UP();					// Up 클래스 생성
			} else if(number == this.guess) {	// 입력값이 랜덤값과 같다면 
				res = new Correct();			// Correct 클래스 생성
			}
		} else if(this.guessLimit == 0) {		// 만약 기회가 남지 않았는데
			if(number == this.guess) {			// 입력값과 랜덤값이 같다면
				res = new Correct();			// Correct 클래스 생성
			}
		}
		return res;		
		// 결과값은 Result 클래스 타입, Result는 Correct, DOWN, UP, Fail 모두를 자식으로 두기 때문에 결과값이 어떤 클래스든 반환할 수 있다.
	}
	
	public boolean remainCount() {	// 기회는 10번으로 제한
		return this.guessLimit > 0 ? true : false;
	}
}
