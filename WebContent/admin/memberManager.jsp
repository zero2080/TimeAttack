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
#list_wrap{
		min-height:600px;
	}
	#list_wrap #members{
		margin:0 auto;
	}
	#members tr{
		border:1px dashed black;
		cursor:pointer;
	}
	#members tr:hover{
		background-color:#eee;
		transition:0.25s;
	}
	#button{
		float:right;
		position:relative;
		right:370px;
	}
	table{
		text-align:center;
	}
	.mid,.mname,.memail,.mpoint,.mstatus{
		background:#eee;
	}
</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<section>
		<div id="list_wrap">
			<table id="members">
				<caption>전체 회원리스트</caption>
				<c:if test="${empty members }">
					<tr>
						<td>가입한 회원이 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${not empty members }">
					<tr>
						<th>아이디</th>
						<th>비밀번호</th>
						<th>이름</th>
						<th>Nick Name</th>
						<th>E - Mail</th>
						<th>등급</th>
						<th>포인트</th>
						<th>가입일</th>
						<th>회원상태</th>
					</tr>
					<c:forEach var="member" items="${members }">
						<tr>
							<td class="mid">${member.mid}</td>
							<td class="mpw">${member.mpw }</td>
							<td class="mname">${member.mname }</td>
							<td class="mnickName">${member.mnickName }</td>
							<td class="memail">${member.memail }</td>
							<td class="mgrade">${member.mgrade }</td>
							<td class="mpoint">${member.mpoint }</td>
							<td class="mrdate">${member.mrdate }</td>
							<td class="mstatus">
								<c:if test="${member.mstatus eq 0}">
									활동 중
								</c:if>
								<c:if test="${member.mstatus eq 1}">
									탈퇴
								</c:if>
								<c:if test="${member.mstatus eq 2}">
									강퇴
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
	</section>
	<jsp:include page="../main/footer.jsp"/>	
</body>
</html>