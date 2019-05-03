<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/footer.css" rel="stylesheet" type="text/css">
</head>
<body id="footer">
	<footer>
		<div id="wrap">
			<div id="nav">
				<ul>
					<li><a href="${conPath }/main.do">Home</a></li>
					<li><a href="/agreement.do">Agreement</a></li>
					<li><a href="/guide.do">Guide</a></li>
					<li><a href="${conPath }/adminLogin.do">Admin Login</a></li>
					<li>Copyrightⓒ 2019 TimeAttack. All rights reserved.</li>
				</ul>
			</div>
			<div id="corp">
				<h3>Company Info</h3>
				<ul>
					<li>Company : Forrest Gump</li>
					<li>Owner : 오범수</li>
					<li>Business License : 000-00-000000</li>
					<li>E - Mail : zero2080@naver.com</li>
				</ul>
			</div>
			<div id="callservice">
				<h3>Cs Center</h3>
				<ul>
					<li>Tel : 032-000-0000</li>
					<li>Mon - Fri 10:00 - 18:00</li>
					<li>Weekend / Holiday OFF</li>
					<li>Bank Info : Kb bank 8282-01-00000000</li>
				</ul>
			</div>
		</div>
	</footer>
</body>
</html>