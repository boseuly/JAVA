package exam07;

public class Director extends AssistantManager{
	// 부장
	private boolean teaManager;		// 팀장직을 가지고 있는지 true, false로 구분
	private boolean headManeger;	// 본부장직을 가지고 있는지 true, false로 구분
	
	
	public Director(String name, int age) {
		super(name, age);
		this.setSalary(8000);
	}

	@Override
	public void bonus(int month) {
		switch(month) {
		case 1:
			super.bonus(month);
		}
	}
	
	
}
