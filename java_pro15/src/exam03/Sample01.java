package exam03;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Sample01 {

	public static void main(String[] args) {
		/*
		 * Map 계열 컬렉션
		 * Map<key, value>
		 * <> 이건 다이아몬드 연산자라고 함
		 */
		
		Map<String, Integer> aMap = new HashMap<String, Integer>();
		aMap.put("a", 100);	aMap.put("b",200);	aMap.put("c",300);
		aMap.put("d",100);	aMap.put("b",400); aMap.put("e",400);
		aMap.put("f",400); aMap.put("g",400); aMap.put("h",400);
		System.out.println(aMap);		// b는 키가 동일하기 때문에 마지막에 추가된 값으로 저장된다.
		
		// 값이 중복되면 이미 저장된 값이 출력되고 새로 들어온 값이 저장된다. 
		Integer result = aMap.put("r", 900);		// 이전의 값(value)을 반환한다.
		System.out.println(result + " " + aMap);	// result는 이전의 값
		
//		result = aMap.put("a", 200);
//		System.out.println(result + " " + aMap);
		
		// 상황 1. 특정 키에 값을 넣었는데 이미 값이 존재해서 null이 나오는 게 아니라 이전값이 나온다.
		// 	  	  -> 이때 취소하고 싶다면 어떻게 해야할까? (즉, 값 추가를 취소하고 싶을 때)
		// 방법)
		result = aMap.put("h", 900);	// 이전의 값(value)을 반환한다.
		if(result != null) {					// 특정 키에 값을 넣었는데 이전의 값이 나온다면 (= 이미 값이 존재했다면)
			aMap.put("h", result);				// 특정 키에 다시 이전의 값을 넣겠다.
		}
		System.out.println(result + " " + aMap);	// result는 이전의 값
		
		// 상황 2. 값(vlaue)추가를 하기 전에 미리 중복이 되는지를 확인한다.
		if(!aMap.containsValue(Integer.valueOf(900))) {
			aMap.put("j", 900);
		}
		System.out.println(aMap);
		
		
		boolean result2;
		// 값이 존재하는지 확인해주는 메소드
		result2 = aMap.containsValue(Integer.valueOf(900));
		System.out.println(result2);
		
		// 키가 존재하는지 확인해주는 메소드
		result2 = aMap.containsKey("a");
		System.out.println(result2);
		
		// Map의 Entry라는 건 <>안에 key와 value를 갖는 걸 의미
		// Set<Entry<String, Integer>> : 키는 String으로, value는 Integer로 갖는 Map entry객체들을 Set으로 담아둔 상황 → 이건 Set처럼 사용해주면 된다.
		// 방법1) 
		Set<Entry<String, Integer>> entrys = aMap.entrySet();		// aMap.entrySet으로 Set객체 만들어서 Set객체에 넣고
		Iterator<Entry<String, Integer>> iter = entrys.iterator();	// set객체를 가지고 Iterator 사용해주고
		while(iter.hasNext()) {	
			Entry<String, Integer> entry = iter.next();				// 값을 하나씩 뽑아서 저장
			System.out.println(entry.getKey() + " : " + entry.getValue());	// 출력
		}
		
		// 방법2) -> 이게 수월함
		for(Entry<String, Integer> entry : aMap.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		// 키만 출력
		for(String s: aMap.keySet()) {
			System.out.print(s + "\t");
		}
		System.out.println();
		
		// value만 출력
		for(Integer i:aMap.values()) {
			System.out.print(i + "\t");
		}
		System.out.println();
		
		
		result = aMap.get("a");
		System.out.println(result);
		
		result = aMap.get("k");
		System.out.println(result);
		
		result = aMap.getOrDefault("k", Integer.valueOf(220));	// 만약 "k"라는 키가 없으면 특정(디폴트)값을 넣겠다. ()괄호 안 두번째 값이 디폴드값임 
		System.out.println(result);
		
		// "a" 키 삭제
		result = aMap.remove("a");
		System.out.println(result + " " + aMap);
		
		aMap.remove("b", Integer.valueOf(300));		// 키: b, 값: 300 매치 안 되니까 삭제 안됨
		System.out.println(aMap);
		aMap.remove("b" , Integer.valueOf(400));	// 키: b, 값: 400인 거 있으니까 삭제  
		System.out.println(aMap);
		
		
		
		
		
	}

}
