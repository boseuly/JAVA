package exam09;

public class Circle {
	private double radius;
	private double area;
	private double round;
	
	public final static double PI = 3.14;
	
	public Circle() {}
	
	public Circle(double radius) {
		setRadius(radius);
	}
	
	// setter
	public void setRadius(double radius) {
		this.radius = radius;
		 _area();			// 이게 있어야지 (setter를 통해 들어온) 매개변수 값을 메소드에 전달해서 
		 _round();			// 계산을 한 뒤 그 결과 값을 getter로 가져올 수 있는 거다.
	}
	
	// getter
	public double getRadius() {
		return radius;
	}
	
	// getter
	public double getArea() {
		return area;
	}
	
	// getter
	public double getRound() {
		return round;
	}
	
	private void _area() {
		this.area = radius * radius * PI;
	}
	
	// 원 둘레 -> 2πr
	private void _round() {
		this.round = 2 * PI * radius;
	}
}
