package locs.service;

import java.util.ArrayList;
import java.util.List;

import locs.model.LocsDAO;
import locs.model.LocsDTO;

public class LocsService {
	private LocsDAO dao = null;
	
	public List<LocsDTO> getAll() {
		dao = new LocsDAO();
		List<LocsDTO> datas = dao.getAll();
		
		dao.close(); // session 썼으면 닫기
		return datas;
	}
	public LocsDTO getId(String locsId) {
		dao = new LocsDAO();
		int id = Integer.parseInt(locsId);
		LocsDTO data = dao.getId(id);
		
		dao.close();
		return data;
	}
	public int locsAdd(String locId, String stAddr, String postal, String city, String state, String ctyId) {
		dao = new LocsDAO();
		LocsDTO data = dao.getId(Integer.parseInt(locId)); // 기본키
		
		if(data == null) {	// 만약 해당 아이디가 없다면
			// 제약조건 검사
			boolean checkCtyId = dao.checkCtyId(ctyId); // 해당 아이디가 있어야 한다.
			if(checkCtyId) {
				LocsDTO locsData = new LocsDTO();
				locsData.setLocId(Integer.parseInt(locId));
				locsData.setStAddr(stAddr);
				locsData.setPostal(postal);
				locsData.setCity(city);
				locsData.setState(state);
				locsData.setCtyId(ctyId);
				
				boolean result = dao.locsAdd(locsData);
				if(result) {
					dao.commit();
					dao.close();
					return 1;
				}
			}else {
				dao.close();
				return -2;	// 외래키 위배
			}
		}else {				// 해당 아이디가 있다면
			dao.close();
			return -1;		// 기본키 위배 
		}
		dao.rollback();
		dao.close();
		return 0;
	}
	public List<LocsDTO> getPage(int page, int pageCount) {
		dao = new LocsDAO();
		int pageNumber = page;	
		int start, end;
		
		start = (pageNumber -1) * pageCount;
		end = pageCount;
		
		List<LocsDTO> datas = dao.searchPage(start, end);
		dao.close();
		return datas;
	}
	public List<Integer> getpageList(int pageCount) {
		dao = new LocsDAO();
		List<Integer> pageList = new ArrayList<Integer>();
		int totalRow = dao.totalRow();	// 전체 행수
		
		for(int i =0; i <= (totalRow - 1) / pageCount ; i++) {
			pageList.add(i + 1);	// 전체행수 -1 을 하는 이유 : 인덱스와 길이의 차이를 없애기 위해
		}
		dao.close();
		return pageList;
	}
}
