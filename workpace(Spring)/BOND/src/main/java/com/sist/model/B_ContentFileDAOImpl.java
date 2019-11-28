package com.sist.model;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class B_ContentFileDAOImpl implements B_ContentFileDAO{

	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void addFile( HashMap<String, Object> blog_contentfile) throws Exception {
	
		this.sqlSession.insert("addFile",blog_contentfile);
	}

	@Override
	public List<String> getFiles(int bc_No) {
		return this.sqlSession.selectList("getfiles", bc_No);
	}

	@Override
	public void deleteFile(int bc_No) {
		this.sqlSession.delete("deletefiles",bc_No );
		
	}


}
