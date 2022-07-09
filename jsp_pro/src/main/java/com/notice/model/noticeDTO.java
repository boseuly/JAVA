package com.notice.model;

import java.sql.Date;

public class noticeDTO {
	private int id;
	private String title;
	private String writerId;
	private String content;
	private Date regdate;
	private String  hit;
	private String files;
	public noticeDTO(int id, String title, String writerId, String content, Date regdate, String hit, String files) {
		super();
		this.id = id;
		this.title = title;
		this.writerId = writerId;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.files = files;
	}
	public noticeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	@Override
	public String toString() {
		return "noticeDTO [id=" + id + ", title=" + title + ", writerId=" + writerId + ", content=" + content
				+ ", regdate=" + regdate + ", hit=" + hit + ", files=" + files + "]";
	}
	
}
