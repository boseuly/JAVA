package exam02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class Sample01 {

	public static void main(String[] args) {
		/*
		 * Set 컬렉션
		 */
		Set<Integer> aSet = new HashSet<Integer>();
		
		boolean result;
		result = aSet.add(100);			// add()는 반환형이 boolean형이어서 확인 하기 위해서 boolean에 넣어봄 
		System.out.println(result + " " + aSet);
		result = aSet.add(200); 	
		System.out.println(result + " " + aSet);
		result = aSet.add(200);
		System.out.println(result + " " + aSet);
		
		Set<Integer> bSet = new HashSet<Integer>();
		bSet.add(300); bSet.add(400);
		
		result = aSet.addAll(bSet);
		System.out.println(result + " " + aSet);
		
		List<Integer> cList = new ArrayList<Integer>();
		cList.add(500);	cList.add(600);	cList.add(100);	cList.add(100);	cList.add(100);
		
		// 하나의 데이터라도 추가가 되면 true가 나오고 모든 데이터가 추가 되지 않으면 false가 나온다.
		result = aSet.addAll(cList);	
		System.out.println(result + " " + aSet);
		
		result = aSet.contains(Integer.valueOf(100));
		System.out.println(result);
		result = aSet.contains(Integer.valueOf(150));
		System.out.println(result);
		
		System.out.println("------비워져있는지 확인------");
		result = aSet.isEmpty();
		System.out.println("안에 내용이 비워져있나요 ? : "+ result);
		
		System.out.println("--------내용 전부 지우기--------");
		bSet.clear();
		result = bSet.isEmpty();
		System.out.println("안에 내용이 비워져있나요 ? : " + result);
		
		System.out.println("------크기------");
		int len = aSet.size();
		System.out.println(len + " " + aSet);
		
		System.out.println("-------해당 내용 지우기------");
		result = aSet.remove(Integer.valueOf(100));
		System.out.println(result + " " + aSet);
		
		result = aSet.remove(Integer.valueOf(150));
		System.out.println(result + " " + aSet);
		
		// Iterator이 있으면 반복문을 사용할 수 있음
		// 방법1)
		Iterator<Integer> iter = aSet.iterator();
		while(iter.hasNext()) {
			Integer i1 = iter.next();
			System.out.print(i1 + "\t");
		}
		System.out.println();
		System.out.println("==========");
		
		// 방법2)
		for(Integer i1: aSet) {
			System.out.print(i1+"\t");
		}
		System.out.println();
		
		
		// Set계열 -> List계열로 변경
		List<Integer> aList = new ArrayList<Integer>(aSet);	
		System.out.println(aList);
		
		// List계열 -> Set계열로 변경 
		Set<Integer> cSet = new HashSet<Integer>(aList);
		System.out.println(cSet);
		
		// List, Set ->  배열로 변경
		Integer[] iArr = aList.toArray(new Integer[aList.size()]);	// ArrayList인 aList를 배열로 변경 (Integer 타입이니까)
		System.out.println(Arrays.toString(iArr));					// 이건 배열로 변경된 거임(그래서 Arrays.toString사용해서 출력)
		iArr = cSet.toArray(new Integer[cSet.size()]);				// HashSet인 cset을 배열로 변경
	
		
		// ListIterator의 previous() 사용해보기 
//		ListIterator<Integer> iter1 = aList.listIterator(aList.size()-1);
//		while(iter1.previous() != null) {
//			Integer i1 = iter.next();
//			System.out.print(i1 + "\t");
//		}
//		System.out.println();
//		System.out.println("==========");
//	
	
	
	
	}
	

}
