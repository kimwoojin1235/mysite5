package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
		//게시판 목록
		public List<BoardVo> selectList(){
			System.out.println("BoardDao selectList");
			
			return sqlSession.selectList("board.selectList");
		}
		//1개 글 내용
		public BoardVo select(int no) {
			System.out.println("BoardDao select");

			return sqlSession.selectOne("board.select",no);
		}
		
		//조회수 증가
		public int updatehit(int no) {
			System.out.println("BoardDao updatehit");
			
			return sqlSession.update("board.updatehit", no);
		}
		
		//글 저장
		public int insert(BoardVo boardVo) {
			System.out.println("BoardDao insert");
			
			return sqlSession.insert("board.insert", boardVo);
		}
		
		//글 수정
		public int update(BoardVo boardVo) {
			System.out.println("BoardDao update");
			
			return sqlSession.update("board.update", boardVo);
		}
		
		//글 삭제
		public int delete(int no) {
			System.out.println("BoardDao delete");
			
			return sqlSession.delete("board.delete", no);
		}
}
