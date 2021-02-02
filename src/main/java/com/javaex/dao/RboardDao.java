package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RboardVo;

@Repository
public class RboardDao {
	@Autowired
	private SqlSession sqlSession;
	
	//글 목록
		public List<RboardVo> selectList() {
			System.out.println("RboardDao selectList");

			return sqlSession.selectList("rboard.selectList");
		}

		// 1개 글 내용
		public RboardVo selectPost(int no) {
			System.out.println("RboardDao selectPost");

			return sqlSession.selectOne("rboard.select", no);
		}
		// 조회수 +1
		public int updateHit(int no) {
			System.out.println("RboardDao updateHit");

			return sqlSession.update("rboard.updateHit", no);
		}

		// 새글 등록
		public int insertnew(RboardVo rboardVo) {
			System.out.println("RboardDao insertNewPost");

			return sqlSession.insert("rboard.insert", rboardVo);
		}

		// 답글 등록
		public int insertRe(RboardVo rboardVo) {
			System.out.println("RboardDao insertReply");

			return sqlSession.insert("rboard.insertre", rboardVo);
		}

		// 그룹 order+1
		public int updategroup(RboardVo rboardVo) {
			System.out.println("RboardDao updateGroup");

			return sqlSession.update("rboard.updategroup", rboardVo);
		}
}
