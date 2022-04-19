package pro15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

class Person{
	private String name;
	private int age;
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}

public class Sample01 {

	public static void main(String[] args) {
		/*
		 * List 계열 컬렉션 - ArrayList
		 * 
		 */
		//List<E> aList = new ArrayList<E>;		// <E>는 제네릭타입임,E 또는 K가 들어가는 게 보통이다. 객체 타입을 넣어주면 됨
		// List 컬렉션을 사용하기 전에는 어떤 타입을 넣어줄지 결정해야 한다.
		List<Integer> aList = new ArrayList<Integer>();
		aList.add(100);
		aList.add(200);
		aList.add(300);
		System.out.println(aList);	

		// 내가 원하는 위치에 데이터 추가 가능
		aList.add(2, 400);				
		System.out.println(aList);
		
		List<Integer> bList = new ArrayList<Integer>();
		bList.add(500); bList.add(600); bList.add(700);
		
		// aList에 bList를 추가해주기
		aList.addAll(bList);		
		System.out.println(aList);

	
		// 수정해보기 (기존 객체 빼고, 새로운 객체 추가)	// 사용자에게 수정 내용 확인을 시켜주고 수정하겠냐고 물은 뒤 안 하겠다고 하면 다시 
		Integer i1 = aList.set(2, 350); 		// 원래대로 복원시켜놓을 수 있다.
		System.out.println(i1 + "/" + aList);
		i1 = aList.set(3, 450);
		System.out.println(i1 + "/" + aList);
		
		// 검색하기
		// 있는지 없는지 확인
		boolean result1 = aList.contains(Integer.valueOf(350));	// aList에 350이 포함되어 있는지 확인, contains가 objects객체를 요구하기 때문에 Wrapper 클래스 사용
		System.out.println(result1);
		
		// 어디에 있는지 확인
		int result2 = aList.indexOf(Integer.valueOf(300));		// index가 -1이 나오면 없다는 의미
		System.out.println(result2);
		
		// 값 추출하기
		System.out.println(aList.get(0));
		System.out.println(aList.get(1));
		System.out.println(aList.get(2));
		
		// 몇 개의 데이터가 저장되어 있는지 확인
		System.out.println(aList.size());
		
		for(int i = 0; i < aList.size(); i++) {		// 일반 배열은 출력을 하기 위해 
			System.out.println(aList.get(i));
		}
		
		// 컬렉션 안에 데이터가 있는지 확인
		result1 = aList.isEmpty();
		System.out.println(result1);
		
		// 컬렉션 다 비우기
		bList.clear();
		
		// 다시 확인
		result1 = bList.isEmpty(); 
		System.out.println(result1);
		
		// 해당 위치 값 지우기
		i1 = aList.remove(0);			// 삭제가 된 객체가 반환된다. 
		System.out.println(i1 + " / " + aList);	// 지금은 제네릭 타입이 Integer이기 때문에 반환타입이 Integer라고 나온다. remove의 반환타입은 <E>이기 때문에
		// 임시로 삭제했다가 나중에 필요할 때 가지고 올 수 있다.
		
		// Iterator 사용
		System.out.println("<<<< Iterator 사용 >>>>");
		Iterator<Integer> iter = aList.iterator();	// Iterator 반복자
		while(iter.hasNext()){
			Integer i2 = iter.next();
			System.out.println(i2);
		}
		
		System.out.println("<<< for each 문 >>>");
		for(Integer i3: aList) {		// Integer 타입이 담겨있는 aList의 데이터를 하나씩 빼서 i3에 담는다. 
			System.out.println(i3);
		}
		
		// 끝에 있던 게 앞으로, 앞에 있던 게 끝으로 순서 바꾸는 거
		System.out.println("리버스 전 : " + aList);
		Collections.reverse(aList);
		System.out.println("리버스 후 : " + aList);
		
		
		// 오름차순으로 정렬
		System.out.println("오름차순 정렬 전 : " + aList);
		Collections.sort(aList);
		System.out.println("오름차순 정렬 후 : " + aList);
		
		// 내림차순으로 정렬 방법1) 오름차순 정렬 후 리버스
		// 내림차순으로 정렬 방법2) sort를 이용한 정렬, 객체를 이용한 정렬을 할 때
		Collections.sort(aList, new Comparator<Integer>(){
			
			@Override
			public int compare(Integer i1, Integer i2) {	// 객체라고 치면, 사람이라는 클래스 안에 이름, 나이, 성별이 존재 
				if(i1 > i2) {								// 이름이 같다면 나이로, 나이마저 같다면 성별로 그 사람을 정렬한다.
					return -1;
				}else if(i1 == i2) {
					return 0;
				}
				return 1;
			}
		});
		List<Person> pList = new ArrayList<Person>();
		pList.add(new Person("홍길동", 23));
		pList.add(new Person("김철수", 25));
		pList.add(new Person("김철수", 21));
		
		Person p1 = pList.get(0);
		Person p2 = pList.get(1);
		Person p3 = pList.get(2);
		
		System.out.println(p1.getName().compareTo(p2.getName()));	// 홍길동의 유니코드값 - 김철수 유니코드 값 -> 해당 문자가 더 앞에 있는 것이다.
		System.out.println(p2.getName().compareTo(p1.getName()));
		System.out.println(p2.getName().compareTo(p3.getName()));
		
		Collections.sort(pList, new Comparator<Person>() {
			
			@Override
			public int compare(Person p1, Person p2) {
				if(p1.getName().compareTo(p2.getName()) > 0){
					return 1;
				}else if(p1.getName().compareTo(p2.getName()) < 0) {
					return -1;
				}else {
					if(p1.getAge() > p2.getAge()) {
						return 1;
					}else if(p1.getAge() < p2.getAge()) {
						return -1;
					}
				}
				return 0;
			}
		});
		for(Person p : pList) {
			System.out.println(p.getName() + " | " + p.getAge());
		}
	}

}
