package exam10.teacher;

public class EngineerCalc extends Calculator implements Engineer{
	@Override
	public int mod(int n1, int n2) {
		return n1 % n2;
	}

	@Override
	public int sqrd(int n1, int n2) {
		int tot = n1;
		for(int i = 0; i < n2; i++) {
			tot *= n1;
		}
		return tot;
	}

	@Override
	public int abs(int n1) {
		return -n1;
	}

	@Override
	public double abs(double n1) {
		return -n1;
	}
}
