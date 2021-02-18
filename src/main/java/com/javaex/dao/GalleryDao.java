package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;


@Repository
public class GalleryDao {
	@Autowired
	private SqlSession sqlsession;
	
		//리스트 셀렉트
		public List<GalleryVo> selectList(){
			System.out.println("GalleryDao.select");
			
			return sqlsession.selectList("gallery.select");
		}
		
		//파일정보 저장
		public int insertFile(GalleryVo galleryVo) {
			System.out.println("GalleryDao.insertFile");
			
			return sqlsession.insert("gallery.insertFile", galleryVo);
		}
		
		//파일정보 삭제
		public int deleteFile(int no) {
			System.out.println("GalleryDao.deleteFile");

			return sqlsession.delete("gallery.deleteFile", no);
		}
	
}
