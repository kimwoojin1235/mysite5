package com.javaex.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestDao {
	@Autowired
	private SqlSession sqlSession;
	//리스트
	public List<GuestVo> getList() {
		System.out.println("list들어옴");
		return sqlSession.selectList("guestbook.selectlist");
	}
	//저장
	 public int guestinsert(GuestVo gVo) {
		 System.out.println("inserrt");
		return sqlSession.insert("guestbook.guestinsert", gVo);
	 }
	 //글삭제
	 public int guestdelete(GuestVo gVo) {
		 System.out.println("delete");
		 return sqlSession.delete("guestbook.delete", gVo);
	 }
	 //글저장(selectkye)
	 public void insertSelectkye(GuestVo guestVo) {
		 System.out.println("dao.insertSelectkye()");
		 System.out.println("dao실행전="+guestVo);//실행전에는 값이 없음
		 sqlSession.insert("guestbook.insertSelectkye",guestVo);
		 System.out.println("dao실행후="+guestVo);//싱행후에는 값(no)이 들어옴
		 //return guestVo.getNo();
	 }
	 //글 1개 가지고 오기
	 public GuestVo selectOne(int no) {
		 System.out.println("dao.selectOne()");
		 return sqlSession.selectOne("guestbook.select",no);
	 }
	 
}