package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/rboard")
public class Rboardcontroller {
	@Autowired
	private RboardService rboardService;
	
	// 댓글 목록
		@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
		public String getList(Model model) {
			System.out.println("RboardController getList");

			model.addAttribute("rList", rboardService.getList());

			return "rboard/list";
		}
		//댓글 읽기
		@RequestMapping(value="/read", method= {RequestMethod.GET ,RequestMethod.POST})
		public String read(@RequestParam("no") int no,
							Model model) {
			System.out.println("컨트롤러 read");
			//게시물 정보 받아옴
			
			model.addAttribute("select", rboardService.read(no));
			
			return "rboard/read";
		}	
		//글쓰기폼
		@RequestMapping(value="/writeForm", method= {RequestMethod.GET ,RequestMethod.POST})
		public String writeForm() {
			System.out.println("컨트롤러 writeForm");
				
				
			return "rboard/writeForm";
		}
		
		
		//새글쓰기
		@RequestMapping(value="/write", method= {RequestMethod.GET ,RequestMethod.POST})
		public String write(@ModelAttribute RboardVo rboardVo,HttpSession session) {
			System.out.println("컨트롤 write");
			
			
			//session은 웹의 개념이라서 controller까지 범위에서 사용하는편이 좋음
			int no = ((UserVo)session.getAttribute("authUser")).getNo();		
			rboardVo.setUserno(no);		
			System.out.println("user_no값이 잘 들어갔는지 확인"+rboardVo);
			
			
			rboardService.write(rboardVo);
									
			return "redirect:/rboard/list";

		}
}
