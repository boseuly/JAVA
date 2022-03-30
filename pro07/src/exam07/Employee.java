package exam07;

public class Employee {
	// 모든 직원 해당(부모클래스)
	private String name;	// 이름
	private int age;		// 나이
	private char gender;	// 성별
	private int salary;		// 연봉
	
	
	public Employee(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	// 급여
	public void payMonth() {
		System.out.println("급여를 지급하였습니다.");
		System.out.printf("지급액 : %.0f\n", (double)getSalary() / 12);
	}
	// 보너스
	public void bonus(int month) {
		System.out.println("보너스를 지급하였습니다.");
		System.out.printf("지급액 : %.0f\n", getSalary() * 0.25);
	}
	
	
	
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
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
	
}
