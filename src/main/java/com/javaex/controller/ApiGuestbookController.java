package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping(value = "/api/guestbook")
public class ApiGuestbookController {
	@Autowired
	private GuestService guestService;
	
	//전체리스트 가지고 오기
	@ResponseBody
	@RequestMapping(value = "/list")
	public List<GuestVo> List() {
		System.out.println("[ApiGuestbookController] /list");

		return guestService.addList();
	}
	

	//글작성(ajax)
	@ResponseBody
	@RequestMapping(value = "/write")
	public GuestVo write(@ModelAttribute GuestVo guestVo) {
		System.out.println("[ApiGuestbookController] /write");
		System.out.println(guestVo);
		//입력된vo 값을 전달하고 저장 vo를 받아야함
		//온 값을 vo안에 담는다.
		return guestService.writeResultVo(guestVo); //자동적으로 json으로 변환이 되어 리턴
	}
	//글작성(ajax --json)
		@ResponseBody
		@RequestMapping(value = "/write2")
		public GuestVo write2(@RequestBody GuestVo guestVo) {//요청문서의 바디의 있음
			System.out.println("[ApiGuestbookController] /write2");
			System.out.println(guestVo);
			//입력된vo 값을 전달하고 저장 vo를 받아야함
			//온 값을 vo안에 담는다.
			return guestService.writeResultVo(guestVo); //자동적으로 json으로 변환이 되어 리턴
		}
	//글삭제(ajax)
	@ResponseBody
	@RequestMapping(value = "/remove")
	public int remopve(@ModelAttribute GuestVo guestVo) {
		System.out.println("[ApiGuestbookController] /remove");
		System.out.println(guestVo);
		
		int conut =  guestService.delete(guestVo);
		System.out.println(conut);
		return conut;
	}
}
