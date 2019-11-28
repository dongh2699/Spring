package com.sist.model;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int createMember(MemberDTO dto) {
	
		return this.sqlSession.insert("addMember", dto);

	}

	@Override
	public int EmailCheck(String m_email) {
		int res = 0;
		MemberDTO dto = null;

		dto = this.sqlSession.selectOne("emailCheck", m_email);

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

		//로그인 체크 이메일 체크 같음 수정할 필요가 있어보임
	

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
	   public MemberDTO findEmail(MemberDTO dto) {
	      
	      System.out.println(dto.getM_Name());
	      System.out.println(dto.getM_Phone());
	      
	      return sqlSession.selectOne("findEmail", dto);
	   }

	   @Override
	   public MemberDTO findPwd(MemberDTO dto) {
	      // TODO Auto-generated method stub
	      return null;
	   }

	   @Override
	   public int modifypwd(MemberDTO dto) {
	      return sqlSession.update("modifypwd", dto);
	   }

}
