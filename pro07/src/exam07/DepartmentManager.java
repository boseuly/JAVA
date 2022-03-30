package exam07;

public class DepartmentManager extends AssistantManager{
	// 과장
	
	private boolean teaManager;	// 팀장직을 가지고 있는지 true, false로 구분
	
	public DepartmentManager(String name, int age) {
		super(name, age);
		this.setSalary(4000);
	}
	
	@Override
	public void bonus(int month) {
		switch(month) {
		case 4: case 8: case 12:
			super.bonus(month);
		}
	}
	


	
}
