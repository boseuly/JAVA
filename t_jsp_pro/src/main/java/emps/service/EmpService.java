package emps.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dept.model.DeptDAO;
import dept.model.DeptDTO;
import emps.model.EmpAllDTO;
import emps.model.EmpDAO;
import emps.model.EmpDTO;
import emps.model.EmpDetailDTO;
import job.model.JobDAO;
import job.model.JobDTO;

public class EmpService {

	
	public EmpDTO getId(String empId) {
		EmpDAO dao = new EmpDAO();
		EmpDTO data = dao.selectId(Integer.parseInt(empId));
		dao.close();
		return data;
	}
	public List<EmpDTO> getEmpAll() {
		EmpDAO dao = new EmpDAO();
		List<EmpDTO> datas = dao.selectAll();
		dao.close();
		return datas;
	}

	public List<EmpDTO> getEmpPage(int page, int count) {
		EmpDAO dao = new EmpDAO();
		List<EmpDTO> datas = dao.selectPage((page - 1) * count, count);
		dao.close();
		return datas;
	}

	public List<Integer> getPageList(int pageCount) {
		EmpDAO dao = new EmpDAO();
		List<Integer> pageList = new ArrayList<Integer>();
		
		int total = dao.totalRow();
		
		for(int num = 0; num <= (total - 1) / pageCount; num++) {
			pageList.add(num + 1);
		}
		
		dao.close();
		return pageList;
	}

	public EmpDetailDTO getDetail(int empId) {
		EmpDAO dao = new EmpDAO();
		EmpDetailDTO data = dao.selectDetail(empId);
		dao.close();
		return data;
	}

	// 직원 추가 -> date는 not null 제약 조건이 있어서 한꺼번에 넣어줄 수밖에 없다.
	public int addEmps(String empId, String empName, String email, String jobName, String deptName
					, String phone, String hireDate, String salary, String commission, String mngId) {
		EmpDAO dao = new EmpDAO();
		
		// empId -> employees - employee_id 기본키 확인 // 해당키가 존재하지 않는다면 사용 가능하다.
		if (dao.getId(Integer.parseInt(empId))) { // 해당 키가 존재한다면
			dao.close();
			return -1;
		}
		//job_title이 존재해야 한다.
		if(!(dao.existjob(jobName))){ // 해당 이름이 존재하지 않는다면
			dao.close();
			return -2;
		}
		if(!(dao.existDept(deptName))) { // 해당 매니저가 있는지 확인
			dao.close();
			return -3;
		}
		
		if(!(dao.existMngId(Integer.parseInt(mngId)))) {
			dao.close();
			return -4;
		}
		
		// 만약 다 아니면 추가 해준다.
		EmpAllDTO data = new EmpAllDTO();
		data.setEmpId(empId);
		data.setEmpName(empName);
		data.setEmail(email);
		data.setJobName(jobName);
		data.setDeptName(deptName); // select form where 을 사용하니까 이것만 보내도 됨
		
		data.setPhone(phone);
		LocalDate todayLocalDate = LocalDate.now( ZoneId.of( "Korea/Seoul" ) );
		java.sql.Date sqlDate = java.sql.Date.valueOf( todayLocalDate );
		data.setHireDate(sqlDate);
		data.setSalary(Integer.parseInt(salary));
		data.setCommission(Integer.parseInt(commission));
		data.setMngId(Integer.parseInt(mngId));
		
		// deptId와 jobId를 찾아야 한다.
		// deptName과 jobName 을 이용해야 함 
		DeptDAO deptDao = new DeptDAO();
		JobDAO jobDao = new JobDAO();
		
		DeptDTO deptDto = deptDao.NameToId(deptName);
		JobDTO jobDto = jobDao.selectId(jobName);
		
		data.setDeptId(deptDto.getDeptId()); // id를 모르기 때문에 찾아서 넣어줌
		data.setJobId(jobDto.getJobId());	
		
		
		boolean result = dao.addEmps(data); 
		
		
		if(result) {
			dao.commit();
			dao.close();
			return 1;
		}
		dao.rollback();
		dao.close();
		return 0; // 알 수 없는 오류로 실패
	}
	public boolean add(EmpDTO empData, EmpDetailDTO empDetailData) {
		EmpDAO dao = new EmpDAO();
		boolean res1 = dao.insertEmployee(empData);
		boolean res2 = dao.updateEmployeeDetail(empDetailData);
		
		if(res1 && res2) {
			dao.commit();
			dao.close();
			return true;
		}else {
			dao.rollback();
			dao.close();
			return false;
		}
	}


}
