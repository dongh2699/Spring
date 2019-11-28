package com.sist.anno03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main2 {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:annotation03.xml");
		
		Student student =  ctx.getBean("student" , Student.class);
		
		student.print();
		
		ctx.close();
		

	}

}
