package exam06;

public class NormalCustomer extends Customer {
	
	private int count;
	private int coupon;
	
	@Override
	public void buy(String productName, int price) {
		if(coupon >= 1) {		// 쿠폰이 1개 이상 있으면 
			_useCoupon(price);	// 쿠폰을 사용해줘라
		}
		if(count == 10) {		// 구매횟수가 10번이라면
			_addCoupon();		// 쿠폰을 발급해라
			count = 0;
		}
		super.buy(productName, price);
		count++;
	}
	
	// 쿠폰 발급
	private void _addCoupon() {
		System.out.println("쿠폰을 발급합니다.");
		System.out.println("발급된 쿠폰은 다음 상품 구매시 자동 사용됩니다.");
		coupon++;
	}
	
	// 쿠폰 사용
	private int _useCoupon(int price) {
		System.out.println("쿠폰을 사용하여 5% 할인이 적용된 금액으로 계산됩니다.");
		price = (int)(price * (1 - 0.05));
		coupon--;
		return price;
	}
	
	public void refund(int price) {
		count--;					// 구매한 횟수를 하나 빼준다.
		System.out.printf("%d 원이 환불되었습니다.\n", price);
	}
	
	
	
}
