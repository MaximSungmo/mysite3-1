package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.GalleryDao;
import com.cafe24.mysite.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao galleryDao;
	
	public boolean deleteImageInformation( Long no ) {
		return 1 == galleryDao.delete( no );
	}
	
	public boolean saveImageInformation( GalleryVo galleyVo ){
		return 1 == galleryDao.insert( galleyVo );
	}
	
	public List<GalleryVo> getGalleryList() {
		return galleryDao.getList();
	}
}