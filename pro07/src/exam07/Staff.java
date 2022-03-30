package exam07;

public class Staff extends Employee {
	// 사원
	
	public Staff(String name, int age) {
		super(name, age);
		this.setSalary(2800);  // 이렇게 부모 setter를 통해 임금 설정 가능
	}
	
	@Override
	public void bonus(int month) {
		switch(month) {
		case 6: case 12:
			super.bonus(month);
		}
	}



}

