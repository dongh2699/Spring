package com.sist.model;

import java.util.List;

public interface bbsDAO {
		List<bbsDTO> getBoardList();
		int  insertBoard(bbsDTO dto);
		bbsDTO boardCont(int no);
		void readCount(int no);
		int updateBoard(bbsDTO dto);
		int deleteBoard(int no);
		void replyUpdate(int board_group, int board_step);
		int replyBoard(bbsDTO dto);
}
