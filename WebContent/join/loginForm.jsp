<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<section>
		<div id="login_frm">
			<form action = "loginPro.do" class="box" method="post">
				<table>
					<tr><th>ID</th>
						<td><input type="text" name="mid" required="required" placeholder="I D" >
					</tr>
					<tr><th>Pass Word</th>
						<td><input type="password" name="mpw" required="required" placeholder="Pass Word" >
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="Login">
					</tr>
				</table>
			</form>
		</div>
	</section>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>