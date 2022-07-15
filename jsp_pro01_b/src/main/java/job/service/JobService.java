package job.service;

import java.util.List;

import job.model.JobDAO;
import job.model.JobDTO;

public class JobService {
	private JobDAO dao;
	
	public List<JobDTO> getAll() {
		dao = new JobDAO();
		List<JobDTO> datas = dao.selectAll();
		dao.close();
				
		return datas;
	}
	// 직급 부서 목록을 만들기 위한 클래스
	
}
