package com.sist.model;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	public int deleteBlog(int b_No) {

		return this.sqlSession.delete("delete_Bond", b_No);
	}

	// 검색한 블로그 리스트 카운트
	@Override
	public int countSearchBlogList(String searchContent) {
		return this.sqlSession.selectOne("CountSearchBlogList", searchContent);
	}

	// 검색한 블로그 리스트 불러오기
	@Override
	public List<HashMap> searchBlogList(HashMap<String, Object> sbp) {
		return this.sqlSession.selectList("search_blog", sbp);
	}

	// 사용자가 가입한 블로그 리스트 카운트
	@Override
	public int countMyBlogList(String m_Email) {

		return this.sqlSession.selectOne("countMyBlogList", m_Email);
	}

	// 사용자 블로그 리스트 페이징처리
	@Override
	public List<BlogDTO> myBlogList(HashMap<String, Object> mbp) {
		return this.sqlSession.selectList("select_MyBlog", mbp);
	}

	@Override
	public int modifyBlog(BlogDTO dto) {

		return this.sqlSession.update("modify_Bond", dto);

	}

	@Override
	public List<BlogDTO> selectAllBlogList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectOneBlog(BlogDTO dto) {
		int res;
		res = this.sqlSession.selectOne("get_Bond", dto);
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
		return this.sqlSession.selectOne("member_Count", b_No);

	}

	@Override
	public List<Blog_contentDTO> gethotcontent_total() {
		return this.sqlSession.selectList("gethotcontent_total");
	}

	// 가입된 멤버인지 체크
	@Override
	public int checkGrade(HashMap<String, Object> gmp) {

		return this.sqlSession.selectOne("blog_Member_Check", gmp);
	}

	// Admin 또는 Manager 인지 확인
	@Override
	public int checkNotice(HashMap<String, Object> ngm) {

		return this.sqlSession.selectOne("blog_Notice_Member_Check", ngm);
	}

	@Override
	public String getMember_Grade(HashMap<String, Object> getMemeber_Grade) {

		return this.sqlSession.selectOne("get_Grede", getMemeber_Grade);
	}
	// 본드 회원 추가
	   @Override
	   public int joinBondGuest(HashMap<String, Object> req) {
	      return this.sqlSession.insert("bondGuestJoin", req);
	   }

	   // 본드 회원 탈퇴
	   @Override
	   public int withdrawalBondMember(HashMap<String, Object> req) {
	      return this.sqlSession.insert("bondGuestWithdrawal", req);
	   }

}
