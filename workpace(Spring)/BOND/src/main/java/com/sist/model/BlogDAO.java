package com.sist.model;

import java.util.HashMap;
import java.util.List;

public interface BlogDAO {
	// 블로그 생성
	public int createBlog(BlogDTO dto);

	// 블로그 삭제
	public int deleteBlog(int b_No);

	// 블로그 수정
	public int modifyBlog(BlogDTO dto);

	// 블로그 전체선택
	public List<BlogDTO> selectAllBlogList();

	// 블로그 검색(블로그이름, 블로그카테고리 ...)
	public List<HashMap> searchBlogList(HashMap<String, Object> sbp);

	// 검색한 블로그 수 카운트
	public int countSearchBlogList(String search_content);

	// 사용자가 가입된 블로그 수 카운트
	public int countMyBlogList(String m_Email);

	// 사용자 가입된 블로그 보여주기
	public List<BlogDTO> myBlogList(HashMap<String, Object> mbp);

	// 블로그 넘버 가져오기
	public int selectOneBlog(BlogDTO dto);

	// 블로그 관리자 생성
	public void createBlog_Member(HashMap<String, Object> req);

	// 해당 블로그 객체 가져오기
	public BlogDTO getBlog_Detail(int b_No);

	// 블로그 멤버수 가져오기
	public int Member_Count(int b_No);


	// 사용자가 가입된 전체 블로그 중 좋아요가 가장 많은 content 가져오기
	public List<Blog_contentDTO> gethotcontent_total();

	// 가입된 일반 회원인지 확인
	public int checkGrade(HashMap<String, Object> gmp);

	// Admin 또는 Manager 인지 확인
	public int checkNotice(HashMap<String, Object> ngm);

	public String getMember_Grade(HashMap<String, Object> getMemeber_Grade);

	// 본드 회원 추가
	   public int joinBondGuest(HashMap<String, Object> req);

	   // 본드 회원 탈퇴
	   public int withdrawalBondMember(HashMap<String, Object> req);
	 }
