package com.test.di03;

public class ExamDAO {

	private String msg;
	
	public ExamDAO() {} // 기본생성자
	
	
	public ExamDAO(String msg) {
		this.msg = msg;
	} // 생성자오버로딩
	
	public void output() {
		System.out.println("메세지 ==> " + msg);
	}
	
	
	
}
