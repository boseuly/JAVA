package exam04.teacher;

public class PremiumCustomer extends Customer {
	private double discount;	// 할인금액
	private int priceTotal;		// 누적구입액
	
	@Override
	public void buy(String productName, int price) {
		double p = _calcDiscount(price);
		priceTotal += price;
		System.out.printf("%s 상품을 %.2f 할인율 적용하여 %,.1f 원에 구입하였습니다.\n", productName, discount, p);
		
	}
	
	
	
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
