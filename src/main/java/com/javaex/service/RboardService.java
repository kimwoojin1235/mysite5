package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RboardDao;
import com.javaex.vo.RboardVo;

@Service
public class RboardService {
	@Autowired
	private RboardDao rboardDao;
	
	//글 목록
	public List<RboardVo> getList() {
		System.out.println("RboardService getList");

		return rboardDao.selectList();
	}

	// 글 읽기
	public RboardVo read(int no) { 
		System.out.println("boardService read");
		
		RboardVo post = rboardDao.selectPost(no);

		return post;
	}
	//글 읽기 & 조회수 +1
		public RboardVo read(int no, String hit) { 
			System.out.println("RboardService read and hit up");

			if("up".equals(hit)) {	
				rboardDao.updateHit(no);	
			}
			
			return read(no);
		}	
		
		//글 등록
		public int write(RboardVo rboardVo) {
			System.out.println("RboardService write");
			
			if(rboardVo.getGroupno() > 0) {
				
				rboardVo.setOrderno(rboardVo.getOrderno()+1);
				rboardVo.setDepth(rboardVo.getDepth()+1);
				
				rboardDao.updategroup(rboardVo);
				
				return rboardDao.insertRe(rboardVo);
				
			}else {
				
				return rboardDao.insertnew(rboardVo);
				
			}
		}
}
