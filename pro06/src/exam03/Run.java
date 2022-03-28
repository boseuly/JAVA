package exam03;

public class Run {

	public static void main(String[] args) {
		/*
		 * - 학생 객체를 활용하기 위한 클래스를 만들어 본다.
		 * - 학생 객체를 사용하여 이름, 나이, 학년, 반, 번호 정보를 다룬다.
		 * - 학생의 나이와 학년은 nextYear()메소드를 사용하여 +1 증가
		 *   시킬 수 있게 한다.
		 * - nextYear()로 학년이 바뀌면 반 또한 랜덤하게 변경되도록 한다.
		 *   반은 1~9반 사이에서 임의로 바뀌게 한다.
		 * - 3학년에서 nextYear()가 되면, 졸업생으로 만들기 위해 
		 *   기존의 학년, 반, 번호는 전부 -1로 설정하게 한다.  
		 * 
		 */
		
//		Student stu = new Student("이보슬",24, 4, 18, 20180031);
//		stu.information();
//		
		Student01 std = new Student01("홍길이", 14,1);
		
		std.nextYear();
		System.out.println(std.getName());
		System.out.println(std.getAge());
		System.out.println(std.getClassLevel());
//		.get -> 이런식으로 정보를 꺼내 쓰는 게 귀찮다면  toString 사용
		System.out.println(std);	//toString 한 다음 이렇게 출력
		
		
	}

}
