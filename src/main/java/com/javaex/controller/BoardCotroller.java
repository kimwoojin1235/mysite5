package com.javaex.controller;

import java.util.List;
import java.util.Map;

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
		
		//글목록2 (검색기능 추가)
		@RequestMapping(value = "/list2", method= {RequestMethod.GET, RequestMethod.POST})
		public String list2(@RequestParam(value = "keyword",required = false,defaultValue = "" )
							String keyword, 
							
							Model model) {
			//value인 keyword가 없다면 ""으로 처리 한다.값이 항상 있는 것은 아니기 때문에defaultValue를 넣는다.
			//keyword가 있으면 값을 주고 없다면은 ""을 주라는 의미
			System.out.println("List2");

			List<BoardVo> boardList = boardService.getBoardList2(keyword);
			//getBoardList2에 키워드를 준다. 값이  null은 아니다
			model.addAttribute("boardList", boardService.getBoardList2(keyword));
			
			return "board/list2";
		}
		//글목록3 (검색기능+페이징)
		@RequestMapping(value = "/list3", method= {RequestMethod.GET, RequestMethod.POST})
		public String list3(@RequestParam(value = "keyword",required = false,defaultValue = "" )
							String keyword,
							@RequestParam(value = "crtPage",required = false,defaultValue = "1" )
							int crtPage,
							Model model) {
			//value인 keyword가 없다면 ""으로 처리 한다.값이 항상 있는 것은 아니기 때문에defaultValue를 넣는다.
			//keyword가 있으면 값을 주고 없다면은 ""을 주라는 의미
			System.out.println("List3");
			//System.out.println("keyword="+keyword);
		//	System.out.println("crtPage="+crtPage);
			Map<String, Object> pMap = boardService.getBoardList3(keyword,crtPage );
			System.out.println("pMap="+pMap);	
			model.addAttribute("pMap", pMap);
			
			return "board/list3";
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
		public String delete(int no) {
			System.out.println("delete");
			
			boardService.delete(no);
			
			return "redirect:/board/list";
		}

}
