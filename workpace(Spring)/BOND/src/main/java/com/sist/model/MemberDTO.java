package com.sist.model;

import java.util.List;

import lombok.Data;

@Data
public class MemberDTO {
	 private String m_Email;

	    private String m_Pw;

	    private String m_Name;

	    private String m_Nickname;

	    private String m_Birth;

	    private String m_Phone;

	    private String m_Picture;
	    
	    private List<Blog_contentDTO> blog_contentDTO;
	    
	    private List<Content_CommentDTO> comments;

		
}
