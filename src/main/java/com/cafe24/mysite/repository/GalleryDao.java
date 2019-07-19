package com.cafe24.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int delete( Long no ) {
		return sqlSession.delete( "gallery.delete", no );
	}
	
	public List<GalleryVo> getList() {
		return sqlSession.selectList( "gallery.getList" );
	}
	
	public int insert( GalleryVo galleyVo ) {
		return sqlSession.insert( "gallery.insert", galleyVo );
	}
}
