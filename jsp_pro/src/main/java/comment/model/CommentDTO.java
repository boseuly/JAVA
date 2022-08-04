package comment.model;

import java.sql.Date;

public class CommentDTO {
	private int id;
	private int bId;
	private String content;
	private int empId;
	private Date createDate;
	private String deleted;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}
	
	public int getbId() {
		return bId;
	}
	public void setbId(String bId) {
		this.bId = Integer.parseInt(bId);
	}
	
	public void setbId(int bId) {
		this.bId = bId;
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
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	@Override
	public String toString() {
		return "CommnetDTO [id=" + id + ", bId=" + bId + ", content=" + content + ", empId=" + empId + ", createDate="
				+ createDate + ", deleted=" + deleted + "]";
	}
	
	
}
