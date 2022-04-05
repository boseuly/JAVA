package model.vo;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Teacher {		// 선생님의 정보
	private String name;
	private String password;
	private Date loginDate;		// 로그인 시간을 기록하기 위해 만듦 -> 로그인이 되면 얘를 기록하면 됨

	// 생성자
	public Teacher(String name) {
		this.name = name;
		this.password = "1111"; // 초기비번
	}

	public Teacher(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	// getter & setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public String getLoginDateFormat() {		// 보기 좋게 문자열로 접속 시간 보여주는 메소드
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		return sFormat.format(loginDate);		// sFormat 형식으로 loginDate 반환
	}
	
	
	public void setLoginDate(Date date) {
		this.loginDate = date;
	}

	
	
	
}
