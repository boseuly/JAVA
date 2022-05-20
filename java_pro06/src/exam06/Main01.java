package exam06;

public class Main01 {

	public static void main(String[] args) {
		//생성자를 이용하는 여러 방법
		GradeList01 gList1 = new GradeList01();
		GradeList01 gList2 = new GradeList01(4);	//배열의 공간을 처음에 정해주는 메소드를 만들것인지
//		GradeList01 gList = new GradeList01("국어", "영어", "수학", "과학");	//과목을 다 정해놓을 것인지
		
//		Grade[] gArr = new Grade[2];
//		GradeList gList = new GradeList(gArr);
		
		GradeList01 gList3 = new GradeList01("국어", "영어", "수학", "과학");
		for(int i = 0; i < gList3.length(); i++) {
			System.out.println(gList3.getName(i) + ":" + gList3.getScore(i));
		}
		
		Grade01[] gArr = new Grade01[2];	
		gArr[0] = new Grade01("과학");
		gArr[1] = new Grade01("영어");
		System.out.println("-----------------");
		GradeList01 gList4 = new GradeList01(gArr);	//외부에서 값을 바꾸면 GradeList의 값 또한 바뀐다.
		gArr[0].setName("국어");
		gList4.get(1).setName("수학");
		gList4.add("역사");
		gList4.add(new Grade01("과학", 89.6));
		
		
		for(int i = 0; i < gList4.length(); i++) {
			System.out.println(gList4.getName(i) + ":" + gList4.getScore(i));
		}
		
		
		
		
		
	}

}
