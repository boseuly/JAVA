package exam09;

public class Rectangle {
	// 사각형 넓이 -> 가로 * 높이
	public double area(int wid) {
		return wid* wid;
	
	}
	
	public double area(int wid, double height) {
		return wid* wid;
	}
	
	
	// 사각형 둘레 -> 네변의 길이 합
	public double round(int height, int wid) {
		double result = (2 * height) + (2 * wid);
		return result;
	}
}
