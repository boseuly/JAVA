package pro07;

public class Parent {
	private int number = 1;
	
	public Parent() {}
	
	public Parent(int number) {
		this.number = number;
	}
	
	public void setNumber(int number) {
		System.out.println("부모의 setNumber 메서드 동작 시작");
		this.number = number;
		System.out.println("부모의 setNumber 메서드 동작 끝");
	}
	public int getNumber() {
		return this.number;
	}
}
