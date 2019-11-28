package com.test.di06;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:sql.xml");
		
		SQLservice service = ctx.getBean("service", SQLservice.class);
		
		service.biz();
		
		ctx.close();
	}

}
