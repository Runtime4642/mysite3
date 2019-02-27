package com.douzone.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileuploadService {
	
	//저장은 프로젝트 안에다가 하는것이 아니다. 
	private static final String SAVE_PATH= "/uploads";
	
	
	private static final String URL= "/uploads/images";
	
	
	//현재는 프로필 업로드만 하게 해놓음
	public String restore(MultipartFile multipartFile,boolean profile) {
		String url ="";
		try {
		//비어 있는지 검사
		if(multipartFile.isEmpty()){
			return url;
		}
		//정보 빼오기
		String originalFileName = multipartFile.getOriginalFilename();
		long fileSize=multipartFile.getSize();
		

		//파일 확장자 이름 가져오기 ex ) save.jpg => jpg
		String extName = originalFileName.substring(originalFileName.lastIndexOf('.')+1);
		
		String saveFileName;
		//저장할 파일 이름
		if(profile) {
		saveFileName = "profile."+extName;
		}
		else{
			saveFileName=generateSaveFileName(extName);
		}
		
		byte[] fileData = multipartFile.getBytes();
		OutputStream os = new FileOutputStream(SAVE_PATH+"/"+saveFileName);
		os.write(fileData);
		os.close();
		
		url = URL + "/" + saveFileName;
		}catch(IOException ex)
		{
			new RuntimeException("upload fail");
		}
		return url;
		
	}

	private String generateSaveFileName(String extName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += "."+extName;
		return fileName;
	}

}
