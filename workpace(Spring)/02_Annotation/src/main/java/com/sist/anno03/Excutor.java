package com.sist.anno03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Excutor {
	
		@Autowired
		@Qualifier("main")
		private Worker worker;

		public Worker getWorker() {
			return worker;
		}

		public void setWorker(Worker worker) {
			this.worker = worker;
		}
		
		public void workerCall() {
			System.out.println("Excutor 클래스 실행");
			this.worker.print();
		}
		
}
