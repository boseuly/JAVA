package exam04;

public class Run {

	public static void main(String[] args) {
		/*
		 * Page 클래스 만들기
		 * 
		 * Page 클래스는 책의 페이지를 넘기는 것과 비슷한 개념의 클래스로 만들어 사용할 것이다.
		 * 해당 객체를 사용하여 책의 페이지를 넘기듯 페이지 번호를 +1 하거나 -1 하는 기능이 필요하다.
		 * 해당 객체를 사용하여 책의 페이지를 넘길 때 다음 또는 이전 페이지로 넘길 수 있는지 확인하기 위한 기능이 필요하다.
		 * 책의 페이지를 넘길 때 2장 또는 그 이상으로도 넘길 수 있는 기능이 필요하다.
		 * 책의 페이지를 넘기는 것에는 한계가 존재하며 이 한계를 넘는 요청이 발생하는 경우 "더 이상 페이지를 넘길 수 없습니다."
		 * 메시지를 출력하도록 한다.
		 * 위의 사항을 만족할 수 있도록 필요한 모든 생성자, 멤버 변수, 메소드 등을 만들고 클래스 다이어그램으로도 만들어 보기
		 * 
		 */
		
		Page p = new Page(3);
		System.out.println(p.getPage());		//현재 페이지 출력
		System.out.println(p.getPageEnd());
		
		p.pageUp();
		System.out.println(p.getPage());
		
		p.pageDown();
		System.out.println(p.getPage());
		
		p.pageDown(2);
		System.out.println(p.getPage());
		
		p.pageUp(95);
		System.out.println(p.getPage());
		
		p.pageUp(10);
		System.out.println(p.getPage());
		
		p.pageDown(101);
		System.out.println(p.getPage());
	}

}
