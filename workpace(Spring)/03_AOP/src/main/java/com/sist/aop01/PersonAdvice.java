package com.sist.aop01;

public class PersonAdvice  implements Person{

			private Person person;

			public Person getPerson() {
				return person;
			}

			public void setPerson(Person person) {
				this.person = person;
			}

			@Override
			public void doSomething() {
				System.out.println("문을 열고 집에 들어갑니다."); // 핵심 기능 이전
				
				try {
					person.doSomething(); // 핵심 관심사항
					System.out.println("잠을 잡니다.");
				}catch(Exception e) {
					System.out.println("화재 발생: 119에 신고합니다...");
				}finally {
					System.out.println("문을 열고 집을 나옵니다.");
				}
				
				
			}
			
			
}
