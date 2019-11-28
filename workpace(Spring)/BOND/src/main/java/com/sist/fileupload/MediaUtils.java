package com.sist.fileupload;

import java.util.HashMap;

import org.springframework.http.MediaType;

public class MediaUtils {

	private static HashMap<String,MediaType> midaTypeMap;
	
	// 클래스  초기화 블럭
	static {
		midaTypeMap =new HashMap<String, MediaType>();
		midaTypeMap.put("JPG", MediaType.IMAGE_JPEG);
		midaTypeMap.put("GIF", MediaType.IMAGE_GIF);
		midaTypeMap.put("PNG", MediaType.IMAGE_PNG);
	}
	
	// 파일 타입
	public static MediaType getMediaType(String fileName) {
		String formatName = getFormatName(fileName);
		return midaTypeMap.get(formatName);
	}
	
	//파일 확장자 추출
	public static String getFormatName(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") +1).toUpperCase();
	}
}
