package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

import com.javaex.dao.UserDao;
import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userservice; 
	//회원가입 폼
	@RequestMapping (value = "/joinForm" ,method = {RequestMethod.GET,RequestMethod.POST})
	public String joinForm() {
		System.out.println("/user/joinForm");
		return "user/joinForm";
	}
	//회원가입
	@RequestMapping(value = "/join", method = {RequestMethod.GET,RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("/user/join");
		System.out.println(userVo.toString());
		int count= userservice.join(userVo);//dao 가 아닌 서비스에 넘긴다.
		return "user/joinok";
	}
	//로그인폼
	@RequestMapping(value = "/loginForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String loginForm() {
		System.out.println("/user/loginForm");
		return"/user/loginForm";
	}
	//로그인
	@RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("/user/login");
		System.out.println(userVo.toString());
		UserVo authUser = userservice.login(userVo);
		//로그인 성공
		if(authUser != null) {//로그인 성공
			session.setAttribute("authUser", authUser);
			return"redirect:/main";
		}else {
			//로그인 실패
			return "redirect:/user/loginForm?result=fail";
		}
		
	}
	//회원정보 수정폼
	@RequestMapping(value = "/modifyForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm(Model model, HttpSession session) {
		System.out.println("/user/modifyForm");
		//세션애서 no값가지고 오기
		int no = ((UserVo)session.getAttribute("authUser")).getNo();
		//세선값이 없으면 -->로그인
		//회원정보 가지고 오기
		UserVo userVo= userservice.modifyForm(no);
		/*
		UserVo authUser =(UserVo)session.getAttribute("authUser");
		authUser =userDao.selectUserupdate(authUser.getNo());
		*/
		//jsp에 데이터 보내기
		model.addAttribute("userVo",userVo);
		return "/user/modifyForm";
	}
	
	
	//회원정보 수정
	@RequestMapping(value = "/modify", method = {RequestMethod.GET,RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("/user/modify");
		UserVo authUser = (UserVo)session.getAttribute("authUser");	
		//세션에서 값 가지고 오기
		int no = authUser.getNo();
		//vo에 no를 준다
		userVo.setNo(no);
		int count = userservice.modify(userVo);
		authUser.setName(userVo.getName());	
		return "redirect:/main";
	}
	
	
	//로그아웃
	@RequestMapping(value = "/logout", method = {RequestMethod.GET,RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("/user/logout");
		
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/main";
	}
	//회원가입에서 id체크
	@ResponseBody//리턴형을 포워드가 아니라데이터로만 본다.
	@RequestMapping(value ="/idcheck", method = {RequestMethod.GET,RequestMethod.POST})
	public String idcheck(@RequestParam("id") String id) {//@ModelAttributet 는 양이 많아지면 사용
		//패스워드는 테스트용
		System.out.println("/user/idcheck");
		System.out.println("id="+id);
		
		String result = userservice.idcheck(id);
		System.out.println(result);
	    return result;
		//return "redirect:/user/joinForm?result="+result;
		//return result; 
		// "result"로 리턴을 하면 /WEB-INF/views/result.jsp를 찾게된다.즉 포워드를 하게됨
		//@ResponseBody-->respons에 body영역의 데이터만 보냄
	}
	
}
