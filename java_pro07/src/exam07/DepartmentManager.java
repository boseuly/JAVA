package exam07;

public class DepartmentManager extends Employee implements TeamManager{
	// 과장
	private boolean teamManager;	// 팀장직을 가지고 있는지 true, false로 구분
	
	
	public boolean isTeamManager() {
		return teamManager;
	}
	
	@Override
	public void teamPayBonus() {
		// 팀장 직책 수행시 연봉의 10% 보너스
		if(isTeamManager()) {
			System.out.println("팀장직 수행 보너스 : " + getSalary() / 0.1 / 12 * 10000 + " 원");
		}
	}
	
	
	@Override
	public void bonus(int month) {
		switch(month) {
		case 4: case 8: case 12:
			super.bonus(month);
		}
	}
	
	public DepartmentManager(String name, int age) {
		super(name, age);
		this.setSalary(4000);
	}

	
}
