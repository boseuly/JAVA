package exam05;

public class Main {

	public static void main(String[] args) {
		// 객체를 사용하는 기본 방법
		Person p1 = new Person("홍길이");
		System.out.println(p1.getName());
		
		Person p2 = new Person("이보슬");
		System.out.println(p2.getName());
		
		Person p3 = new Person("박일도");
		System.out.println(p3.getName());
		
		//배열을 이용한 객체 사용법
		//얘네(객체) 다 일일이 쓰기 귀찮으니까 배열로 만들려고 하는 거임
		Person[] pArr = new Person[4];
		pArr[0] = p1;
		pArr[1] = p2;
		pArr[2] = p3;
		pArr[3] = new Person("최가은");
		
		for(int i = 0 ; i < pArr.length; i++) {
			System.out.println("pArr[" + i + "] -> " + pArr[i].getName());
			
		}
		
		PersonList pList = new PersonList(pArr);	//위에서 만든 pArr
		System.out.println(pList.get(0));
		
		// 굳이 Person객체 따로 안 만들어도 PersonList클래스를 
		// 통해서 Person객체를 쉽게 만들 수 있다.
		pList.add("이해슬");
		pList.add("이다슬");
		
		for(int i =0; i<pList.length(); i++) {
			Person data = pList.get(i);
			System.out.println(data.getName());	//얘는 Person클래스의 메소드
		}
		
		pList.update("홍길이", "홍길영");
		pList.update(1, "박재은");
		
		pList.remove("박일도");
//		pList.remove(2);
		
		System.out.println("--------구분선--------");
		for(int i =0; i<pList.length(); i++) {
			Person data = pList.get(i);			//굳이 이렇게 안 빼도 되지만 보기 편하라고 한 거임
			System.out.println(data.getName());
		}
		
		
	}
}
