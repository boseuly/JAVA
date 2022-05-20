package exam10.teacher;

public class ProgrammerCalc extends Calculator implements Programmer{

	@Override
	public String binary(int n1) {
		String b = "";
		while(n1 > 1) {
			b = n1 % 2 + b;
			n1 /= 2;
		}
		return n1 + b;
	}

	@Override
	public String octal(int n1) {
		String o = "";
		while(n1 > 7) {
			o = n1 % 8 + o;
			n1 /= 8;
		}
		return n1 + o;
	}

	@Override
	public String hexa(int n1) {
		String h = "";
		while(n1 > 15) {
			int x = n1 % 16;
			if(x >= 10) {
				h = hexCode(x) + h;
			} else {
				h = x + h;				
			}
			n1 /= 16;
		}
		
		return (n1 >= 10 ? hexCode(n1) : n1) + h;
	}
	
	private char hexCode(int n1) {
		return (char)(n1 + 55);
	}

}
