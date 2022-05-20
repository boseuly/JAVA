package exam08;

public class Main {
	public static void main(String[] args) {
		/*
		 * 추상 클래스 
		 * - 몸체 없는 메서드(추상 메서드)를 포함하는 클래스, abstract 키워드를 사용해서 만든다.
		 * - 미완성된 클래스(추상클래스)를 만들어서 이를 상속하는 자식 클래스들이 미완성된
		 * 	 부분(추상 메서드)을 완성할 수 있게 강제시키기 위한 용도로 사용
		 * - 추상 클래스를 상속하는 모든 자식들은 미완성된 부분을 완성해야 하기 때문에 일관성이 높아진다.
		 * - 추상 클래스를 사용해서 객체를 직접 생성할 수는 없다. 단, 참조 타입으로는 만들어지는 건 가능 
		 * 	 ex) Shape가 추상 클래스라면 
		 * 		 Shape s = new Shape();    ->   X
		 */
		// 이렇게 객체를 직접 만들 수는 없음
		// Shape s = new Shape();
		
		//단, 이렇게 참조변수를 만들어서 자식 객체를 저장하는 건 가능하다.
		Shape s1 = new Circle();
		Shape s2 = new Square();
		Shape s3 = new Triangle();
		
		
		
		
	}
}
