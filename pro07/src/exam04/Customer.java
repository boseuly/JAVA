package exam04;

public class Customer {

	/*
	 * 고객 
	 * - 일반 고객 : 이름, 나이, 성별 
	 * 
	 * - 프리미엄 고객 : 이름, 나이, 성별, 할인율, 누적구입액
	 * 
	 * 프리미엄 고객의 경우 누적구입액에 따라 할인율을 차등적으로 부여하는 기능이 있다.
	 * - 누적구입액이 1,000,000 이상의 경우 할인율 2%
	 * - 누적구입액이 3,000,000 이상의 경우 할인율 5%
	 * - 누적구입액이 10,000,000 이상의 경우 할인율 10%
	 */
	private String name;	// 이름
	private int age;		// 나이
	private char gender;	// 성별
	
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
	@Override
	public String toString() {
		return "Customer [name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}
	
	
	
}
