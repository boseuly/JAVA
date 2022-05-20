package exam06;

import java.util.Objects;

public class Customer {

	private String name;
	private int age;
	private char gender;
	
	// getter & setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public void buy(String productName, int price) {
		System.out.printf("%s 상품을 %,d 원에 구입하였습니다.\n", productName, price);
		
	}
	
	// 갱신
	public Customer renewal() {
		System.out.println("고객 정보를 갱신합니다.");
		return this;	// 나 자신을 다시 반환 시켜주는 것
	}
	
	// 환불(영수증X)
	public void refund() {
		System.out.println("영수증 없이 환불 조치를 하였습니다.");
	}
	// 환불(영수증O)
	public void refund(String receipt) {
		System.out.println("영수증을 확인하였습니다.");
		System.out.printf("%s 물품을 환불 조치 합니다.\n", receipt);
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(age, gender, name);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return age == other.age && gender == other.gender && Objects.equals(name, other.name);
	}
	
	
	@Override
	public String toString() {
		return "Customer [name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}
	
	
	
	
}
