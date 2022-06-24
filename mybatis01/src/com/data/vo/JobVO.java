package com.data.vo;

public class JobVO {
	// ResultMap  사용하려고 만든 클래스
	// db 타입에 맞춰서 변수 타입 지어줘야 한다.
	private String jobCode;
	private String jobTitle;
	private int jobMinSalary;
	private int jobMaxSalary;
	
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public int getJobMinSalary() {
		return jobMinSalary;
	}
	public void setJobMinSalary(int jobMinSalary) {
		this.jobMinSalary = jobMinSalary;
	}
	public int getJobMaxSalary() {
		return jobMaxSalary;
	}
	public void setJobMaxSalary(int jobMaxSalary) {
		this.jobMaxSalary = jobMaxSalary;
	}
	@Override
	public String toString() {
		return "JobVO [jobCode=" + jobCode + ", jobTitle=" + jobTitle + ", jobMinSalary=" + jobMinSalary
				+ ", jobMaxSalary=" + jobMaxSalary + "]";
	}
	
}
