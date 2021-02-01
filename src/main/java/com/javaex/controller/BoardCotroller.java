package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value="/board")
public class BoardCotroller {
	@Autowired
	private BoardService boardService;
	
	//글 목록
		@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST} )
		public String getList(Model model){
			System.out.println("getList");
			
			model.addAttribute("boardList", boardService.getList());
			
			return "board/list";
		}
		
		//글 읽기
		@RequestMapping(value="/read", method= {RequestMethod.GET, RequestMethod.POST} )
		public String read(@RequestParam("no") int no, Model model) {
			System.out.println("read");
			
			model.addAttribute("count", boardService.read(no));
			
			return "board/read";
		}
		
		//글 쓰기 폼
		@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST} )
		public String writeForm() {
			System.out.println("writeForm");
			
			return "board/writeForm";
		}
		
		//글 저장
		@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST} )
		public String write(@ModelAttribute BoardVo boardVo) {
			System.out.println("write");
			
			boardService.write(boardVo);
			
			return "redirect:/board/list";
		}
		
		//글 수정 폼
		@RequestMapping(value="/modifyForm", method= {RequestMethod.GET, RequestMethod.POST} )
		public String modifyForm(@RequestParam("no") int no, Model model) {
			System.out.println("modifyForm");
			
			model.addAttribute("count", boardService.modifyForm(no));
			
			return "board/modifyForm";
		}
		
		//글 수정
		@RequestMapping(value="/modify", method= {RequestMethod.GET, RequestMethod.POST} )
		public String modify(@ModelAttribute BoardVo boardVo) {
			System.out.println("modify");
			
			boardService.modify(boardVo);
			
			return "redirect:/board/list";
		}
		
		@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST} )
		public String remove(int no) {
			System.out.println("delete");
			
			boardService.remove(no);
			
			return "redirect:/board/list";
		}

}
