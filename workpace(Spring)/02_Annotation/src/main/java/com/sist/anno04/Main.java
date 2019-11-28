package com.sist.anno04;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:annotation04.xml");
		
		Home home = ctx.getBean("home", Home.class);

		home.print();
		
		ctx.close();
	}

}
