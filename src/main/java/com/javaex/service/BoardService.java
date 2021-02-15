package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

import oracle.net.aso.i;
import oracle.security.crypto.cert.ext.NameConstraintsExtension;

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
		//리스트 (리스트+검색추가)
		public List<BoardVo> getBoardList2(String keyword){
			//서비스 입장에서는 항상 값이 있음
			System.out.println("boardService getBoardList2");
			
			
			List<BoardVo> boardList = boardDao.selectList2(keyword);
			return boardList;
		}
		
		//리스트 (리스트+검색추가)
		public Map<String, Object> getBoardList3(String keyword,int crtPage){
			//서비스 입장에서는 항상 값이 있음
			System.out.println("boardService getBoardList3");
			
			//한패이지당 글이 10개 crtPage-->시작~끝  1-->1~10 2-->11~20  3-->21~30 4-->31~40
			
			//페이지당 글갯수
			int listCnt = 10;
			//현재 페이지
			crtPage=(crtPage > 0)? crtPage : (crtPage=1);
			//시작글 번호 startRnum 1page -->1 2page-->11
			int startRnum = (crtPage-1)*listCnt+1;
			//끝글 번로endRnum 1page-->1~10
			int endRnum = (startRnum +listCnt)-1;
			List<BoardVo> boardList = boardDao.selectList3(keyword,startRnum,endRnum);
			
			//페이징 계산
			//페이지당 버튼 겟수
			int pageBtnCount=5;
			
			//전체 글갯수 구하기
			int totalCount =boardDao.selectTotalCut(keyword);
			
			//1--> 1~5 2-->1~5 3-->1~5 4-->1~5 5-->1~5 6-->6~10 7-->6~10
			 
			
			//마지막 버튼 번호
			int endPageBtnNo=(int)Math.ceil(crtPage/(double)pageBtnCount) * pageBtnCount; // (1/5)*5-->0*5-->0
										//    1/5.0-->0.2-->1.0-->1*5-->5
			//시작 버튼 번호
			int startPageBtnNo=endPageBtnNo-(pageBtnCount-1);
			//다음버튼 boolean
			boolean next;
			
			if(endPageBtnNo*listCnt<totalCount) {//5*10 < 51
				next = true;
			}else {								//5*19 < 35
				next =false;
				endPageBtnNo = (int)Math.ceil(totalCount/(double)listCnt);
				//totalCount에서listCnt를 나누어 값이 없다면은 나오지 않는다.
			}
			//이전버튼 boolean
			boolean prev;
			if(startPageBtnNo !=1) {
				prev = true;
			}else {
				prev =false;
			}
			//prev,startPageBtnNo,endPageBtnNo,next,boardList-->jsp전달
			Map<String, Object> pMap = new HashMap<String, Object>();
			pMap.put("boardList", boardList);
			pMap.put("prev", prev);
			pMap.put("startPageBtnNo", startPageBtnNo);
			pMap.put("endPageBtnNo", endPageBtnNo);
			pMap.put("next", next);
			
			return pMap;
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
			System.out.println(boardVo);
			
			for(int i=1 ; i<=1234;i++) {
				boardVo.setTitle(i+"번재 글 제목입니다");
				boardVo.setContent(i+"번째 글내용입니다.");
				boardVo.setUserno(21);
				boardDao.insert(boardVo);
			}
			System.out.println(boardVo);
			return 1;
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
