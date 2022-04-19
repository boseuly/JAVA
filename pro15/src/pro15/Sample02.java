package pro15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
		List<Integer> lotto = new ArrayList<Integer>();
		// 반복문으로 리스트에 값을 넣어준다.
		for(int i = 0; i < 6;) {
			int r = rand.nextInt(45) + 1;				// 랜덤값 만들어주고
			if(!lotto.contains(Integer.valueOf(r))) { 	// 해당 랜덤값이 lotto의 값에 포함 되어 있는지, 만약 없다면
				lotto.add(r);
				i++;
			}
		}
		Collections.sort(lotto);
		System.out.println(lotto);
		
	}	
}
