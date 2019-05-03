<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table id="fb_re" border=1>
			<tr>
				<th>작성자</th>
				<td>댓글</td>
				<td>작성일(IP)</td>
			</tr>
			<c:if test="${totCnt != null }">
				<c:forEach var="dto" items="${fbreply }">
					<tr>
						<th>${dto.mId }<br>
							(${dto.mNickName })</th>
						<td class="left">${dto.frContent }</td>
						<td id="fr_DateIp">
							<fmt:formatDate value="${dto.frDate }" pattern="yyyy/MM/dd"/><br>
							(${dto.frIp })
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${not empty member }">
				<tr>
					<th>${member.mid }<br>
						(${member.mnickName })</th>
					<td><textarea rows="3" cols="80" id="rpCon"></textarea></td> 
					<td><button id="rePly">댓글달기</button></td>
				</tr>
			</c:if>
		</table>		
</body>
</html>