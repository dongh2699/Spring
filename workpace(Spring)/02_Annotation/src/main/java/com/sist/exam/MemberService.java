package com.sist.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

		@Autowired
		private MemberDAO dao;

		public MemberDAO getDao() {
			return dao;
		}

		public void setDao(MemberDAO dao) {
			this.dao = dao;
		}
		
		public void login(MemberDTO dto) {
			dao.result(dto);
		}
		
}
