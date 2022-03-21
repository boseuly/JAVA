package exam10;

public class Product {
	private String pName = "키보드";
	private int price = 250000;
	private String brand = "X오X드";
	
	//기본생성자 -> 객체생성할 수 있도록 해줌
	public Product() {}
	
	
	public void information() {
		System.out.println(pName);
		System.out.println(price);
		System.out.println(brand);
	}
}
