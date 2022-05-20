package exam10;

public class Main {

	public static void main(String[] args) {
		Calculator c1 = new ProgrammerCalculator();
		
		System.out.println(c1.addition(2.3, 5.6));
		
		((ProgrammerCalculator)c1).binaryNumber();
		Calculator c2 = new ScientificCalculator();
		((Scientific)c2).absoluteValue(10);
		
		
		ProgrammerCalculator pc = (ProgrammerCalculator) c1;
		pc.absoluteValue(10);
		
		
		
	}

}
