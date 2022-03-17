package exam04;

public class Sample01 {

	public static void main(String[] args) {
		StaticTest st1 = new StaticTest();
		StaticTest st2 = new StaticTest();
		StaticTest st3 = new StaticTest();
		
		// static에 만들어진 share를 st1, st2, st3이 모두 참조하고 있어서 st1, st2, st3 모두 값이 바뀐다. 
		// static인데 인스턴스 변수를 사용하면 이렇게 됨
		st1.share = 20;
		System.out.println(st1.share + " | " +st2.share + " | " +st3.share);
		
		st1.share = 30;
		System.out.println(st1.share + " | " +st2.share + " | " +st3.share);
		
		st1.share = 40;
		System.out.println(st1.share + " | " +st2.share + " | " +st3.share);
		
		// static 예약어를 사용한 변수는 클래스명으로 사용하는 것을 권장한다. 인스턴스변수로 하면 값이 같이 바뀌어 버리기 때문에
		StaticTest.share = 20;
		System.out.println(StaticTest.share + " | " +StaticTest.share + " | " +StaticTest.share);
		
		StaticTest.share = 30;
		System.out.println(StaticTest.share + " | " +StaticTest.share + " | " +StaticTest.share);
		
		StaticTest.share = 40;
		System.out.println(StaticTest.share + " | " +StaticTest.share + " | " +StaticTest.share);
	
		
		//Final 예약어
		FinalTest ft1 = new FinalTest();
		
		System.out.println(ft1.CONSTANT);
		//ft1.CONSTANT = 20; -> final 예약어는 한 번 초기화 하면 수정 불가능
		
		
		// 상수이면서 final 예약어 사용시
		FinalStaticTest fst1 = new FinalStaticTest();
		FinalStaticTest fst2 = new FinalStaticTest();
		
		System.out.println(fst1.CONSTSHARE + " | " + fst2.CONSTSHARE + " | ");
		System.out.println(FinalStaticTest.CONSTSHARE);
		//FinalStaticTest.CONSTSHARE = 30; -> final 예약어라서 불가능
		
	}

}
