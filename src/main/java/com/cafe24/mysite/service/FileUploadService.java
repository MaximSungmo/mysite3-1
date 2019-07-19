package com.cafe24.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
	private static final String SAVE_PATH = "/uploads"; 
	private static final String PREFIX_URL = "/uploads/images/";
	
	public String restore( MultipartFile multipartFile ) {
		
		String url = null;
		
		try {
			if( multipartFile.isEmpty() == true ) {
				return url;
			}
			
			String originalFileName = multipartFile.getOriginalFilename();
			String extName = originalFileName.substring( originalFileName.lastIndexOf( '.' ), originalFileName.length());
			// Long fileSize = multipartFile.getSize();
			String saveFileName = genSaveFileName( extName );
			
			writeFile( multipartFile, saveFileName );
			
			url = PREFIX_URL + saveFileName;
			
		} catch( IOException e ) {
			throw new RuntimeException( e );
		}
		
		return url;
	}

	private void writeFile( 
		MultipartFile multipartFile,
		String saveFileName ) throws IOException {
		
		byte[] fileData = multipartFile.getBytes();
		
		FileOutputStream fos =
			new FileOutputStream( SAVE_PATH + "/" + saveFileName );
		
		fos.write( fileData );
		fos.close();
	}
	
	private String genSaveFileName( String extName ) {
		String fileName = "";
		
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get( Calendar.YEAR );
		fileName += calendar.get( Calendar.MONTH );
		fileName += calendar.get( Calendar.DATE );
		fileName += calendar.get( Calendar.HOUR );
		fileName += calendar.get( Calendar.MINUTE );
		fileName += calendar.get( Calendar.SECOND );
		fileName += calendar.get( Calendar.MILLISECOND );
		fileName += extName;
		
		return fileName;
	}

}