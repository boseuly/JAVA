package locs.service;

import java.util.List;

import locs.model.LocsDAO;
import locs.model.LocsDTO;

public class LocsService {
private LocsDAO dao;

	public List<LocsDTO> getAll() {
		dao = new LocsDAO();
		List<LocsDTO> datas = dao.searchAll();
		return datas;
	}
	
	public LocsDTO getId(String id) {
		dao = new LocsDAO();
		return _getId(Integer.parseInt(id));
	}
	
	public LocsDTO getId(int id) {
		dao = new LocsDAO();
		return _getId(id);
	}
	
	public LocsDTO getId(LocsDTO locsDto) {
		return _getId(locsDto.getLocId());
	}
	
	private LocsDTO _getId(int id) {
		dao = new LocsDAO();
		LocsDTO data = dao.searchId(id);
		return data;
	}

	// 지역 추가하기 
	public int datasAdd(LocsDTO data) {
		dao = new LocsDAO();
		// 기본키 검사 (location_id 이미 존재하는지)
		if (data != null) {
			if(dao.searchId(data.getLocId()) != null) {	// 만약 해당 아이디가 이미 존재한다면 
				dao.rollback();
				dao.close();
				return -2;	// 기본키 위배
			}else if(!dao.checkCtyId(data.getCtyId())) {// 외래키 검사 -> countryId 가 CONTRIES테이블에 존재하는지
				dao.rollback();
				dao.close();
				return -1;	// 외래키 위배
			}else {			// 제약조건을 다 충족한다면 추가해주기
				int result = dao.addLocs(data);
				dao.commit();
				dao.close();
				return result;
			}
		}else {
			dao.close();
			dao.rollback();
			return 0;
		}
	}
}
