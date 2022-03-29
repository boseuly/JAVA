package exam06;

import java.util.Arrays;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		/*
		 *  다형성 / 업캐스팅 / 다운캐스팅 활용
		 *  - 일반 고객 5명을 생성한다.(이름, 나이, 성별 초기화 하지 않아도 됨)
		 *  - 일반 고객 5명을 배열에 넣어 랜덤한 고객, 랜덤한 가격으로 물품을 구입하게 만든다.
		 *  - 물품 구입액이 1,000,000 원을 초과하면 프리미엄 고객으로 전환시켜서 물품 구입에 
		 *    할인율을 적용할 수 있도록 만든다.
		 *  - 고객이 물품을 구입하는 작업을 반복문을 통해 반복하게 만드며, 100번 반복하게 만든다. 
		 *  
		 *  일반 고객 -> 프리미엄 고객 전환
		 *  NormalCustomer n1 = new NormalCustomer("홍길동");
		 *  PremiumCustomer p1 = new PremiumCustomer();
		 *  p1.setName(n1.getName);
		 *  
		 */
		
		Random random = new Random();
		Customer[] cArr = new Customer[5];
		
		for(int i = 0; i < cArr.length; i++) {
			cArr[i] = new NormalCustomer();
		}
		// 물품 구입 횟수
		int count[] = new int[cArr.length];	
		
		int unit = 10000;
		for(int i = 0; i < 100; i++) {				// 구매하는 행위를 100번 반복
			int idx = random.nextInt(cArr.length);	// 반복할 때마다 회원도 랜덤으로 결정됨
			int price = (random.nextInt(199000) + unit) / unit * unit;	// 가격도 랜덤으로 결정
			if(cArr[idx] instanceof NormalCustomer) {			// NomalCustomer(일반고객) 클래스라면
				NormalCustomer c = (NormalCustomer) cArr[idx];	// 형변환 시켜주고 NomalCustomer 클래스 레퍼런스인 c에 넣어라
				
				c.buy("XXXX", price);
				
				if(price > 1000000) {		// price 가 1,000,000 원이 초과했다면 프리미엄 고객으로 만들어줘야 함
					PremiumCustomer p = new PremiumCustomer();
					p.setName(c.getName());
					p.setAge(c.getAge());
					p.setGender(c.getGender());
					p.setPriceTotal(price);
					cArr[idx] = p;			// 외부에서 만들어놓은 배열에 p를 넣어줘야 한다. cArr 배열로 고객을 관리하기 때문에
					System.out.println("축하합니다. 구입액이 1,000,000 원을 초과하여 프리미엄 등급으로 상승하였습니다.");
					System.out.println("앞으로 상품 구입 누적액에 따른 할인율이 적용됩니다.");
				}
			}else {			// 프리미엄 고객이라면
				PremiumCustomer p = (PremiumCustomer) cArr[idx];
				p.buy("XXXX", price);
				
			}
		}
		
		Customer[] cArr1 = new Customer[3];
		
		for(int i = 0; i < cArr1.length; i++) {
			cArr1[i] = new NormalCustomer();
		}
		
		cArr1[0].setName("이보슬");
		cArr1[1].setName("이다슬");
		cArr1[2].setName("이해슬");
		
		// 여기서 이제 NormalCustomer의 환불 메소드를 사용하기 위해 다운캐스팅을 한다. 
		NormalCustomer n1 = (NormalCustomer)cArr[0];
		n1.buy("샤넬", 1000000);
		n1.refund(1000000);
		
//		<내가 푼 거 -> 막힘,,,>
//		Customer[] c1 = new Customer[5];	
//		
//		// 업캐스팅
//		c1[0] = new NormalCustomer("나천재");	
//		c1[1] = new NormalCustomer("나욜로");
//		c1[2] = new NormalCustomer("나최고");
//		c1[3] = new NormalCustomer("코딩천재");
//		c1[4] = new NormalCustomer("아신나");
//		PremiumCustomer[] p1 = new PremiumCustomer[0];
//		
//		// 랜덤한 가격을 만들어준다.
//		Random rand = new Random();
//		int r = rand.nextInt(10000000) + 1;
//		int price = 0;
//		
//		// 랜덤한 상품 인덱스
//		String[] str =new String[] {"샤넬", "루이비통", "메종", "생로랑", "구찌"};
//		int n = rand.nextInt(5);		// 0~4까지의 숫자 중에서 랜덤값이 나오면 그 숫자가 n에 저장됨
//		String prodectName = "";
//		
//		
//		int total = 0;	// 총 누적구입액
//		int count = 0;	// p1 인덱스
//		
//		// 구입하는 작업 100번 반복
//		for(int i = 0; i < 100; i++) {
//			
//			if(total < 1000000) {		// 일반 회원인 경우
//				prodectName = str[n];
//				price = r;
//				c1[i].buy(prodectName, price);
//				total += price;
//			}else {
//				prodectName = str[n];
//				price = r;
//				p1 = Arrays.copyOf(p1, p1.length + 1);
//				p1[count] = (PremiumCustomer)(c1[i]);
//				count++;
//				p1[count].buy(prodectName, price);
//				total += price;
//			}
//			
//		}
//		
		
		
		
		
		
		
	}
	

}
