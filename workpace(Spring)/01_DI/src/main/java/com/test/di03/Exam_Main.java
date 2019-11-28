package com.test.di03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Exam_Main {

	public static void main(String[] args) {
		AbstractApplicationContext ctx =
				new GenericXmlApplicationContext("classpath:examdao.xml");
		
		// 생성자를 이용해서 주입하는 방법
		ExamDAO dao = ctx.getBean("exam", ExamDAO.class);
		
		dao.output();
		
		ctx.close();
	}

}
