package exam06;

public class EmployeeCustomer extends Customer {
	/*
	 * - 직원 고객이 물품을 구입할 때 직원 할인가를 적용하여 구입할 수 있도록 한다.
	 * 	 (직원 할인가는 10%이다.)
	 * 
	 * 모든 고객은 구입한 물품에 대해 환불을 받을 수 있다.
	 * 단, 일반 고객은 구입 영수증이 있어야 환불 받을 수 있다.
	 * 
	 * 고객 정보는 1년에 한 번씩 갱신을 수행하며, 프리미엄 고객의 경우 갱신할 시점의
	 * 누적구입액이 최소 5,000,000원 미만인 경우 일반 고객으로 강등된다.
	 * (100번 반복 횟수에서 30, 60, 90번 반복에 해당하는 경우 모든 고객의 정보를
	 * 	갱신하도록 하여 1년에 한번씩 갱신을 수행한 것으로 간주한다.)
	 */
	
//	private double discount = 0.1;
//	private int price;
//	
//	// buy 메소드 오버라이딩
//	@Override
//	public void buy(String productName, int price) {
//		int r = _calDiscount(price);
//		System.out.printf("10% 할인되어 %s 상품을 %d 원에 구입하였습니다.\n", productName, r);
//		
//	}
//	// 직원 할인 (10%)
//	private int _calDiscount(int price) {
//		return (int)(price * (1 - 0.1));
//	}
//	
	
	@Override
	public void buy(String productName, int price) {
		price = (int)(price * (1 - 0.1));
		super.buy(productName, price);
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
