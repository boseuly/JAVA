package exam02;

public class Teacher extends Person {
	private int classLevel;	//학년
	private int classRoom;	//반
	private String subject; //담당과목
	
	
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", " 
				+ getClassLevel() + ", " 
				+ getClassRoom() + ", " 
				+ getSubject();
	}
	
	
	
}
