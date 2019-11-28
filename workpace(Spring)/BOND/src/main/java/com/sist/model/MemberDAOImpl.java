package com.sist.model;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	//회원가입 멤버 생성
	@Override
	public int createMember(MemberDTO dto) {
	
		return this.sqlSession.insert("addMember", dto);

	}

	//이메일 확인
	@Override
	public int EmailCheck(String m_email) {
		int res = 0;
		 MemberDTO dto = this.sqlSession.selectOne("emailCheck", m_email);

		try {
			if (dto == null) {
				res = 1;
			} else {
				res = 0;
			}
		} catch (Exception e) {

		}
		return res;
	}


	@Override
	public MemberDTO loginCheck(String m_Email) {
		return this.sqlSession.selectOne("loginCheck", m_Email);
		 
	}

	@Override
	public int modify_Member(MemberDTO dto) {

		return this.sqlSession.update("modify_Member",dto);
	}

	@Override
	public int delete_Member(String m_Email) {
		return this.sqlSession.delete("delete_Member", m_Email);
	
	}

	@Override
	public int pw_Check(String m_Pw) {
		return this.sqlSession.selectOne("check_Pw",m_Pw);
	}

	@Override
	   public List<MemberDTO> findEmail(MemberDTO dto) {

	      return sqlSession.selectList("findEmail", dto);
	   }


	   @Override
	   public int modifypwd(MemberDTO dto) {
	      return sqlSession.update("modifypwd", dto);
	   }

}
