package board.model;

import java.sql.Date;

import emp.model.EmpDTO;

public class EmpBoardDTO {
	private int id;			// 게시글 id
	private String title;
	private String content;
	private int empId;		// 작성자 id
	private EmpDTO empObj; 	// emp객체를 넣어줌 -> 해당 게시글을 누가 봤는지 알아야 할 때 자꾸 
	private Date createDate;
	private int viewCnt;
	private int like;
	
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
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	@Override
	public String toString() {
		return "EmpBoardDTO [id=" + id + ", title=" + title + ", content=" + content + ", empId=" + empId
				+ ", createDate=" + createDate + ", viewCnt=" + viewCnt + ", like=" + like + "]";
	}
	
}
