<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var = "conPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath}/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<c:if test="${1 eq member.mstatus || 2 eq member.mstatus}">
		<script>
			alert('탈퇴된 회원입니다.');
			location.href="${conPath}/loginOut.do";
		</script>
	</c:if>
	<jsp:include page="header.jsp" />
	<section>
		<div id="wrap">
			<div id="top">
				<div id="video">
				<h2>Hot clip</h2>
					<iframe width="600" height="335" src="https://www.youtube.com/embed/KXjndMZAblo" ></iframe>
				</div>
				<div id="content">
					<table>
						<c:forEach var="i" begin="0" end="3" step="1">
							<tr>
								<th rowspan="2"><img src="" alt="test${i }"></th>
								<td><span>title / nickName</span> </td> 
							</tr>
							<tr>
								<td style="text-align:right;"><span style="font-size:0.7em;">UPDATE : 2018/03/29</span></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<hr>
			<div id="bottom">
				<ul>
					<c:forEach var="j" begin="0" end="20" step="1">
						<li>
							<table>
								<tr>
									<th rowspan="2"><img src="" alt="imageName"></th>
									<td>게임제목${j }</td>
									<td colspan="3"/>
									<td>2019/02/12 23:20:11</td>
								</tr>
								<tr>
									<td>nickName / id</td>
									<td>Run 01:20:53
									<td>Leon A</td>
									<td>HardCore</td>
									<td>View : 20</td>
							</table>
						</li>
						<!-- 기록 반복문 시간별 최신등록 -->
					</c:forEach>
				</ul>
			</div>
		</div>
	</section>
	<jsp:include page="footer.jsp" />
</body>
</html>