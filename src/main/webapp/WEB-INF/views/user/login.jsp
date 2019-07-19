<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib  uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<sec:csrfMetaTags/>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
var csrfParameter = $('meta[name="_csrf_parameter"]').attr('content');
var csrfHeader = $('meta[name="_csrf_header"]').attr('content');
var csrfToken = $('meta[name="_csrf"]').attr('content');
console.log(csrfParameter + ":" + csrfHeader + ":" + csrfToken)


$(function(){
	$('login-form2').submit(function(event){
		//submit의 기본 동작을 불가상태로 변경 
		event.preventDefault();
		var params = "email=" + $('#email').val() +"%password="+ $('#password').val();
		$.ajax({
			url:"${pageContext.request.contextPath}/user/auth",
			type:"post",
			contentType:"application/json", //Post 방식 json
			dataType:"json",
			data: params,
			success:function(response){
				console.log(response);
				if(response.result != "success"){
					console.error(response.message);
					return
				}		
				// li 엘리먼트 삭제
				$("li[data-no='"+response.data+"']").remove();
				dialogDelete.dialog('close');
			},
			error: function(jqXHR, status, e){
				console.error(status + ":" + e);			
			}
		});
	})
})

</script>



</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">
				<form id="login-form" name="loginform" method="post" action="${pageContext.servletContext.contextPath }/user/auth">
					<sec:csrfInput />
					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="">
					<label class="block-label" >패스워드</label>
					<input id="password" name="password" type="password" value="">
					<label class="block-label" >자동로그인</label>
					<input name="remember-me" type="checkbox">
					<c:if test='${result == "fail" or param.result == "fail" }'>
						<p>
							로그인이 실패 했습니다.
						</p>
					</c:if>
					<input type="submit" value="로그인">
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>