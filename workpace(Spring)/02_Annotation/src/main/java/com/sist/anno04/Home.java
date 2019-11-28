package com.sist.anno04;

import javax.annotation.Resource;

public class Home {

		@Resource(name="num1")
		// 설정파일(xml)에서 동일한 id로 자동 매핑
		// 해당 멤버에도 적용 가능
		private Number num1;
		
		@Resource(name="num2")
		private Number num2;
		
		@Resource(name="num3")
		private Number num3;
		
		public Number getNum1() {
			return num1;
		}
		public void setNum1(Number num1) {
			this.num1 = num1;
		}
		public Number getNum2() {
			return num2;
		}
		public void setNum2(Number num2) {
			this.num2 = num2;
		}
		public Number getNum3() {
			return num3;
		}
		
		@Resource(name="num3") // 설정 메서드 적용이 가능
		public void setNum3(Number num3) {
			this.num3 = num3;
		}
		
		public void print() {
				System.out.println("num1 ==> " + this.num1); 
				System.out.println("num2 ==> " + this.num2);
				System.out.println("num3 ==> " + this.num3);
		}
		
}
