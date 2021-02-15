package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;
@Service
public class GuestService {
	@Autowired
	private GuestDao guestDao;
	//리스트
	public List<GuestVo> addList(){
		System.out.println("addList");
		
		return guestDao.getList();
	}
	//글저장
	public int insert(GuestVo guestVo) {
		System.out.println("add");
		
		return guestDao.guestinsert(guestVo);
	}
	//글 삭제
	public int delete(GuestVo guestVo) {
		System.out.println("delete");
		
		return guestDao.guestdelete(guestVo);
	}
	//ajax 글저장-->저장된글 리턴
	public GuestVo writeResultVo(GuestVo guestVo) {
		//글저장-->번호
		//int no = guestDao.insertSelectkye(guestVo);
		System.out.println("sevice: dao.insertselect실행전="+guestVo);//값을 담아서 보냈다
		guestDao.insertSelectkye(guestVo);
		System.out.println("sevice: dao.insertselect실행후="+guestVo);//값을 가지고 왔다.
		int no= guestVo.getNo();
		//글 가져오기
		GuestVo gVo = guestDao.selectOne(no);//gVo 안에 담았다.
		return gVo;//담아져 있는 걸 리턴시킨다.

		
		//방금 저장한글 조회하기
		//글번호로 가지고 온다
		//최근글을 가지고 온다.저장하고 최근글이 방금저장을한 글이 아닐수도 있음
		//해결방법-->
	}
}
