package com.sist.model;

import java.util.HashMap;
import java.util.List;

public interface CategoryDAO {
	
	//카테고리리스트 가져오기
	public List<CategoryDTO> getCategory();
	
	//카테고리 넣기
	public void create_B_Category(HashMap<String, Object>  blog_Category);

	public void modify_B_Category(HashMap<String, Object> blog_Category);
	
	public int blog_Category(int b_No);
}
