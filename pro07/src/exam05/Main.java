package exam05;

public class Main {

	public static void main(String[] args) {
		/*
		 * 다형성
		 * - 객체지향 프로그래밍의 3대 특징(캡슐화, 상속, 다형성) 중 하나에 해당한다.
		 * - 하나의 객체가 여러 형태를 가진다는 의미
		 * - 상속을 사용하여 부모 타입으로부터 파생된 여러 타입의 자식 객체를 부모 클래스로 다룰 수 있도록 하는 기능
		 * 	 (다형성을 사용하기 위해서는 상속 단계가 필요하다.)
		 */
		
		/*
		 * 업 캐스팅
		 * - 상위 객체로 변환 하는 것
		 * - 자식 클래스가 부모 클래스로 변화하는 것 -> 부모 타입의 참조변수에 자식 타입의 참조변수를 저장할 수 있다.
		 * - 자동으로 변환이 이루어지기 때문에 별도의 캐스팅 연산자를 사용할 필요가 없다.(부모는 하나밖에 없으니까 명시할 필요 없음)
		 */
		Person s = new Student("나천재", 14);		// Person의 참조변수에 Student 클래스 객체주소 저장
		Person t = new Teacher("나욜로", 39);		// Person의 참조변수에 Teacher 클래스 객체주소 저장 
		
		Person[] p1 = new Person[3];			// 원래 배열에는 같은 타입의 객체만 저장된다.
		p1[0] = new Teacher("나욜로", 39);		// 근데 부모 레퍼런스 변수에 자식 객체 담을 수 있음
		p1[1] = new Student("나천재", 16);
		p1[2] = new Student("박수쳐", 16);
		
		
		// 부모타입이기 때문에 부모타입의 메소드만 사용할 수 있음 -> 
		for(int i = 0; i < p1.length; i++) {
			System.out.println("이름 : " + p1[i].getName());
			System.out.println("나이 : " + p1[i].getAge());
			System.out.println("====================");
		}
		
		/*
		 * 다운 캐스팅
		 * - 하위 객체로 변환 하는 것 
		 * - 업 캐스팅으로 부모 타입의 참조변수에 저장한 자식 객체의 참조변수를 다시 원래의 타입으로 변환하기 위해 사용
		 * - 캐스팅 연산자를 사용하여 변환을 시켜야 한다.(자식은 여러타입이 있으니까 명시해둬야 함)
		 */
		
		// 업캐스팅을 하면 자식 클래스의 메소드는 사용할 수 없다.
		// 따라서 자식 클래스의 메소드를 사용하기 위해서는 다운 캐스팅을 해줘야 함
		Person s2 = new Student("홍길동", 16);	// 업캐스팅
		Student s3 = (Student)s2;				// 다운캐스팅
		s3.setClassLevel(1);
		s3.setClassRoom(4);
		
		Person t2 = new Teacher("김철원", 39);
		Teacher t3 = (Teacher)t2;
		t3.setClassLevel(1);
		t3.setClassRoom(4);
		t3.setSubject("국어");
		
		Person[] p2 = new Person[2];
		p2[0] = s3;		p2[1] = t3;
		
		for(int i = 0; i < p2.length; i++) {
			int level, room;
			String subject = ""; String name = "";
			
			if(p2[i] instanceof Student) {
				level = ((Student) p2[i]).getClassLevel();
				room = ((Student) p2[i]).getClassRoom();
				name = p2[i].getName();
				System.out.printf("%d 학년 %d 반 %s학생\n", level, room, name);
			}else if(p2[i] instanceof Teacher) {
				level = ((Teacher) p2[i]).getClassLevel();
				room = ((Teacher) p2[i]).getClassRoom();
				subject = ((Teacher) p2[i]).getSubject();
				System.out.printf("%d 학년 %d 반 %s 담당 선생님\n", level, room, subject);
			}
		}
		
		
	
	}

}
