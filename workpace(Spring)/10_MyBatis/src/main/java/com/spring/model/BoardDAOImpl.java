package com.spring.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	@Override
	public List<BoardDTO> getList() {
		
		return this.sqlSession.selectList("list");
		
	}

	@Override
	public int insertBoard(BoardDTO dto) {
		int res=0;
		res = this.sqlSession.insert("add", dto);
		
		return res;
	}

	@Override
	public void readCount(int no) {
		this.sqlSession.update("count", no);

	}

	@Override
	public BoardDTO content(int no) {
//		BoardDTO dto;
//		dto = this.sqlSession.selectOne("cont", no);
//		return  dto;
		
		return this.sqlSession.selectOne("cont", no);
	}

	@Override
	public int updateBoard(BoardDTO dto) {
		int res=0;
		res = this.sqlSession.update("modify", dto);
		return res;
	}

	@Override
	public int deleteBoard(int no) {
		int res = 0;
		res = this.sqlSession.delete("delete", no);
		return res;
	}

	@Override
	public List<BoardDTO> search(String field, String name) {
				
		return sqlSession.selectList(field, name);
			
	}

}
