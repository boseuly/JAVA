package exam02;

import java.util.Objects;

public class Person {
	private String name;
	private char gender;
	private int age;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {		//이 형식을 자식도 사용할 수 있음
		return getName() + ", "+ getAge() + ", "+ getGender();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(age, gender, name);
	}
	
	@Override
	// 자바에서 기본적으로 제공하는 equals는 객체를 비교해주는 거고 우리가 Overriding한 건 그 안에 내용을 비교하고자 하는 것
	public boolean equals(Object obj) {	//Object obj -> Person 객체라고 생각하면 됨
		// 동일 객체인지를 참조값으로 비교하는 것이 아닌 객체의 멤버 변수 값으로 
		// 비교 하고자 할 때 활용
		Person p = (Person)obj;
		if(this.getName().equals(p.getName() )){	// 외부에서 받은 Person객체를 다운 캐스팅하고 그것의 name이랑 지금 Person의 name이랑 비교
			if(this.getAge() == p.getAge()) {		// 서로 이름과 나이가 같은지를 비교
				if(this.getGender() == p.getGender()) {		// 서로 이름과 나이, 성별이 같은지를 비교
					return true;
				}
			}
		}
		return false;
	}
}
