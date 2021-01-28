package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	//회원가입
	public int join(UserVo userVo) {
		System.out.println("uderService join()");
		//서비스가 dao에게 값을 던진다.
		return userDao.insert(userVo);
	}
	public UserVo login(UserVo userVo) {
		System.out.println("uderService login()");
		return userDao.selectUser(userVo);
	}
	public UserVo modifyForm(int no) {
		System.out.println("uderService modifyForm");
		 
		return userDao.selectUserupdate(no);
	}
	public int modify(UserVo userVo) {
		System.out.println("uderService modify");
		//다오에게 값을 넘기고 있다.
		return userDao.update(userVo);
	}
}
