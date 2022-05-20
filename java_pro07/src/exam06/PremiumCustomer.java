package exam06;

public class PremiumCustomer extends Customer {
	private double discount;	// 할인금액
	private int priceTotal;		// 누적구입액
	
	
	
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(int priceTotal) {
		this.priceTotal = priceTotal;
	}

	@Override
	public void buy(String productName, int price) {
		double p = _calcDiscount(price);
		priceTotal += price;
		System.out.printf("%s 상품을 %.2f 할인율 적용하여 %,.1f 원에 구입하였습니다.\n", productName, discount, p);
		
	}
	@Override
	public Customer renewal() {		// 여기가 Customer인 이유 -> return 타입이 자유로움 (일반, 프리미엄 고객 둘다 return 가능)
		// 누적사용액이 500만원 미만이면 일반고객
		// 누적사용액이 500만원 이상이면 프리미엄고객
		Customer c = this;				// 업캐스팅
		if(priceTotal < 5000000) {
			c = new NormalCustomer();	
			c.setName(getName());		// 업캐스팅된 부모타입에 넣는 것
			c.setGender(getGender());
			c.setAge(getGender());
			System.out.println("누적사용액이 기준에 미달하여 일반 고객으로 강등 조치되었습니다.");
		}
		return c;
	}
	
	// 할인율 계산 -> 내야할 금액 도출
	private double _calcDiscount(int price) {	
		if(priceTotal >= 10000000) {
			discount = 0.1;
			return price *(1 - 0.1);
		}else if(priceTotal >= 3000000) {
			discount = 0.05;
			return price *(1 - 0.05);
		}else if(priceTotal >= 1000000) {
			discount = 0.02;
			return price* (1 - 0.02);
		}else {
			return price;
		}
	}
	
	
	
	
	
	
	
	
}
