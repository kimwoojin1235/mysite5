package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserDao userDao;
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
		int count= userDao.insert(userVo);
		System.out.println("userController count "+ count);
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
		UserVo authUser = userDao.selectUser(userVo);
		
		//로그인 성공
		if(authUser != null) {//로그인 성공
			session.setAttribute("authUser", authUser);
			return"redirect:/main";
		}else {
			//로그인 실패
			//로그인 -->result = fail
			return "redirect:/user/loginForm?result=fail";
		}
		
	}
	//회원정보 수정폼
	@RequestMapping(value = "/modifyForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm(Model model, HttpSession session) {
		System.out.println("/user/modifyForm");
		UserVo authUser =(UserVo)session.getAttribute("authUser");
		authUser =userDao.selectUserupdate(authUser.getNo());
		model.addAttribute("userVo",userDao.selectUserupdate(authUser.getNo()));
		return "/user/modifyForm";
	}
	
	
	//회원정보 수정
	@RequestMapping(value = "/modify", method = {RequestMethod.GET,RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("/user/modify");
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		userVo.setNo(authUser.getNo());
		
		userDao.update(userVo);
		
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
	
}
