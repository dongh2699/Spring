package com.sist.anno02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:annotation02.xml");

		
		UseClass use = ctx.getBean("useclass",UseClass.class);
		
		use.implCall();
		
		ctx.close();
	}

}
