package exam07;

public class AssistantManager extends Employee{
	// 대리
	private int corpCardTotal;	// 법인카드 사용누적금액
	
	public AssistantManager(String name, int age) {
		super(name, age);
		this.setSalary(3000);
	}
	
	@Override
	public void bonus(int month) {
		switch(month) {
		case 6: case 12:
			super.bonus(month);
		}
	}
	
	// 법인카드
	// 대리 이상인 직급들이 대리를 상속하면 각 클래스마다 메소드 굳이 안 써도 됨 
	public void corpCard(int amount) {
		if((getSalary() * 0.015) * 10000 > getCorpCardTotal() + amount) {	// salary가 만원 단위라서 * 10000한 거임
			System.out.printf("법인카드로 %,d원을 지출하였습니다.", amount);
			setCorpCardTotal(getCorpCardTotal() + amount);		// 그냥 corpCardTotal이라고 하면 다른 클래스에서 사용 불가 -> get사용해준다.
		}else {
			System.out.println("법인 카드 한도를 초과하였습니다.");
			System.out.printf("현재까지 사용액은 %,d원 입니다.\n", getCorpCardTotal());
			System.out.printf("한도내에서 %,.0f원 만큼만 사용할 수 있습니다.\n",(getSalary() * 0.015) * 10000 - getCorpCardTotal());
		}
	}

	public int getCorpCardTotal() {
		return corpCardTotal;
	}

	public void setCorpCardTotal(int corpCardTotal) {
		this.corpCardTotal = corpCardTotal;
	}
	
	
}
