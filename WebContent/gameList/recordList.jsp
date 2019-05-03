<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table{
		width:900px;
		margin:0 auto;
		text-align:center;
	}	
	table td{
		border-right:1px solid black;
	}
</style>
</head>
<body>
	<div id="wrap">
		<table>
			<tr>
				<th>No</th>
				<th>ID(NickName)</th>
				<th>Running Time</th>
				<th>Difficulty</th>
				<th>Mode</th>
				<th>Registration Date</th>
				<th>Status</th>
			</tr>
			<c:if test="${empty tts }">
				<tr>
					<td colspan="7">
						등록된 기록이 없습니다.
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty tts }">
				<c:forEach var="tt" items="${tts }">
					<tr>
						<td>${tt.tNumber }</td>
						<td>${tt.mId }(${tt.mNickName })</td>
						<td><fmt:formatDate value="${tt.tTmst }" pattern="kk:mm:ss"/></td>
						<td>${tt.tDifficulty }</td>
						<td>${tt.tMode }</td>
						<td>${tt.tRdate }</td>
						<td>
							<c:if test="${0 eq tt.tStatus }">
								등록대기
							</c:if>
							<c:if test="${1 eq tt.tStatus }">
								보류
							</c:if>
							<c:if test="${2 eq tt.tStatus }">
								등록
							</c:if>
							<c:if test="${3 eq tt.tStatus }">
								수정요청
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</div>
</body>
</html>