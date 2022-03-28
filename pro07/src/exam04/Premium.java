package exam04;

public class Premium extends Customer {
	private double disRate;		// 할인율
	private int additions;		// 누적구입액
	
	
	//누적구입액 구하는 메소드
	public int sum(int addition) {
		this.additions += addition;	//누적구입액 + 이번에 구입한 금액
		return this.additions;
	}
	
	// 누적 구입액에 따른 할인율
	public double discountRate() {
		if(this.additions >= 10000000) {
			this.disRate = 2;
		}else if(this.additions >= 3000000) {
			this.disRate = 5;
		}else if(this.additions >= 10000000) {
			this.disRate = 10;
		}
		return this.disRate;
	}

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
		return super.toString() + "Premium [disRate=" + disRate + ", additions=" + additions + "]";
	}
	
	
	
}
