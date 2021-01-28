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

	public List<GuestVo> getList() {
		System.out.println("list들어옴");
		return sqlSession.selectList("guestbook.selectlist");
	}
	 public int guestinsert(GuestVo gVo) {
		 System.out.println("inserrt");
		return sqlSession.insert("guestbook.guestinsert", gVo);
	 }

	 public int guestdelete(GuestVo gVo) {
		 System.out.println("delete");
		 return sqlSession.delete("guestbook.delete", gVo);
	 }
}