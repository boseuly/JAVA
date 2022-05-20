package exam09;

public class Triangle {
	public int length;
	public int width;
	
	
	// 삼각형 둘레
	public int round(int len) {
		int r = 3 * len;
		return r;
	}
	// 삼각형 넓이
	public double round(int len, int wid) {
		double r = (len * wid) / 2;
		return r;
	}
	
	
	
}
