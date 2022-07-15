package locs.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import locs.model.LocsDAO;
import locs.model.LocsDTO;

public class LocsService {
private LocsDAO dao;
	
	public LocsService() {
		dao = new LocsDAO();
	}

	public List<LocsDTO> getAll() {
		List<LocsDTO> datas = dao.searchAll();
		return datas;
	}
	
	public LocsDTO getId(String id) {
		return _getId(Integer.parseInt(id));
	}
	
	public LocsDTO getId(int id) {
		return _getId(id);
	}
	
	public LocsDTO getId(LocsDTO locsDto) {
		return _getId(locsDto.getLocId());
	}
	
	private LocsDTO _getId(int id) {
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
		if(checkCtyId) {
			data.setCtyId("-1");
			ctyIdOk = false;
		}
		
		// 만약 ctyId와 locId 둘다 제약조건에 성립한다면 해당 데이터를 추가해야 한다.
		if(locIdOk && ctyIdOk) {
			dao.addLocs(data);
			dao.close();
			return data;
		}else {
			// 만약 하나라도 성립하지 않는다면 세션에 저장하고 내보내야 한다
			session.setAttribute("data", data); // 세션에 저장
			dao.close();
			return data;
		}
	}
}
