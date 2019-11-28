package com.spring.upload;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

//해당 Upload라는 클래스는 비지니스 로직을 수행하는 클래스이다.
@Service
public class Upload {

public boolean fileUpload(MultipartHttpServletRequest mRequest) {
   
   boolean isUpload = false;
   
   String uploadpath = "C:/uploadFile/";
   
   Calendar cal = Calendar.getInstance();
   
   int year= cal.get(Calendar.YEAR);
   int month=cal.get(Calendar.MONTH) +1;
   int date = cal.get(Calendar.DATE);
   
   Iterator<String> iterator=mRequest.getFileNames();
   while(iterator.hasNext()) {
      String uploadFileName=iterator.next();
      
      //톰캣에 있는 임시 파일임.
      MultipartFile mFile=mRequest.getFile(uploadFileName);
      // 실제 파일명-하지만 여전히 임시적으로 저장되는 파일임.
      String originFileName=mFile.getOriginalFilename();
      
      //물리적으로 저장할 필요가 있습니다.
      // "C:uploadFile/2019-10-01"
      String homedir=uploadpath+year+"-"+month+"-"+date;
      
      File path1 = new File(homedir);
      if(!path1.exists()) {
         path1.mkdirs(); //실제 폴더가 만들어짐.
      }
      //파일을 만들자
      String saveFilenName=originFileName;
      
      if(saveFilenName!=null && !saveFilenName.equals("")) {//파일이 존재하면
         if(new File(homedir+"/"+saveFilenName).exists()) { //파일이 중복이 된다면
            saveFilenName = saveFilenName+"_"+System.currentTimeMillis();
         }
         
         try {
            mFile.transferTo(new File(homedir+"/"+saveFilenName));
            isUpload =true;
         } catch (IllegalStateException e) {
            
            e.printStackTrace();
            isUpload=false;
         } catch (IOException e) {
            
            e.printStackTrace();
            isUpload=false;
         }
      }
   } //while문 end
   
   return isUpload;
}
}
