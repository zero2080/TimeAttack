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
	#modi_wrap a{
		text-align:center;
	}
</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
		<section>
			<div id="modi_wrap">
				<h1><a href="${conPath }/recordManager.do">개인기록 관리</a></h1>
				<h1><a href="${conPath }/memberModify.do">회원정보 수정</a></h1>
				<h1><a href="${conPath }/quitMember.do">회원탈퇴</a></h1>
			</div>
		</section>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>