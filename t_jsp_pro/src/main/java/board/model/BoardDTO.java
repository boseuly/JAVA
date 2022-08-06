package board.model;

import java.sql.Date;

public class BoardDTO {
	private int id;		// 게시글 id
	private String title;	// 게시글 제목
	private String content;	// 게시글 내용 
	private int empId;		// 작성자 id
	private String empName;	// 작성자명
	private Date createDate;	// 작성날짜
	private int viewCnt;		// 조회수
	private int like;			// 추천수
	
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setId(String id) {
		this.id = Integer.parseInt(id);
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
	public void setEmpId(String empId) {
		this.empId = Integer.parseInt(empId);
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
	public void setViewCnt(String viewCnt) {
		this.viewCnt = Integer.parseInt(viewCnt);
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public void setLike(String like) {
		this.like = Integer.parseInt(like);
	}
	@Override
	public String toString() {
		return "BoardDTO [id=" + id + ", title=" + title + ", content=" + content + ", empId=" + empId + ", empName="
				+ empName + ", createDate=" + createDate + ", viewCnt=" + viewCnt + ", like=" + like + "]";
	}
	
	
}
