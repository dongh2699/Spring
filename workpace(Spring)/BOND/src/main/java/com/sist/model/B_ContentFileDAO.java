package com.sist.model;

import java.util.HashMap;
import java.util.List;

public interface B_ContentFileDAO {

	public void addFile( HashMap<String, Object> blog_contentfile) throws Exception;
	
	public List<String> getFiles(int bc_No);

	public void deleteFile(int bc_No);

	
	
	
}
