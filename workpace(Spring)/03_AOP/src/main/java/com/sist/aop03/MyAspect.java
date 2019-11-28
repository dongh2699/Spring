package com.sist.aop03;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// 애노테이션을 이용하는방법(@AspectJ 애노테이션 기반의 AOP구현)
@Aspect
@Component
public class MyAspect {

		private double time;
		
		@Pointcut("execution(* doSomething())")
		public void myPointcut() {}
		
		
		
		@Before("myPointcut()")
		public void before() {
				time = System.currentTimeMillis();
				System.out.println("문을 열고 집에 들어갑니다.");
		}
		
		@After("myPointcut()")
		public void af() {
				time = System.currentTimeMillis() - time;
				System.out.println("문을 열고 집을 나옵니다.");
				System.out.println("걸린시간 : " + time + "ms");
		}
		
		@AfterReturning("myPointcut()")
		public void after_returning() {
			System.out.println("잠을 잡니다.");
		}
		@AfterThrowing("myPointcut()")
		public void after_throwing() {
			System.out.println("화재 발생 : 119에 신고합니다.");
		}
		
}
