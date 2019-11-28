package com.test.di02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		AbstractApplicationContext ctx =
					new GenericXmlApplicationContext("classpath:getsum.xml");
		
		// setter() 메서드를 통한 주입
		MyGetSum getSum = ctx.getBean("mySum", MyGetSum.class);
		
		getSum.sum();
		
		ctx.close();

	}

}
