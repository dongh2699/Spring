package com.sist.anno05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Home {

		@Autowired
		private Worker worker;
		
		@Override
		public String toString() {
		
		return "Home 클래스 ==> " + worker ;
		}
}
