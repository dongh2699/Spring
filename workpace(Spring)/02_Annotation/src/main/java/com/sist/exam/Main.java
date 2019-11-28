package com.sist.exam;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		AbstractApplicationContext ctx =
				new GenericXmlApplicationContext("classpath:beans_exam.xml");
		
		MemberDTO dto = ctx.getBean(MemberDTO.class);
		
		// toString() 메서드 호출 - id/pwd 출력
		System.out.println(dto.toString()); 
		
		MemberService Serv = ctx.getBean(MemberService.class);
		
		Serv.login(dto);
		
		ctx.close();
	}

}
