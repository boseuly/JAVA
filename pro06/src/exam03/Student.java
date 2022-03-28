package exam03;

import java.util.Random;

public class Student {
	private String name;	//이름
	private int age;		//나이
	private int year;		//학년
	private int group;		//반
	private int number;		//번호
	
	//생성자
	public Student() {}
	
	public Student(String name, int age, int year, int group, int number) {
		this.name = name;
		this.age = age;
		this.year = year;
		this.group = group;
		this.number = number;
	}
	
	
	//getter, setter 생성
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	// 메소드 생성(나이, 학년 +1, 반 랜덤으로 정해주기) 
	public void nextYear() {	
		if(year == 3) {		//졸업생
			this.year = -1;
			this.group = -1;
			this.number = -1;
		}else {
			this.age++;
			this.year++;
			Random rand = new Random();
			this.group = rand.nextInt(9)+1;
		}
		
	}
	//개인정보 출력
	public void information() {
		System.out.println("이름 : " + this.name);
		System.out.println("나이 : " + this.age);
		System.out.println("학년 : " + this.year);
		System.out.println("반 : " + this.group);
		System.out.println("학번 : " + this.number);
	}
	
	
	
}
