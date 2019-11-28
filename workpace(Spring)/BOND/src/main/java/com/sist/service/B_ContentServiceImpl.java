package com.sist.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.model.B_ContentDAO;
import com.sist.model.B_ContentFileDAO;
import com.sist.model.B_Content_FileDTO;
import com.sist.model.BlogDAO;
import com.sist.model.Blog_Content_GoodDTO;
import com.sist.model.Blog_contentDTO;
import com.sist.model.Content_CommentDTO;
import com.sist.model.MemberDTO;

@Service
public class B_ContentServiceImpl implements B_ContentService {

	private final B_ContentDAO b_ContentDAO;
	private final B_ContentFileDAO b_ContentFileDAO;

	@Inject
	public B_ContentServiceImpl(B_ContentDAO b_ContentDAO, B_ContentFileDAO b_ContentFileDAO) {

		this.b_ContentDAO = b_ContentDAO;
		this.b_ContentFileDAO = b_ContentFileDAO;
	}

	@Transactional
	@Override
	public int create_board(Blog_contentDTO dto) throws Exception {
		int res = b_ContentDAO.create_board(dto);

		int board_no = b_ContentDAO.getboard_no();
		String[] files = dto.getBc_files();
		HashMap<String, Object> blog_contentfile = new HashMap<String, Object>();
		if (files != null) {
			for (String fileName : files) {
				blog_contentfile.put("board_no", board_no);
				blog_contentfile.put("fileName", fileName);

				b_ContentFileDAO.addFile(blog_contentfile);
			}
		}

		return res;
	}

	// 공지사항 추가
	@Transactional
	@Override
	public int create_notice(Blog_contentDTO dto) throws Exception {
		int res = b_ContentDAO.create_notice(dto);

		int board_no = b_ContentDAO.getboard_no();
		String[] files = dto.getBc_files();
		HashMap<String, Object> blog_contentfile = new HashMap<String, Object>();
		if (files != null) {
			for (String fileName : files) {
				blog_contentfile.put("board_no", board_no);
				blog_contentfile.put("fileName", fileName);

				b_ContentFileDAO.addFile(blog_contentfile);
			}
		}

		return res;
	}

	@Transactional
	@Override
	public int update_board(Blog_contentDTO dto) throws Exception {
		int res = b_ContentDAO.update_Board(dto);
		String[] files = dto.getBc_files();
		HashMap<String, Object> blog_contentfile = new HashMap<String, Object>();
		if (files != null) {
			blog_contentfile.put("board_no", dto.getBc_No());
			b_ContentFileDAO.deleteFile(dto.getBc_No());
			for (String fileName : files) {
				blog_contentfile.put("fileName", fileName);
				b_ContentFileDAO.addFile(blog_contentfile);
			}
		}
		return res;
	}

	@Transactional
	@Override
	public int delete_board(Blog_contentDTO dto) {
		b_ContentFileDAO.deleteFile(dto.getBc_No());
		return b_ContentDAO.delete_Board(dto.getBc_No());

	}

	@Override
	public List<Blog_contentDTO> NoticeBoardList(int b_No) {
		// TODO Auto-generated method stub
		return b_ContentDAO.NoticeBoardList(b_No);
	}

	@Override
	public List<MemberDTO> getBoardList(Blog_contentDTO dto) {
		return b_ContentDAO.getBoardList(dto);
	}

	@Override
	public int create_Comment(Content_CommentDTO dto) {
		return b_ContentDAO.create_Comment(dto);
	}

	@Override
	public List<HashMap<String, Object>> getcomment_list(Content_CommentDTO dto) {
		return b_ContentDAO.getcomment_list(dto);
	}

	@Override
	public String CheckGoods(Blog_Content_GoodDTO dto) {

		return b_ContentDAO.CheckGoods(dto);
	}

	@Override
	public int addGoods(Blog_Content_GoodDTO dto) {

		return b_ContentDAO.addGoods(dto);
	}

	@Override
	public int CancelGoods(Blog_Content_GoodDTO dto) {

		return b_ContentDAO.CancelGoods(dto);
	}

	@Override
	public int getGoodsCount(int bc_No) {

		return b_ContentDAO.getGoodsCount(bc_No);
	}

	@Override
	public List<Blog_contentDTO> HotBoardList(int b_No) {

		return b_ContentDAO.HotBoardList(b_No);
	}

	@Override
	public void updateGoods(Blog_contentDTO dto) {
		b_ContentDAO.updateGoods(dto);

	}

	@Override
	public int delete_Comment(Content_CommentDTO dto) {
		return b_ContentDAO.delete_Comment(dto);
	}

	@Override
	public int update_Comment(Content_CommentDTO dto) {
		// TODO Auto-generated method stub
		return b_ContentDAO.update_Comment(dto);
	}

	@Override
	public int create_Reply_Comment(Content_CommentDTO dto) {

		return b_ContentDAO.create_Reply_Comment(dto);
	}

	@Override
	public int getComment_step(int cc_No) {
		return b_ContentDAO.getComment_step(cc_No);

	}

	@Override
	public void update_Step(Content_CommentDTO dto) {
		 b_ContentDAO.update_Step(dto);
		
	}
	// 사진첩가져오기
    @Override
    public List<HashMap<String, Object>> getPictureList(int b_No) {
       
       return b_ContentDAO.getPictureList(b_No);
       
    }
    
    // 인기사진첩 가져오기
    @Override
    public List<HashMap<String, Object>> getHotpictureList(int b_No) {
    
       return b_ContentDAO.getHotpictureList(b_No);
    }   

}
