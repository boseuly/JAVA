package locs.service;

import java.util.List;

import dept.model.DeptDAO;
import dept.model.DeptDTO;
import locs.model.LocsDAO;
import locs.model.LocsDTO;

public class LocsService {
private LocsDAO dao;

	public List<LocsDTO> getAll() {
		dao = new LocsDAO();
		List<LocsDTO> datas = dao.searchAll();
		return datas;
	}
	
	// 페이징 구현 중 미완
	public List<LocsDTO> getPage(int pageNumber) {
		int start, end;
		start = (pageNumber - 1) * 10;
		
		dao = new LocsDAO();
		return null;
	}
	
	
	public LocsDTO getId(String id) {
		dao = new LocsDAO();
		return _getId(Integer.parseInt(id)); //String으로 전달해도 형변환하니까 돼야 하는 거 아닌가...
	}	// 왜 자꾸 여기서 오류가 나는지 모르겠음..ㅠ
	
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

	// 지역 추가하기 1
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
	
	// 지역 추가하기 2 : 에러 한꺼번에 보내기
	public LocsDTO addLocs(String locsId, String stAddr, String postal, String city, String state, String ctyId) {
		dao = new LocsDAO();
		
		LocsDTO locsDto = null;
		if(locsId.matches("\\d+")) {	// 만약 locsId가 숫자형태가 맞다면
			boolean isValid = true;
			locsDto = new LocsDTO();
			locsDto.setLocId(Integer.parseInt(locsId));
			locsDto.setStAddr(stAddr);
			locsDto.setPostal(postal);
			locsDto.setCity(city);
			locsDto.setState(state);
			locsDto.setCtyId(ctyId);	// ctyId 외래키 충족한다면
			
			if(dao.searchId(locsDto.getLocId()) != null) {
				locsDto.setLocId(-1);
				isValid = false;
			}
			if(!dao.checkCtyId(locsDto.getStAddr())) {
				locsDto.setStAddr("-1");
				isValid = false;
			}
			if(isValid) {	// 만약 제약조건에 어긋하는 게 없다면
				int isSaved = dao.addLocs(locsDto);
				if(isSaved == 1) {
					dao.commit();
					dao.close();
					return locsDto;
				}else {// 저장에 실패했다면
					dao.rollback();
					dao.close();
					return null;
				}
			}
		}
		// 만약제약조건에 어긋난다면
		dao.rollback();	 
		dao.close();
		return locsDto;
	}
	
	// 수정(update)
	public int locsModify(String locId, String stAddr, String postal, String city, String state, String ctyId) {
		// country_id는 외래키 검사를 해야 한다.
		boolean fkCheck = dao.checkCtyId(ctyId);	// true여야 통과
		if(!fkCheck) {		// false -> 해당 country_id는 존재하지 않음
			dao.rollback();	
			dao.close();
			return -1;
		}
		// 만약 제약조건 확인 통과 했다면
		LocsDTO data = new LocsDTO();
		data.setLocId(Integer.parseInt(locId));
		data.setStAddr(stAddr);
		data.setPostal(postal);
		data.setCity(city);
		data.setState(state);
		data.setCtyId(ctyId);
		
		boolean modResult = dao.update(data); 
		if(modResult) {
			dao.commit();
			dao.close();
			return 1;
		}
		dao.rollback();
		dao.close();
		return 0;
	}

	public boolean deleteLocs(String id) {
		int locId = Integer.parseInt(id);

		
		// 해당 locId가 존재하는지 확인
		if(dao.searchId(locId) != null) {// 해당 아이디가 존재한다면
			boolean result = dao.deleteLocs(locId);
			if(result) {
				dao.commit();
				dao.close();
				return true;
			}
		}
		// 해야 locId가 존재하지 않는다면
		return false;
	}
}
