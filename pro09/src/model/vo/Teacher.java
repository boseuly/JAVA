package model.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Teacher extends Account {
	private Date loginDate;	// 로그인 시간
	
	public Teacher(String name) {		
		setName(name);
		setPassword("1111");
	}
	
	public Teacher(String name, String password) {
		setName(name);
		setPassword(password);
	}

	public Date getLoginDate() {
		return loginDate;
	}
	
	public String getLoginDateFormat() {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		return sFormat.format(loginDate);		// sFormat형식으로 loginDate를 반환
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	@Override
	public String resetPassword() {
		String prefix = "TCH_";
		String newPass = super.resetPassword();	// 새로운 패스워드 생성
		setPassword(prefix + newPass);
		return newPass;
	}
	
	
	
	
	
}
