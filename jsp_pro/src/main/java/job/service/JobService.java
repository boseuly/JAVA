package job.service;

import java.util.List;

import job.model.JobDAO;
import job.model.JobDTO;

public class JobService {
	private JobDAO dao;
	
	public List<JobDTO> getAll() {
		dao = new JobDAO();
		List<JobDTO> datas = dao.getAll();
		dao.close();
		
		return datas;
	}
	
}
