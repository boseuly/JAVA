package locs.service;

import java.util.List;

import javax.servlet.http.HttpSession;

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

	public LocsDTO addLocs(HttpSession session, String locId, String stAddr, String postal, String city, String state, String ctyId) {
		// 만약 추가가 실패하게 되면 해당 정보들을 세션에 저장해야 한다.
		dao = new LocsDAO();
		LocsDTO data = new LocsDTO();	// 추가가 실패하면 여기에 값을 넣어야 한다. 
		data.setLocId(Integer.parseInt(locId)); //만약 추가에 실패한다면 어떤 게 문제인지 알려주기 위해
		data.setStAddr(stAddr);					// 해당 객체에 숫자를 넣어야 한다. locId가 문제라면 setLocId(-1) 이런 식으로 
		data.setPostal(postal);					// 단, 성공하면 그대로 return 시키면 된다.
		data.setCity(city);
		data.setState(state);
		data.setCtyId(ctyId);
		
		// 해당 데이터를 추가하기 전에 locId가 이미 존재하는지, ctyId가 존재하는지 확인해야 한다.
		LocsDTO checkLocId  = dao.searchId(Integer.parseInt(locId));	// 해당 데이터를 전달한다.
		boolean checkCtyId = dao.checkCtyId(ctyId);	// 제약조건 확인
		boolean locIdOk = true; boolean ctyIdOk = true; 
		
		// 만약 locId가 이미 존재한다면 
		if(checkLocId != null) {
			data.setLocId(-1);
			locIdOk = false;
		}
		// 만약 ctyId가 존재하지 않는다면
		if(!checkCtyId) {
			data.setCtyId("-1");
			ctyIdOk = false;
		}
		// 굳이 세션에 저장할 필요가 없음
		// 만약 ctyId와 locId 둘다 제약조건에 성립한다면 해당 데이터를 추가해야 한다.
		if(locIdOk && ctyIdOk) {
			dao.addLocs(data);
			dao.commit();
			dao.close();
			return data;
		}else {
			// 만약 하나라도 성립하지 않는다면 세션에 저장하고 내보내야 한다
			dao.rollback();
			dao.close();
			return data;
		}
		
	}

	public boolean delLocs(String locId, String stAddr, String postal, String city, String state, String ctyId) {
		// 원래는 locId가 숫자형식인지 matches를 통해서 확인 해야 하고, 해당 아이디가 존재하며 나머지 정보들과 매칭이 되는지 확인을 해야 한다.
		// 하지만 여기서는 이미 조회된 정보리스트에서 삭제 페이지로 바로 넘어가는 거기 때문에 그럴 필요가 없다고 생각해서 확인 작업을 넘어간 거다 
		dao = new LocsDAO();
		LocsDTO data = new LocsDTO();
		data.setLocId(Integer.parseInt(locId));
		data.setStAddr(stAddr);
		data.setPostal(postal);
		data.setCity(city);
		data.setState(state);
		data.setCtyId(ctyId);
		
		boolean result = dao.delLocs(data);
		if(result) {	// 삭제 성공
			dao.commit();
			dao.close();
			return true;
		}
		dao.rollback();
		dao.close();
		return false;
	}

	public LocsDTO modLocs(String locId, String stAddr, String postal, String city, String state, String ctyId) {
		// ctyId가 제약조건을 위배하는지 확인해야 한다.
		dao = new LocsDAO();
		LocsDTO data = new LocsDTO();
		
		data.setLocId(locId);
		data.setState(stAddr);
		data.setPostal(postal);
		data.setCity(city);
		data.setState(state);
		data.setCtyId(ctyId);
		
		boolean checkCtyId = dao.checkCtyId(ctyId);
		
		if(!checkCtyId) { // 제약조건 위배
			data.setCtyId("-1");
			dao.close();
			return data;	// 제약조건 확인 할 게 1개밖에 없기 때문에 위배되면 바로 return 시킴
		}else { // 제약조건에 위배되지 않는다면 
			boolean modLocs = dao.modLoc(data);
			if(!modLocs) {	// 알 수 없는 오류로 수정이 안 됨
				data.setLocId("-1"); // 알 수 없는 오류
				dao.rollback();
				dao.close();
			}else {
				dao.commit();
				dao.close();
			}
		}
		return data;
	}
	
}
