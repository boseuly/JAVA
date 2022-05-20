package exam10;

public class Circle {
	private final static double PI = 3.14;
	private int radius = 1;
	
	public Circle() {}
	
	public void getAreaOfCricle() {
		double area = radius * radius * PI;
		System.out.println(area);
	}

	public void getSizeOfCircle() {
		double round = 2* PI * radius;
		System.out.println(round);
	}
	
	public void incrementRadius() {
		radius += 1;
	}
	
}
