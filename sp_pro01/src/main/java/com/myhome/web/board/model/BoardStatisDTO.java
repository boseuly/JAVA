package com.myhome.web.board.model;

import java.sql.Date;

// 방문 내역을 담은 데이터 DTO
public class BoardStatisDTO {
	private int id;	
	private int empId; 	// 방문한 사람의 id
	private int bId;	// 방문한 게시글 id
	private boolean view;
	private Date latestView;
	private boolean like;
	private Date latestLike;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public boolean isView() {
		return view;
	}
	public void setView(boolean view) {
		this.view = view;
	}
	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	
	public Date getLatestView() {
		return latestView;
	}
	public void setLatestView(Date latestView) {
		this.latestView = latestView;
	}
	public Date getLatestLike() {
		return latestLike;
	}
	public void setLatestLike(Date latestLike) {
		this.latestLike = latestLike;
	}
	@Override
	public String toString() {
		return "EmpBoardStatisDTO [id=" + id + ", empId=" + empId + ", bId=" + bId + ", view=" + view + ", latestView="
				+ latestView + ", like=" + like + ", latestLike=" + latestLike + "]";
	}
	
}
