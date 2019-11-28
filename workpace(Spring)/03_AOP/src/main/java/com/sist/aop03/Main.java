package com.sist.aop03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = 
						new GenericXmlApplicationContext("classpath:aop02.xml");
		
		Person boy = ctx.getBean("boy" , Person.class);
		
		boy.doSomething();
		
		System.out.println("==============================");
		
		Person girl = ctx.getBean("girl" , Person.class);
		
		girl.doSomething();
		
		ctx.close();
	}

}
