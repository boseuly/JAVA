package controller;

import model.vo.Grade;
import model.vo.Student;

public interface ImplDatabaseManager {
	public Grade[] search(String name);		// 성적 클래스 배열
	public boolean add(String name);		
	public Student modify(String name, String subject, int score);	// 학생정보 클래스
	public boolean remove(String name);
	
}
