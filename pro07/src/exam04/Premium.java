package exam04;

public class Premium extends Customer {
	private double disRate;		// 할인율
	private int discount;		// 할인 이후 금액
	private int additions;		// 누적구입액
	
	
	//누적구입액 구하는 메소드
	public int sum(int addition) {
		this.additions += addition;	//누적구입액 + 이번에 구입한 금액
		return this.additions;
	}
	
	
	// 누적 구입액에 따른 할인율 -> 할인 이후 금액 구하기
	public void discountPrice(int price) {	// 상품 금액 price
		boolean bool = false;
		if(this.additions >= 1000000) {
			this.disRate = 0.02;
			this.discount =  (int)(price- (price * this.disRate));
			bool = true;
		}else if(this.additions >= 3000000) {
			this.disRate = 0.05;
			this.discount = (int)(price- (price * this.disRate));
			bool = true;
		}else if(this.additions >= 10000000) {
			this.disRate = 0.1;
			this.discount = (int)(price- (price * this.disRate));
			bool = true;
		}else {
			System.out.println("일반고객으로 분류되어 할인율을 0% 입니다.");
			bool = false;
		}
		if(bool) {
			int discountAmost = (price - this.discount);
			System.out.println(discountAmost + "할인 적용되어 결제하실 금액은 " + discount + "원 입니다.");
		}
		
	}
	// 할인 금액 구하는 메소드
//	public void discountAmost(int price) {	// 할인 금액
//		System.out.println("총 할인 금액은 " + (price - this.discount) + " 만 원입니다."); 
//	}
	

	public double getDisRate() {
		return disRate;
	}

	public void setDisRate(double disRate) {
		this.disRate = disRate;
	}

	public int getAdditions() {
		return additions;
	}

	public void setAdditions(int additions) {
		this.additions = additions;
	}

	@Override
	public String toString() {
		return super.toString() + "Premium [additions=" + additions
				 + ", discount=" + discount + "]";
	}
	
	
	
}
