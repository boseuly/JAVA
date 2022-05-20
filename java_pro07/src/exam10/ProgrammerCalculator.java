package exam10;

public class ProgrammerCalculator extends ScientificCalculator implements Programmer {

	@Override
	public void binaryNumber() {
		System.out.println("2진수 결과가 나왔습니다.");
	}

	@Override
	public void octalDigit() {
		System.out.println("8진수 결과가 나왔습니다.");
	}

	@Override
	public void hexadecimalNumber() {
		System.out.println("16진수 결과가 나왔습니다.");
	}

}
