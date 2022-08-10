package com.myhome.web.board.vo;

public class BoardVO {
	private int id;				// 수정을 고려해서 id도 같이 넣어줌
	private String title; 		// 추가할때는 title,content만 필요
	private String content;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "BoardVO [id=" + id + ", title=" + title + ", content=" + content + "]";
	}
	
	
}
