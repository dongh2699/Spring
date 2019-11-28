package com.sist.model;

import java.util.HashMap;
import java.util.List;

public interface BlogDAO {
	//블로그 생성
	public int createBlog(BlogDTO dto);

	//블로그 삭제
	public int deleteBlog(BlogDTO dto);

	//블로그 수정
	public int modifyBlog(BlogDTO dto);

	//블로그 전체선택
	public List<BlogDTO> selectAllBlogList();
	
	//블로그 검색(블로그이름, 블로그카테고리 ...)
	public List<BlogDTO> searchBlogList();
	
	//가입된 블로그 보여주기
	public List<BlogDTO> myBlogList(MemberDTO dto);
	
	//블로그 넘버 가져오기
	public int selectOneBlog(BlogDTO dto);
	
	//블로그 관리자 생성
	public void createBlog_Member(HashMap<String, Object> req);
	
	//해당 블로그 객체 가져오기
	public BlogDTO getBlog_Detail(int b_No);
	
	//블로그 멤버수 가져오기
	public int Member_Count(int b_No);
	
	//블로그 게시판글 추가
	public int create_board(Blog_contentDTO dto);
	
	//블로그 게시글 가져오기
	public List<Blog_contentDTO> getBoardList(Blog_contentDTO dto);
}
