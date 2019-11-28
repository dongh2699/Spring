package com.sist.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sist.model.B_ContentDAO;
import com.sist.model.B_ContentFileDAO;

@Service
public class B_ContentFileServiceImpl implements B_ContentFileService{

	private final B_ContentFileDAO b_ContentFileDAO;

	@Inject
	public B_ContentFileServiceImpl(B_ContentFileDAO b_ContentFileDAO) {
		this.b_ContentFileDAO=b_ContentFileDAO;
	}

	@Override
	public void addFile(String fullName)  {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getFiles(int bc_No) {
		return b_ContentFileDAO.getFiles(bc_No); 		
	}

	
	
	
}
