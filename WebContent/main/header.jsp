<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/header.css" rel="stylesheet" type="text/css">
</head>
<body id="header">
	<header>
		<div id="gnb">
			<div class="left">
				<ul>
					<li><a href="${conPath }/gameList.do">Game</a></li>
					<li><a href="${conPath }/freeBoard.do">Community</a></li>
					<li><a href="${conPath }/notice.do">Notice</a></li>
				</ul>
			</div>
			<c:if test="${empty member and empty admin }">
				<div class="right">
					<ul>
						<li><a href="${conPath }/joinForm.do">Join us</a></li>
						<li><a href="${conPath }/loginForm.do">Log in</a></li>
					</ul>
				</div>
			</c:if>
			<c:if test="${not empty member and empty admin }">
				<div class="right">
					<ul>
						<li><a href="${conPath }/loginOut.do">Log out</a></li>
						<li><a href="${conPath }/myInfo.do">My info</a></li>
					</ul>
				</div>
			</c:if>
			<c:if test="${empty member and not empty admin }">
				<div class="right">
					${admin.aname }(Admin)<br>
					<ul>
						<li><a href="${conPath }/adminLogout.do">Logout</a></li>
						<li><a href="${conPath }/gameManagemnet.do">게임관리</a></li>
						<li><a href="${conPath }/memberManagement.do">회원관리</a></li>
					</ul>
				</div>
			</c:if>
			<div class="center">
				<img id="logo" src="${conPath }/img/logo.png" onclick="location.href='${conPath }/main.do'">
				<img id="run" src="${conPath }/img/run.gif">
			</div> 
		</div>
		<hr>
	</header>
</body>
</html>