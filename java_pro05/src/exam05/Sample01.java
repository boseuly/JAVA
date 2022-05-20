package exam05;

import java.util.Random;

public class Sample01 {

	public static void main(String[] args) {
		Circle c1 = new Circle();	
		Circle c2 = new Circle();	// Circle클래스로 2개의 인스턴스 생성
		
		// c1 인스턴스
		c1.radius = 1.5;							//반지름 1.5
		c1.diamiter	= c1.radius * 2;				//지름 
		c1.area = c1.radius * c1.radius * Circle.PI;	//원의 넓이
		
		// c2 인스턴스
		c2.radius = 3.5;							//반지름 3.5
		c2.diamiter = c2.radius * 2;				//지름 
		c2.area = c2.radius * c2.radius * Circle.PI;  	//원의 넓이
		
		System.out.printf("c1 인스턴스 정보 : 반지름(%.2f), 지름(%.2f), 넓이(%.2f)\n", c1.radius,c1.diamiter,c1.area);
		System.out.printf("c2 인스턴스 정보 : 반지름(%.2f), 지름(%.2f), 넓이(%.2f)\n", c2.radius,c2.diamiter,c2.area);
		
		
		Rectangle r1 = new Rectangle();
		Rectangle r2 = new Rectangle();	//Rectangle클래스로 2개의 인스턴스 생성
		
		Random random = new Random();
		
		//너비
		r1.width = c1.diamiter;		
		r2.width = c2.diamiter;
		
		//높이
		r1.height = random.nextInt(7)+3;
		r2.height = random.nextInt(7)+3;
		
		//직사각형 넓이
		r1.area = r1.width * r1.height;
		r2.area = r2.width * r2.height;
		
		
		System.out.printf("r1 인스턴스 정보 : 너비(%.2f), 높이(%.2f), 넓이(%.2f)\n", r1.width,r1.height,r1.area);
		System.out.printf("r2 인스턴스 정보 : 너비(%.2f), 높이(%.2f), 넓이(%.2f)\n", r2.width,r2.height,r2.area);
	}

}
