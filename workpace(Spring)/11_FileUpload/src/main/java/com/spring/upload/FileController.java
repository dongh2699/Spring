package com.spring.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileController {
	
	@Autowired
	private Upload upload;
	
	@RequestMapping("/upload.do")
	public String upload() {
		return "uploadForm";
	}
	
	@RequestMapping(value="/upload_ok.do", method=RequestMethod.POST)
	public ModelAndView upfile(MultipartHttpServletRequest mRequest) {
		
		ModelAndView mav = new ModelAndView();
		if(this.upload.fileUpload(mRequest)) {
				mav.addObject("result", "파일 업로드 성공");
		} else {
			mav.addObject("result", "파일 업로드 실패");
		}
		
		mav.setViewName("uploadResult");
		
		
		return mav;
	}
	
	
	
	
}
