package com.spring.model;

import java.util.List;

public interface BoardDAO {

	
		public List<BoardDTO> getList();
		public int insertBoard(BoardDTO dto);
		public void readCount(int no);
		public BoardDTO content(int no);
		public int updateBoard(BoardDTO dto);
		public int deleteBoard(int no);
		public List<BoardDTO> search(String field, String name);
}
