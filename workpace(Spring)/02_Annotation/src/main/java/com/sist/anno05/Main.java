package com.sist.anno05;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		AbstractApplicationContext ctx =
							new GenericXmlApplicationContext("classpath:annotation05.xml");
		
		Home home = ctx.getBean("home", Home.class);
		
		System.out.println(home.toString());
		
		ctx.close();
	}

}
