package dept.service;

import java.util.List;

import dept.model.LocsDAO;
import dept.model.LocsDTO;

public class LocsService {
	
	private LocsDAO dao;
	public LocsService() {
		dao = new LocsDAO();
	}
	
	public List<LocsDTO> getAll() {
		List<LocsDTO> datas = dao.searchAll();
		return datas;
	}
	public LocsDTO getId(String locsId) {
		return _getId(Integer.parseInt(locsId));
	}
	public LocsDTO getId(int locsId) {
		return _getId(locsId);
	}
	public LocsDTO getId(LocsDTO locsData) {
		return _getId(locsData.getLocalID());
	}
	private LocsDTO _getId(int id) {
		LocsDTO data = dao.searchId(id);
		return data;
	}
	
}
