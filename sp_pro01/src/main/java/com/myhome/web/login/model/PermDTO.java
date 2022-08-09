package com.myhome.web.login.model;

import java.io.Serializable;

public class PermDTO implements Serializable{ // 세션에 객체 저장시 텍스트 파일과 객체가 유사하면 자동으로 역직렬화 해준다. 하지만 
	private static final long serialVersionUID = 1L; // 지금 어떤 정보가 없어서 implements 해줌
	private int empid;
	private String tablename;
	private boolean pRead;
	private boolean pAdd;
	private boolean pUpdate;
	private boolean pDelete;
	
	
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public boolean ispRead() {
		return pRead;
	}
	public void setpRead(boolean pRead) {
		this.pRead = pRead;
	}
	public boolean ispAdd() {
		return pAdd;
	}
	public void setpAdd(boolean pAdd) {
		this.pAdd = pAdd;
	}
	public boolean ispUpdate() {
		return pUpdate;
	}
	public void setpUpdate(boolean pUpdate) {
		this.pUpdate = pUpdate;
	}
	public boolean ispDelete() {
		return pDelete;
	}
	public void setpDelete(boolean pDelete) {
		this.pDelete = pDelete;
	}
	@Override
	public String toString() {
		return "PermDTO [empid=" + empid + ", tablename=" + tablename + ", pRead=" + pRead + ", pAdd=" + pAdd
				+ ", pUpdate=" + pUpdate + ", pDelete=" + pDelete + "]";
	}
	
	
}
