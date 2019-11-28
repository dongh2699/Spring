package com.test.di08;

import java.util.Map;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:map.xml");
		
		MapTest map = ctx.getBean("maptest", MapTest.class);
		
		map.print();
		
		ctx.close();
	}

}
