package exam04;

public class Page {
	
	private int page;			// 현재 페이지	
	private int pageStart = 1;	// 시작 페이지
	private int pageEnd;		// 끝 페이지
	
	public Page(int page) {
		this(page, 100);		//끝페이지는 100으로 내가 미리 설정
	}
	
	public Page(int page, int pageEnd) {		//첫 페이지와 끝페이지를 정한다.
		this.page = page;
		this.pageEnd = pageEnd;
	}
	
	public int getPage() {
		return this.page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageEnd() {
		return this.pageEnd;
	}
	
	
	
	//1페이지씩 이동
	public void pageUp() {
		if(this.page == this.pageEnd) {
			System.out.println("더이상 이동할 페이지가 없습니다.");
		}
		this.page++;
	}
	
	public void pageDown() {
		if(this.page == this.pageStart) {
			System.out.println("더이상 이동할 페이지가 없습니다.");
		}
		this.page--;
	}
	
	
	//페이지 이동 메소드
	public int pageUp(int num) {		//num만큼 페이지 이동
		this.page += num;		//num을 더해서 page에 다시 넣어줌
		this._pageOver(); 
		return this.page;
		//num이 pageStart 보다 작아야 됨 -> 크면 더이상 이동 X
	}
	
	public int pageDown(int num) {
		this.page -= num;
		this._pageOver();
		return this.page;
		
	}
	
	//pageEnd, pageStart 한계를 초과했을 때 "더이상 페이지 넘길 수 없습니다."
	private void _pageOver() {
		if(this.page > this.pageEnd) {
			this.page = this.pageEnd;
			System.out.println("더이상 이동할 페이지가 없습니다.");
		}
		
		if(this.page < this.pageStart) {		// 이전 페이지로 이동했는데 더이상 이전 페이지가 없을 때
			this.page = this.pageStart;
			System.out.println("더이상 이동할 페이지가 없습니다.");
		}
	}
	
}
