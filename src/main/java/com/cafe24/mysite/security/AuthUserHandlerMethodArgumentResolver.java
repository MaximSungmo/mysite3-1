package com.cafe24.mysite.security;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	

	@Override
	public Object resolveArgument(
		MethodParameter parameter,
		ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest,
		WebDataBinderFactory binderFactory) throws Exception {
		
		Object principal = null;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("authentication : "+authentication);
		if(authentication != null) {
			principal = authentication.getPrincipal();
			System.out.println("principal : "+principal);

		}
		System.out.println("principla class : "+principal.getClass());
		if( principal == null || principal.getClass() == String.class ) {
			
			System.out.println("return null : ");
			return null;
		}
		
		return principal;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		// @AuthUser가 안붙어 있음
		if( authUser == null ) {
			return false;
		}
		
		// 파라미터 타입이 SecurityUser가 아님
		if(parameter.getParameterType().equals( SecurityUser.class ) == false) {
			return false;
		}
		
		return true;
	}
}
