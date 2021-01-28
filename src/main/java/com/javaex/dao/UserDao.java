package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	//회원가입-->회원정보 저장
	public int insert(UserVo userVo) {
		System.out.println("dao insert");
		System.out.println(userVo.toString());
		int count = sqlSession.insert("user.insert",userVo);
		System.out.println("User insert count:  "+count);
		return count;
	}
	
	//로그인-->회원정보 가지고오기
	public UserVo selectUser(UserVo userVo) {
		System.out.println("user dao select");
		System.out.println(userVo.toString());
		return sqlSession.selectOne("user.selectUser", userVo);
	}
	//유저 한명의 정보를 가지고온다(수정용)
	public UserVo selectUserupdate(int no) {
		System.out.println("update");
		return sqlSession.selectOne("user.selectUserupdate", no);
	}//수정
	public int update(UserVo userVo) {
		System.out.println("updateUser");		
		return sqlSession.update("user.update", userVo);
	}
}
