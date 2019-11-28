package com.sist.model;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogDAOImpl implements BlogDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int createBlog(BlogDTO dto) {
		this.sqlSession.insert("createBond", dto);
		return 0;
	}

	@Override
	public int deleteBlog(BlogDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyBlog(BlogDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BlogDTO> selectAllBlogList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlogDTO> searchBlogList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlogDTO> myBlogList(MemberDTO dto) {
		return this.sqlSession.selectList("select_MyBlog", dto);
	}

	@Override
	public int selectOneBlog(BlogDTO dto) {
		 int res;
		 res=this.sqlSession.selectOne("get_Bond",dto);
		 return res;
	}

	@Override
	public void createBlog_Member(HashMap<String, Object> req) {
		this.sqlSession.insert("b_m_create", req);
		
	}

	@Override
	public BlogDTO getBlog_Detail(int b_No) {
		return this.sqlSession.selectOne("getBlog_Detail", b_No);
		
	}

	@Override
	public int Member_Count(int b_No) {
		return this.sqlSession.selectOne("member_Count",b_No);
		
	}

	public int create_board(Blog_contentDTO dto){
		

		
		return this.sqlSession.insert("create_board",dto);
	}

	@Override
	public List<Blog_contentDTO> getBoardList(Blog_contentDTO dto) {
		return this.sqlSession.selectList("get_BoardList",dto);
	}

	
	

	

	
}
