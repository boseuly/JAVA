package exam04.teacher;

public class Main {

	public static void main(String[] args) {
		NormalCustomer c1 = new NormalCustomer();
		PremiumCustomer p1 = new PremiumCustomer();
		c1.buy("샤넬", 4000000);
		p1.buy("샤넬", 4000000);
		p1.buy("제네시스gv90", 80000000);
		p1.buy("루이비통", 2000000);
	}
	

}
