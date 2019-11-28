package com.sist.model;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	@Override
	public List<CategoryDTO> getCategory() {//카테고리 리스트 가져오기
		return this.sqlSession.selectList("get_Category");
		
	}

	@Override
	public void create_B_Category(HashMap<String, Object>  blog_Category) { //카테고리 넣기
			this.sqlSession.insert("create_Category",blog_Category);
		}

	@Override
	public void modify_B_Category(HashMap<String, Object> blog_Category) {
		this.sqlSession.update("update_Category",blog_Category);
		
	}

	@Override
	public int blog_Category(int b_No) {
		return this.sqlSession.selectOne("blog_Category",b_No);
	}
	

		
	}


