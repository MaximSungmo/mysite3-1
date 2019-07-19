package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mysite.service.FileUploadService;
import com.cafe24.mysite.service.GalleryService;
import com.cafe24.mysite.vo.GalleryVo;

@Controller
@RequestMapping( "/gallery" )
public class GalleryController {

	@Autowired
	private GalleryService galleryService;	
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping( "" )
	public String index( Model model ){
		model.addAttribute( "list", galleryService.getGalleryList() );
		return "gallery/index";
	}

	@RequestMapping( "/delete/{no}")
	public String delete( @PathVariable( "no" ) Long no ) {
		galleryService.deleteImageInformation( no );
		return "redirect:/gallery";
	}
	
	@RequestMapping( value="/upload", method=RequestMethod.POST )
	public String upload( 
		@ModelAttribute GalleryVo galleryVo,
		@RequestParam( "file" ) MultipartFile multipartFile ) {
		
		String image_url = fileUploadService.restore( multipartFile );
		galleryVo.setImage_url(image_url);

		galleryService.saveImageInformation( galleryVo );
		
		return "redirect:/gallery";
	}
}