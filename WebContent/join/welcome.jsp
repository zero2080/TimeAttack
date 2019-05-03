<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#wrap{
		margin:0 auto;
	}
	h1, h2, h3{
		text-align:center;
	}
</style>
<script>
	window.onload=function(){
		setInterval(function() {
			location.href="${conPath}/main.do";
		}, 3000);
	};
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
		<div id="wrap">
			<h1>회원가입을 축하합니다~</h1>
			<h2>축하 기념으로 1000포인트를 드립니다.</h2>
			<h3>3초 후 메인으로 이동합니다.</h3>			
		</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>