package com.sist.anno01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:annotation01.xml");
			
		Required 	required = ctx.getBean("required", Required.class);
		
		required.print();
		
		ctx.close();

	}

}
