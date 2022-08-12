package com.myhome.web.upload.model;

public class FileUploadDTO {
	private int id;
	private int bId;
	private String fileName;	
	private String location; 	// 실제 파일 주소
	private String url;			// 파일 url 
	private long fileSize;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	@Override
	public String toString() {
		return "FileUploadDTO [id=" + id + ", bId=" + bId + ", fileName=" + fileName + ", location=" + location + ", url="
				+ url + ", fileSize=" + fileSize + "]";
	}
	
}
