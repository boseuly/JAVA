package exam02;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Sample02 {
	public static void main(String[] args) {
	/*
	 * 	로또 번호 생성
	 * 	- 1~45까지의 랜덤 번호를 생성하여 리스트에 담는다.
	 * 	- 중복된 값이 없어야 한다.
	 * 	- 총 6개의 정수 값이 리스트에 담길 수 있게 한다.
	 * 	- 마지막 출력할 때에는 오름차순으로 정렬하여 출력한다.
	 */
	
		Random rand = new Random();
		Set<Integer> lotto = new HashSet<Integer>();
		// 반복문으로 리스트에 값을 넣어준다.
		for(int i = 0; i < 6;) {
			int r = rand.nextInt(45) + 1;		// 랜덤값 만들어주고 
			if(lotto.add(r)) { 	// 여기서 바로 lotto에 r값을 추가 하고, 만약 추가가 가능하다면 i++
				i++;			// Set은 중복이 불가능하다는 특징이 있기 때문에 이게 가능하다.(ArrayList는 로직이 다름)
			}
		}
		System.out.println(lotto);
		// 정렬이 뒤죽박죽인 건 어쩔 수 없다.
		
	}	
}
