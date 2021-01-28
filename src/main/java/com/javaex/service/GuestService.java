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
	
	public List<GuestVo> addList(){
		System.out.println("addList");
		
		return guestDao.getList();
	}
	public int insert(GuestVo guestVo) {
		System.out.println(" add");
		
		return guestDao.guestinsert(guestVo);
	}
	
	public int delete(GuestVo guestVo) {
		System.out.println(" delete");
		
		return guestDao.guestdelete(guestVo);
	}
}
