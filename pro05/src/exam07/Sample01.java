package exam07;

public class Sample01 {

	public static void main(String[] args) {
		Initialize init = new Initialize();
		System.out.println("JVM 초기값 : " + init.num1 + ", " + Initialize.name1);
		System.out.println("명시적 초기값 : " + init.num2 + ", " + Initialize.name2);
		System.out.println("명시적 초기값 : " + init.num3 + ", " + Initialize.name3);
	
		char c = 23;
		Constructor con = new Constructor(c);		//char 출력
		
		Constructor con1 = new Constructor(10);
		Constructor con2 = new Constructor(10,20);
		Constructor con3 = new Constructor(10,20,30);
		Constructor con4 = new Constructor(10,20,30,40);
	
		BookStore book1 = new BookStore();
		BookStore book2 = new BookStore("Java Programming");
		BookStore book3 = new BookStore("Java", 12344);
		
		
	}

}
