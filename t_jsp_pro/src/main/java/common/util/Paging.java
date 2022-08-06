package common.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Paging {
	private int offset;
	private int limit;
	private List<Integer> pages;	// 총 몇 페이지인지
	private List<Object> pageDatas;	// 한 페이지에 들어갈 객체 -> Obejct인 이유는
	private int currentPage; 		// 현재 페이지
	private int nextPage;			// 다음 페이지 
	private int prevPage;			// 이전 페이지
	
	public Paging() {}
	// 매개변수 있는 생성자를 통해서 초기화를 해줘야 한다. 
	public Paging(int page, int limit, int totalRows) {
		this.offset = limit * (page - 1);
		this.limit = limit;
		this.currentPage = page;
		this.nextPage = page + 1; 
		this.prevPage = page - 1; 
		this.pages = new ArrayList<Integer>(); // 모든 페이지 리스트
		page = 1; 
		for(int i = 0; i <= totalRows; i += limit) {
			this.pages.add(page++); 
		}
	}
	public int getOffset() {
		return offset;
	}
	public int getLimit() {
		return limit;
	}
	// 원하는 만큼의 페이지 리스트만 뽑아오기
	public List<Integer> getPages(int start, int end){
		start = start < 1 ? 1 : start;
		end = end > pages.size() ? pages.size() : end; // pageList : 리스트에서 특정 구간을 잘라서 가져온다.
		return pages.subList(start - 1, end); // list는 0부터 시작하지만 그 안에 담긴 페이지 번호는 1부터 시작하기 때문에 내가 가져오고 싶은 페이지번호 -1을 해줘야 한다.
	}
	public List<Object> getPageDatas(){
		return pageDatas;
	}
	
	public void setPageDatas(Iterator<Object> iter) {
		this.pageDatas = new ArrayList<Object>();
		while(iter.hasNext()) {
			pageDatas.add(iter.next());
		}
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public int getNextPage() {
		return nextPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	
	public boolean hasNextPage() {
		return this.nextPage > this.pages.size() / limit; // size가 10이면 실제 페이지는 11페이지
		// return this.pages.contains(this.nextPage); // pages 객체에 다음 페이지가 존재하는지 확인 
	}
	
	public boolean hasPrevPage() {
		return this.prevPage <= 0 ? false : true;
		// return this.pages.contains(this.prevPage);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
