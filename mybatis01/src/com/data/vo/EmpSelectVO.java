package com.data.vo;

import java.sql.Date;
import java.util.List;

public class EmpSelectVO {
	private int salary;
	private Date startDate;	// sql 로 전달해야 하는 date이기때문에 sql.Date로 임포트
	private Date endDate;
	private int deptId;
	private List<Integer> deptIdList;
//	private String strStartDate;
//	private String strEndDate;
	
	public List<Integer> getDeptIdList() {
		return deptIdList;
	}
	public void setDeptIdList(List<Integer> deptIdList) {
		this.deptIdList = deptIdList;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	
}
