package emps.model;

public class EmpDTO {
	private int empId;
	private String empName;
	private String email;
	private String jobName; // 직급id가 아닌 직급명을 보여주기 위해서 
	private String jobId;
	private String deptName;
	private int deptId;
	
	
	@Override
	public String toString() {
		return "EmpDTO [empId=" + empId + ", empName=" + empName + ", email=" + email + ", jobName=" + jobName
				+ ", jobId=" + jobId + ", deptName=" + deptName + ", deptId=" + deptId + "]";
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	// int형 맨날 형변환하기 귀찮으니까 생성자에서 형변환시켜줌
	public void setEmpId(String empId) {
		this.empId = Integer.parseInt(empId);
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	// int형 맨날 형변환하기 귀찮으니까 생성자에서 형변환시켜줌
	public void setDeptId(String deptId) {
		this.deptId = Integer.parseInt(deptId);
	}
	
}
