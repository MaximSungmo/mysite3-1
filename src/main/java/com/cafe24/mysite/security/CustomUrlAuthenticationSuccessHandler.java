package com.cafe24.mysite.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.cafe24.mysite.dto.JSONResult;



public class CustomUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
    	
    	SavedRequest savedRequest = requestCache.getRequest( request, response );
        
        if ( savedRequest != null ) {
            requestCache.removeRequest( request, response );
            clearAuthenticationAttributes( request );
        }

		String accept = request.getHeader( "accept" );
    	
		SecurityUser securityUser = null;
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal != null && principal instanceof UserDetails) {
				securityUser = (SecurityUser) principal;
			}
		}
		
		// 웹 요청인지 앱 요청인 지 확인하는 것 
    	if( accept == null || accept.matches( ".*application/json.*" ) == false ) {
    		
    		request.getSession(true).setAttribute("loginNow", true);
    		// 메인으로 페이지 이동
            getRedirectStrategy().sendRedirect( request, response, "/");
//    		getRedirectStrategy().sendRedirect( request, response, "/" );
    		return;
    	}
    	
    	MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
    	MediaType jsonMimeType = MediaType.APPLICATION_JSON;
		
    	JSONResult jsonResult = JSONResult.success(securityUser);
    	if( jsonConverter.canWrite( jsonResult.getClass(), jsonMimeType ) ) {
        	jsonConverter.write( jsonResult, jsonMimeType, new ServletServerHttpResponse( response ) );
    	}
    }

    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}