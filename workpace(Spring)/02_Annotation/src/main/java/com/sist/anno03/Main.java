package com.sist.anno03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:annotation03.xml");
		
		Excutor exe =  ctx.getBean("excutor" , Excutor.class);
		
		exe.workerCall();
		
		ctx.close();
	}

}
