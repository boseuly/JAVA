package job.model;

import java.util.List;

import common.util.AbstractDAO;

public class JobDAO extends AbstractDAO{

	public List<JobDTO> getAll() {
		List<JobDTO> datas = session.selectList("jobMapper.selectJobAll");
		return datas;
	}

}
