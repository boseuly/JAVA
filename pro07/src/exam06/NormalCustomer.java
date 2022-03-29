package exam06;

import java.util.Scanner;

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
	
	// 구입 영수증(구입 영수증이 너무 어려움)
	
	
	Scanner sc = new Scanner(System.in);
	
	// 환불(영수증 확인 필요한데 로직을 어떻게 짜야 할지 모르겠음)
	public void refund(int price) {
		System.out.print("환불을 진행하시겠습니까? (Y/N) : ");
		String str = sc.next();
		if(str.equals("Y")) {
			if(this.coupon >= 1) {	// 쿠폰이 1개 이상이고
				if(this.count == 0) {
					this.coupon--;	// 쿠폰은 하나 없애주고
					this.count = 9;	// 구매 횟수도 하나 줄여준다
					System.out.printf("%d 원이 환불되었습니다.\n", price);
				}
			}else {						// 만약 쿠폰이 0개이고
				if(this.count >= 1) {	// 구매 횟수가 1번 이상이라면
					this.count--;		// 구매한 횟수를 하나 빼준다.
					System.out.printf("%d 원이 환불되었습니다.\n", price);
				}else {
					System.out.println("구매내역이 없습니다.");	
				}
			}
		}else {
			System.out.println("환불 진행이 취소되었습니다.");
		}
		
		
		
	}
	
	
	
}
