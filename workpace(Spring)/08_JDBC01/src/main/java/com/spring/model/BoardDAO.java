package com.spring.model;

import java.util.List;

public interface BoardDAO {
	List<BoardDTO> getBoardList();
	void insertBoard(BoardDTO dto);
	BoardDTO boardCont(int no);
	
	void readCount(int no);
	void updateBoard(BoardDTO dto);
	void deleteBoard(int no);
}
