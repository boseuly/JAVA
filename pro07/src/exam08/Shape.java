package exam08;

public abstract class Shape {
	private double width;
	private double height;
	
	
	// 추상 메서드가 있으면 추상 클래스로 만들어줘야 한다.
	// 추상 메서드
	public abstract double getArea();
	
	
	// 추상 메서드
	public abstract double getRount();

	
	// 메서드
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	
	
}
