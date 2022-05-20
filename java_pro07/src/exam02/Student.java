package exam02;

public class Student extends Person {
	private int classLevel;	// 학년
	private int classRoom;	// 반
	
	
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
	
	@Override
	public String toString() {
		return super.toString() + ", " 
				+ getClassLevel() + ", " 
				+ getClassRoom();
			  // 부모의 toString을 호출하고 이어서 자신의 것도 정의	
	}
	
}
