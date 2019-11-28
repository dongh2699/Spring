package com.sist.model;

import javax.servlet.http.HttpSession;

public interface MemberDAO {
	 
	 public int createMember(MemberDTO dto) ;
	 
	  public int EmailCheck(String m_email);
	// 01_01. 회원 로그인 체크
	  public MemberDTO loginCheck(String m_Email);
		
	    // 03. 화원 정보 수정
	  public int modify_Member(MemberDTO dto);
		
		//회원 정보 삭제
	  public int delete_Member(String m_Email);
		
		//비밀번호 확인
	  public int pw_Check(String m_Pw);
      // 이메일 찾기
      public MemberDTO findEmail(MemberDTO dto);
      
      // 비밀번호 찾기
      public MemberDTO findPwd(MemberDTO dto);
      
      //비밀번호 수정
      public int modifypwd(MemberDTO dto);
}
