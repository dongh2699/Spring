package com.sist.aop01;

public class Main {

	public static void main(String[] args) {
		
			Person boy = new Boy();
			Person girl = new Girl();
			PersonAdvice advice = new PersonAdvice();
			
			advice.setPerson(boy);
			
			advice.doSomething();

	}

}
