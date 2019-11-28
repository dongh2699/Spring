package com.test.di09;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* DI 설정에서 Java 코드에서 애노테이션을 설정하여 주입하는 방법
 *@Configuration, @Bean 애노테이션을 사용함.
 *@Configuration :  클래스 앞에 선언하는 애노테이션
 *									"해당 클래스는 스프링 설정에 사용되는 클래스 입니다." 라고
 *									알려주는 애노테이션.
 *@Bean : 메서드 앞에 선언하는 애노테이션.
 *					"해당 메서드는 객체를 생성하는데 사용" 된다는 의미.
 *- 애노테이션은
 *		- 컴파일러에게 정보를 알려주거나
 *		- 컴파일 할 때와 설치 시의 작업을 지정하거나.
 *		- 실행할 때 별도의 처리가 필요한 경우에 사용한다.
 *		- 애노테이션은 클래스, 메서드, 변수 등 모든 요소에 선언이 가능하다.
*/

@Configuration
public class Config {
	Scanner scan = new Scanner(System.in);
	@Bean
	public Player player1() {
		ArrayList<String> position = new ArrayList<String>();
		
		System.out.print("보직:");		
		String po1 = scan.next();
		System.out.print("보직:");
		String po2 = scan.next();
		
		position.add(po1);
		position.add(po2);
		
		System.out.print("이름: ");
		String name = scan.next();
		scan.nextLine();
		System.out.print("나이:");
		int age = scan.nextInt();
	
		
		Player player = new Player(name, age, position);
	
		System.out.print("신장:");
		int height = scan.nextInt();
	
		System.out.print("몸무게:");
		int weight = scan.nextInt();
		
		player.setHeight(height);
		player.setWeight(weight);
		
		return player;
	}
	
	@Bean
	public Player player2() {
		ArrayList<String> position = new ArrayList<String>();
		System.out.print("보직:");		
		String po1 = scan.next();
		System.out.print("보직:");
		String po2 = scan.next();
		
		position.add(po1);
		position.add(po2);
		
		System.out.print("이름: ");
		String name = scan.next();
		scan.nextLine();
		System.out.print("나이:");
		int age = scan.nextInt();
	
		
		Player player = new Player(name, age, position);
	
		System.out.print("신장:");
		int height = scan.nextInt();
	
		System.out.print("몸무게:");
		int weight = scan.nextInt();
		
		player.setHeight(height);
		player.setWeight(weight);
		
		return player;
	}

}
