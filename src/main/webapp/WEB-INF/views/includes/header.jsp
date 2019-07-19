<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib  uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

		<div id="header">
			<h1>MySite</h1>
			<ul>
				<!-- 인증여부를 Spring security로 확인하는 작업  -->
				<sec:authorize access="isAuthenticated()">
						<li><a href="${pageContext.servletContext.contextPath }/user/update">회원정보수정</a><li>
						<li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a><li>
						<li><sec:authentication property="principal.name"/>님 안녕하세요 ^^;</li>
				</sec:authorize>
				<sec:authorize access="!isAuthenticated()">
						<li><a href="${pageContext.servletContext.contextPath }/user/login">로그인</a><li>
						<li><a href="${pageContext.servletContext.contextPath }/user/join">회원가입</a><li>
				</sec:authorize>
			</ul>
		</div>