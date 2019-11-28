package com.sist.service;

import java.util.List;

public interface B_ContentFileService {

	
	public void addFile(String fullName);

	public List<String> getFiles(int bc_No);
}
