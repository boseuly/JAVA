package locs.service;

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
}
