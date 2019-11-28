package com.sist.model;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class B_ContentDAOImpl implements B_ContentDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	//일반회원 글쓰기
	   @Override
	   public int create_board(Blog_contentDTO dto){
	 
	      return this.sqlSession.insert("create_board",dto);
	   }
	   // 관리자 공지사항
	   @Override
	   public int create_notice(Blog_contentDTO dto) {
	   
	      return this.sqlSession.insert("create_notice", dto);
	   }

	@Override
	public List<MemberDTO> getBoardList(Blog_contentDTO dto) {
		
		return this.sqlSession.selectList("get_BoardList", dto);
	}

	@Override
	public int create_Comment(Content_CommentDTO dto) {

		return this.sqlSession.insert("create_Comment", dto);
	}

	@Override
	public List<HashMap<String, Object>> getcomment_list(Content_CommentDTO dto) {

		return this.sqlSession.selectList("get_CommentList", dto);

	}

	/*
	 * public List<HashMap> recommendation_Bond() { return
	 * this.sqlSession.selectList("recommendation_Bond"); }
	 */
	// 좋아요 갯수 카운트
	@Override
	public int getGoodsCount(int bc_No) {

		return this.sqlSession.selectOne("GoodsCount", bc_No);
	}

	// 좋아요, 좋아요 취소 체크
	@Override
	public String CheckGoods(Blog_Content_GoodDTO dto) {
		String checkStr = "";
		Blog_Content_GoodDTO Gdto = this.sqlSession.selectOne("GoodsCheck", dto);

		if (Gdto == null) {
			checkStr = "false";
		} else {
			checkStr = "true";
		}

		return checkStr;
	}

	// 좋아요 추가
	@Override
	public int addGoods(Blog_Content_GoodDTO dto) {

		return this.sqlSession.insert("addGoods", dto);
	}

	// 좋아요 취소
	@Override
	public int CancelGoods(Blog_Content_GoodDTO dto) {

		return this.sqlSession.delete("CancelGoods", dto);
	}

	@Override
	public void updateGoods(Blog_contentDTO dto) {
		this.sqlSession.update("updateGoods", dto);

	}

	@Override
	public int getboard_no() {

		return this.sqlSession.selectOne("getboard_no");
	}

	// 공지사항 불러오기
	@Override
	public List<Blog_contentDTO> NoticeBoardList(int b_No) {

		return this.sqlSession.selectList("getNoticeList", b_No);
	}

	// 인기게시글 불러오기
	@Override
	public List<Blog_contentDTO> HotBoardList(int b_No) {

		return this.sqlSession.selectList("getHotList", b_No);
	}
	@Override
	public int update_Board(Blog_contentDTO dto) {
	
		return this.sqlSession.update("update_Board", dto);
	}
	@Override
	public int delete_Board(int bc_No) {
		return  this.sqlSession.delete("delete_Board",bc_No);
		
	}
	@Override
	public int delete_Comment(Content_CommentDTO dto) {
		return this.sqlSession.delete("delete_Comment", dto);
	}
	@Override
	public int update_Comment(Content_CommentDTO dto) {
		return this.sqlSession.update("update_Comment",dto);
	}
	@Override
	public int create_Reply_Comment(Content_CommentDTO dto) {
		System.out.println(dto.getCc_Cont());
		System.out.println(dto.getCc_Group());
		System.out.println(dto.getCc_Indent());
		System.out.println(dto.getCc_No());
		System.out.println(dto.getCc_Step());
		
		
		return this.sqlSession.insert("create_Reply_Comment",dto);
	}
	@Override
	public int getComment_step(int cc_No) {
		return this.sqlSession.selectOne("getComment_step", cc_No);
	}
	@Override
	public void update_Step(Content_CommentDTO dto) {
		this.sqlSession.update("update_Step",dto);
		
	}
	// 사진첩가져오기
    @Override
    public List<HashMap<String, Object>> getPictureList(int b_No) {
       System.out.println(b_No);
       return this.sqlSession.selectList("getPictureList", b_No);
       
    }
    
    // 인기사진첩 가져오기
    @Override
    public List<HashMap<String, Object>> getHotpictureList(int b_No) {
    
       return this.sqlSession.selectList("getHotPicture", b_No);
    }   
	
}
