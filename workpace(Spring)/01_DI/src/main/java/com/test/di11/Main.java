package com.test.di11;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.test.di11.Player;

public class Main {

	public static void main(String[] args) {
		// 자바 코드에 XML 파일을 포함해서 DI를 적용하는 방법
		
		AnnotationConfigApplicationContext ctx =
							new AnnotationConfigApplicationContext(Config.class);
		
		Player player1 = ctx.getBean("player1" , Player.class);
		System.out.println("선수 이름 :"  + player1.getName());
		System.out.println("선수 나이 :"  + player1.getAge());
		System.out.println("선수 포지션 :"  + player1.getPosition());
		System.out.println("선수 신장 :"  + player1.getHeight());
		System.out.println("선수 체중 :"  + player1.getWeight());
		System.out.println("=============================");
		
		Player player2 = ctx.getBean("player2" , Player.class);
		System.out.println("선수 이름 :"  + player2.getName());
		System.out.println("선수 나이 :"  + player2.getAge());
		System.out.println("선수 포지션 :"  + player2.getPosition());
		System.out.println("선수 신장 :"  + player2.getHeight());
		System.out.println("선수 체중 :"  + player2.getWeight());
		System.out.println("=============================");
		
		Player player3 = ctx.getBean("player3" , Player.class);
		System.out.println("선수 이름 :"  + player3.getName());
		System.out.println("선수 나이 :"  + player3.getAge());
		System.out.println("선수 포지션 :"  + player3.getPosition());
		System.out.println("선수 신장 :"  + player3.getHeight());
		System.out.println("선수 체중 :"  + player3.getWeight());
		System.out.println("=============================");
		
		ctx.close();

	}

}
