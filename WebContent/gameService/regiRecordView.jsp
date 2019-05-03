<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<table>
			<caption><h3>Record registration</h3><br>${game.gTitle }</caption>
			<tr>
				<th>Platform</th>
				<td>
					<c:forEach var="pf" items="${platform }">
						<input type="radio" name="regi_pf" value="${pf }">${pf }
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th>Mode</th>
				<td>
					<c:forEach var="md" items="${modes }">
						<input type="radio" name="regi_md" value="${md }">${md }
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th>Difficulty</th>
				<td>
					<c:forEach var="diff" items="${diffs }">
						<input type="radio" name="regi_diff" value="${diff }">${diff }
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th>Running Time</th>
				<td>
					<input type="text" name="regi_hh" size="2" placeholder="hh"> : <input type="text" name="regi_mm" size="2" placeholder="mm"> : <input type="text" name="regi_ss" size="2" placeholder="ss">
				</td>
			</tr>
			<tr>
				<th>Video Link</th>
				<td><input type="text" name="regi_link"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" id="regiOk" value="등록" onclick="regist()">
					<input type="button" id="regiCC" value="닫기" onclick="close()">
				</td>
			</tr>
		</table>
		<br><br>
</body>
</html>