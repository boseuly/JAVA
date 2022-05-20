package exam04;

public class Page01 {
	private int pageNumber;
	private int limitPageNumber;		//한계치
	
	public Page01(int limitPageNumber) {
		this.limitPageNumber = limitPageNumber;
	}
	
	public int getPageNumber() {
		return this.pageNumber;
	}
	
	
	public void nextPage() {
		this.nextPage(1);
	}
	//여러장씩
	public void nextPage(int number) {
		if(this.existsNextPage()) {
			this.pageNumber += number;
		}else {
			System.out.println("더이상 페이지를 넘길 수 없습니다.");
		}
	}
	
	
	public void prevPage() {
		this.prevPage(1);
	}
	
	public void prevPage(int number) {
		if(this.existsPrevPage()) {
			this.pageNumber -= number;
		}else {
			System.out.println("더이상 페이지를 넘길 수 없습니다.");
		}
	}
	
	//페이지 존재 유무 확인
	//1씩 증가할 때 페이지 유무 확인
	public boolean existsNextPage() {		
		return this.existsNextPage(1);
	}
	
	//여러장씩 증가할 때 페이지 유무학인
	public boolean existsNextPage(int number) {	
		if(this.pageNumber + number > this.limitPageNumber) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean existsPrevPage() {
		return this.existsPrevPage(1);
	}
	
	public boolean existsPrevPage(int number) {
		if(this.pageNumber - number < 1) {
			return false;
		}else {
			return true;
		}
	}
	
}
