package exam03;

import java.util.Random;

public class Student01 {
	private String name;	//이름
	private int age;		//나이
	private int classLevel;		//학년
	private int classRoom;		//반
	private int classNum;		//번호
	
	
	public Student01(String name) {	//신입생 등록하는 경우에 사용
//		this.name = name;
//		this.age = 14;
//		this.classLevel = 1;
		this(name, 14, 1);		//코드가 계속 중복되니까 호출을 통해 간단하게 만듦
	}
	
	public Student01(String name, int age) {	//신입생인데 빠른 년생일 수도 있응까
//		this.name = name;
//		this.age = age;
//		this.classLevel = -1;
		this(name, age, 1);		//아래 매개변수 3개인 생성자를 호출 -> 간단해짐
	}
	
	public Student01(String name, int age, int classLevel) {
		this.name = name;
		this.age = age;
		this.classLevel = classLevel;
	}
	
	public String getName() {
		return this.name;
	}
	public int getAge() {
		return this.age;
	}
	public int getClassLevel() {
		return this.classLevel;
	}
	
	public void nextYear() {
		this.age++;
		
		if(this.classLevel == 3) {
			this._graduate();		//따로 메소드 만들어서 호출해줌
		}else {
			this.classLevel++;
			this.classRoom = this._assignClassRoom();	//_assignClassRoom메소드에서 return값 받음
			
		}
	}
	
	private int _assignClassRoom() {	//반 배정 메소드
		Random rand = new Random();
		return rand.nextInt(9)+1;
	}
	
	
	private void _graduate() {			//졸업반
		this.classLevel = -1;
		this.classRoom = -1;
		this.classNum = -1;
	}
	//문자열 출력, 모든 객체가 가지고 있는 기능 -> toString
	public String toString() {	//.get -> 이런식으로 정보를 꺼내 쓰는 게 귀찮다면 toString 사용
//		return this.name + "(" + this.age + "): " + this.classLevel + " 학년 " + this.classRoom + "반";
		return String.format("%s(%d): %d 학년 %d 반", this.name, this.age, this.classLevel, this.classRoom);	
	}
	
}
