package emps.model;

import java.sql.Date;

public class EmpDetailDTO {
	private int empId;
	private String phone;
	private Date hireDate;
	private int salary;
	private double commission;
	private int mngId;
	
	public int getEmpId() {
		return empId;
	}
	
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Date getHireDate() {
		return hireDate;
	}
	
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	
	public int getSalary() {
		return salary;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public double getCommission() {
		return commission;
	}
	
	public void setCommission(double commission) {
		this.commission = commission;
	}
	
	public int getMngId() {
		return mngId;
	}
	
	public void setMngId(int mngId) {
		this.mngId = mngId;
	}
	
	@Override
	public String toString() {
		return "EmpDetailDTO [empId=" + empId + ", phone=" + phone + ", hireDate=" + hireDate + ", salary=" + salary
				+ ", commission=" + commission + ", mngId=" + mngId + "]";
	}

	public void setEmpId(String empId2) {
		this.empId = Integer.parseInt(empId2);
		
	}

	public void setHireDate(String hireDate2) {
		if(hireDate2 != null) {
			if(hireDate2.isEmpty()) { // 만약 "" 빈문자열이 들어온다면
				this.hireDate = new Date(new java.util.Date().getTime()); 
			}else {
				this.hireDate = Date.valueOf(hireDate2);
			}
		}else {
			// util.Date를 통해 오늘 날짜를 생성한 뒤 sql.Date에 넣어줌 
			this.hireDate = new Date(new java.util.Date().getTime());
		}
	}
	public void setSalary(String salary2) {
		if(salary2 == null) {
			salary2 = "0";
		}
		this.salary = Integer.parseInt(salary2);
		
	}

	public void setCommission(String commission2) {
		// 혹시 문자열로 "10" -> 0.01로 변환해줘야 한다. 
		this.commission = Double.parseDouble(commission2);
	}
}
