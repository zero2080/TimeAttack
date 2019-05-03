<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	window.onload=function(){
		setInterval(function(){
			opener.location.reload();
			window.close();
		},3000);
	};
</script>
</head>
<body>
	<div>
		<c:if test = "${1 eq resultMSG }">
			<h1>등록완료</h1>
		</c:if>
		<c:if test = "${0 eq resultMSG }">
			<h1>등록실패</h1>
		</c:if><br>
		<h3>3초 후 창이 닫힙니다.</h3>
	</div>
</body>
</html>