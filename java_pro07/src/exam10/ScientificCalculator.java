package exam10;

public class ScientificCalculator extends Calculator implements Scientific {
	// 공학용 계산기
	
	
	@Override
	public void remainder(int a, int b) {
		System.out.printf("%d를 %d로 나눈 나머지는 %d입니다.", a, b, a % b);
	}

	@Override
	public void square(int a, int b) {
		System.out.printf("%d의 %d제곱 결과가 나왔습니다.", a, b);
	}
	@Override
	public void absoluteValue(int a) {
		System.out.println("%d의 절대값 결과가 나왔습니다.");
	}
	
	
	
	
}
