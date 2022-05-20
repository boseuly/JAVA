package exam02;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Person{
	private String name;
	private int age;
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
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
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(age, name);
	}
	
	// 객체뿐만 아니라 그 안에 포함되어 있는 값(여기선 name,age)을 가지고 중복을 확인하고 싶다면 Source - hashCode() and equals() - name, age 선택 
	// 주소는 다 다르지만 값이 중복된다면 그 값을 비교해서 중복되는 값을 뺀다. (ex : 김철수, 김철수 -> 김철수) 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return age == other.age && Objects.equals(name, other.name);
	}
	
	// 이름만 가지고 중복을 걸러내고 싶다면 Source - hashCode() and equals() - name만 선택
	
	
	
}




public class Sample04 {

	public static void main(String[] args) {
		Set<Person> pSet = new HashSet<Person>();
		pSet.add(new Person("홍길동" , 23));
		pSet.add(new Person("김철수", 25));	// 객체뿐만 아니라 그 안에 포함되어 있는 값을 가지고 중복을 확인하고 싶다면
		pSet.add(new Person("김철수", 25));
		
		System.out.println(pSet); // toString이용해서 출력하기 (물론 클래스에 오버라이딩은 되어있어야 됨 -> 안 해주면 그냥 객체주소만 출력됨)
	}
	
	

}
