package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FlieUploadService;

@Controller
@RequestMapping("/fileup")
public class FileUploadController {
	@Autowired
	private  FlieUploadService fiieupload;
	
	//파일등록폼
	@RequestMapping(value = "/form", method = {RequestMethod.GET,RequestMethod.POST})
	public String form() {
		System.out.println("FileUploadController.form");
		
		return "/fileUpload/form";
	}
	//파일 업로드
	@RequestMapping(value = "/upload", method = {RequestMethod.GET,RequestMethod.POST})
	public String upload(@RequestParam("file") MultipartFile file,Model model) {
		//MultipartFile은 첨부가된 파일을 보내는 역활을 한다.
		System.out.println("FileUploadController.upload");
		
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		String saveName = fiieupload.retore(file);
		model.addAttribute("saveName",saveName);
		return "/fileUpload/result";
	}
	
	
}
