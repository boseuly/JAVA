package com.data.vo;

public class DeptVO {
	//ResultMap  사용하려고 만든 클래스
	private int deptId;
	private String DeptName;
	
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return DeptName;
	}
	public void setDeptName(String deptName) {
		DeptName = deptName;
	}
	@Override
	public String toString() {
		return "DeptVO [deptId=" + deptId + ", DeptName=" + DeptName + "]";
	}
}
