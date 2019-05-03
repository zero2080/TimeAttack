<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#b_wrap{
		text-align:center;
	}
</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<section>
		<div id="b_wrap">
			<h2><a href="${conPath }/gameRecodManagement.do">게임정보관리</a></h2>		
			<h2><a href="${conPath }/recordConfirmView.do">기록승인관리</a></h2>
		</div>
	</section>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>