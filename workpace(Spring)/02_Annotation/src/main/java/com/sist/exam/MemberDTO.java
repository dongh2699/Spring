package com.sist.exam;

import org.springframework.stereotype.Component;

@Component
public class MemberDTO {  // 일반적인 클래스
		private String id = "hong";
		private String pwd = "1234";
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
		
		@Override
		public String toString() {
			return "MemberDTO [id=" + id + ", pwd=" + pwd + "]";
		}
		
	
}
