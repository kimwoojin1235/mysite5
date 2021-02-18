package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FlieUploadService {

	public String retore(MultipartFile flie) {
		System.out.println("FlieUploadService.retore");
		System.out.println(flie.getOriginalFilename());
		
		//db저장할 정보 수집
		String saveDir= "C:\\javaStudy\\upload";
		//오리지널 파일 이름
		String orgname= flie.getOriginalFilename();
		System.out.println("orgname="+orgname);
		//확장자
		String exName = orgname.substring(orgname.lastIndexOf("."));
		System.out.println("exName="+exName);
		//서버 저장파일 이름(이름이 중복이 되면 안된다.)
		String saveName =System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
		System.out.println("saveName="+saveName);
		//서버 파일패스-->저장경로
		String fliePath =saveDir + "\\"+saveName;
		System.out.println("fliePath="+fliePath);
		
		//파일사이즈
		long flieSize =flie.getSize();
		System.out.println("flieSzie="+flieSize);
		
		//서버 하드디스크에 파일 저장
		try {
			byte[] flieData = flie.getBytes();
			OutputStream out = new FileOutputStream(fliePath);
			BufferedOutputStream bos = new BufferedOutputStream(out); 
			bos.write(flieData);
			bos.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return saveName;
	}
}
