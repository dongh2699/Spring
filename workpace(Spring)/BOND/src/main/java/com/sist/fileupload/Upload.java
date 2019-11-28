package com.sist.fileupload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

//Form방식으로 작동하는 Upload 로직
@Service
public class Upload {
	public ArrayList<String> fileUpload(MultipartHttpServletRequest mRequest) {
		ArrayList<String> upload_Paths = new ArrayList<String>();
		String uploadPath = "C:/NCS/workspace(spring)/BOND/src/main/webapp/resources/upload";
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int date = cal.get(Calendar.DATE);
		Iterator<String> iterator = mRequest.getFileNames();
		while(iterator.hasNext()) {
			String uploadFileName = iterator.next();

			// 톰캣에 있는 임시파일임.
			MultipartFile mFile =  mRequest.getFile(uploadFileName);
			
			// 실제 파일명 - 하지만 여전히 임시적으로 저장되는 파일이다.
			String originFileName =  mFile.getOriginalFilename();
			
			// 물리적으로 저장할 필요가 있습니다.
			// "C:/uploadFile/2019-10-01";
			String homedir = uploadPath;
			
			File path1 = new File(homedir);
			if(!path1.exists()) {
				path1.mkdirs();		// 실제 폴더가 만들어 짐.
			}
			// 파일을 만들자
			String saveFileName = originFileName;
			if(saveFileName!=null&&!saveFileName.equals("")) {
				if(new File(homedir+"/"+saveFileName).exists()) {
					saveFileName = saveFileName+"_"+System.currentTimeMillis();
				}
				try {
					mFile.transferTo(new File(homedir+"/"+year+"-"+month+"-"+date+saveFileName));
					uploadPath=year+"-"+month+"-"+date+saveFileName;
					upload_Paths.add(uploadPath);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		} // while 문 end
		return upload_Paths;
	}
}
