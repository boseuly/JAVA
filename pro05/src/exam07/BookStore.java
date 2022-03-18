package exam07;

public class BookStore {
	// 도서 대여 프로그램
	public String bookName;	//책이름
	public String publisher;	//출판사
	public String writer; 		//저자
	public int n;				//권 수
	public int tentDays;		//대여기간
	public int category;		//분류
	public int rentPrice;		//대여번호
	public int codeNumber;
	
	
	//매개변수 없는 생성자
	public BookStore() {
		
	}
	
	public BookStore(String bookName) {
		this.bookName = bookName;
		System.out.println(bookName);
	}
	public BookStore(String bookName, int codeNumber) {
		this(bookName);
		this.codeNumber = codeNumber;
	}
	public BookStore(String bookName, int codeNumber, String writer) {
		this(bookName, codeNumber);
		this.writer = writer;
	}
	public BookStore(String bookName, int codeNumber, String writer, int rentPrice ) {
		this(bookName, codeNumber, writer);
		this.rentPrice = rentPrice;
		
	}
	
	
	
	
}
