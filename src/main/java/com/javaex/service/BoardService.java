package com.javaex.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private HttpSession session;
	
	//게시판 목록
		public List<BoardVo> getList(){
			System.out.println("boardService getList");
			
			return boardDao.selectList();
			
		}
		//글 가지고 오기
		public BoardVo read(int no) {
			System.out.println("boardService read");
			
			BoardVo count = boardDao.select(no);
			boardDao.updatehit(no);
			
			return count;
		}
		//글 저장
		public int write(BoardVo boardVo) {
			System.out.println("boardService write");
			
			boardVo.setUserno(((UserVo)session.getAttribute("authUser")).getNo());
			
			return boardDao.insert(boardVo);
		}
		
		//글 수정 폼
		public BoardVo modifyForm(int no) {
			System.out.println("boardService modifyForm");
			
			return boardDao.select(no);
		}
		
		//글 수정
		public int modify(BoardVo boardVo) {
			System.out.println("boardService modify");
			
			return boardDao.update(boardVo);
		}
		
		//글 삭제
		public int delete(int no) {
			System.out.println("boardService remove");
			
			return boardDao.delete(no);
		}
}
