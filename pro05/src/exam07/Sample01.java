package exam07;

public class Sample01 {

	public static void main(String[] args) {
		Initialize init = new Initialize();
		System.out.println("JVM 초기값 : " + init.num1 + ", " + Initialize.name1);
		System.out.println("명시적 초기값 : " + init.num2 + ", " + Initialize.name2);
		System.out.println("명시적 초기값 : " + init.num3 + ", " + Initialize.name3);
	
		
		char c = 23;
		Constructor con = new Constructor(c);		//기본값인 int가 아닌 char,double, long 등으로 값 넣으려면 객체따로 생성해서 객체를 ()안에 넣기
		
		Constructor con1 = new Constructor(10);
		Constructor con2 = new Constructor(10,20);
		Constructor con3 = new Constructor(10,20,30);
		Constructor con4 = new Constructor(10,20,30,40);
	
		BookStore book1 = new BookStore();			//기본생성자 사용해보기
		BookStore book2 = new BookStore("Java Programming");	//매개변수 있는 생성자에 초기값 넣기
		BookStore book3 = new BookStore("Java", 12344);
		
		book1.bookName = "Java Programming";
		System.out.println(book1);
		
	}

}
