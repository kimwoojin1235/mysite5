package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;


@Controller
@RequestMapping(value = "/guest")
public class GuestController {
	@Autowired
	private GuestService gservice;
	
	@RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
	public String List(Model model) {
		System.out.println("list");
		model.addAttribute("glist",gservice.addList());
		return "/guestbook/AddList";
	}
	@RequestMapping(value = "/write ",method = {RequestMethod.GET,RequestMethod.POST})
	public String write(@ModelAttribute GuestVo guestVo) {
		//@ModelAttribute를 사용하여서 vo의 값을가져온뒤 입력을함
		//리스트와 같이 있있어Model을 사용할 필요x
		System.out.println("등록");
		System.out.println(guestVo.toString());
		gservice.insert(guestVo);
		
		return "redirect:/guest/list";
	}

	@RequestMapping(value = "/deleteForm",method = {RequestMethod.GET,RequestMethod.POST})
	public String deleteForm() {
		System.out.println("삭제 폼입니다.");
		//삭제 창으로 보내준다.
		return "/guestbook/DeleteForm";
	}
	@RequestMapping(value = "/delete",method = {RequestMethod.GET,RequestMethod.POST})
	public String delete(@ModelAttribute GuestVo guestVo) {
		System.out.println("삭제입니다");
		//model은 포워드만을 하기 위한 것이므로 리다이렉트를 하는 이런경우에는 어울리지 않는다.
		int delete = gservice.delete(guestVo);
		if(delete==1) {	//성공
			return "redirect:/guest/AddList";
		}else {//실패
			//실패시에는 삭제창으로 리다이렉트 시키는 것이 나은 방식이다.
			return "redirect:/guest/DeleteForm?result=fail&no="+guestVo.getNo();
		}
	}
}
