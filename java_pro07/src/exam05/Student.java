package exam05;

public class Student extends Person {
	private int classLevel;
	private int classRoom;
	
	public Student(String name, int age) {
		super(name, age);
	}
	
	public int getClassLevel() {
		return classLevel;
	}
	public void setClassLevel(int classLevel) {
		this.classLevel = classLevel;
	}
	public int getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(int classRoom) {
		this.classRoom = classRoom;
	}
	
	
	
	
	
	
}
