<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#record{
		margin:0 auto;
	}
	#record table{
		margin:0 auto;
		text-align:center;
		
	}
	#record table th{
		border-bottom:1px dashed #666;
	}
	#record table td{
		font-size:0.8em;
		border-right:1px solid black;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('.modi').click(function(){
			var tNumber = $(this).parent().parent().attr('id');
			var param = '?tNumber='+tNumber+'&mId='+'${member.mid}'
			var popUrl = "${conPath}/recordModify.do"+param;
			var popOption = "width=600, height=360, resizable=no, scrollbars=no, status=no;";
					window.open(popUrl,"",popOption);
		});
	});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<section>
		<div id="record">
			<table>
				<tr>
					<th>No</th>
					<th>GameNo</th>
					<th>Platform</th>
					<th>Mode</th>
					<th>Difficulty</th>
					<th>Running Time</th>
					<th>Status</th>
					<th>Link</th>
					<th>Date</th>
				</tr>
				<tr>
					<c:if test="${empty records }">
						<th>개인기록이 없습니다.</th>
					</c:if>
				</tr>
					<c:if test="${not empty records }">
						<c:forEach var="r" items="${records }">
							<tr id="${r.tNumber }">
								<td> &emsp; ${r.tNumber } &emsp; </td>
								<td> &emsp; ${r.gNumber } &emsp; </td>
								<td> &emsp; ${r.tPlatform } &emsp; </td>
								<td> &emsp; ${r.tMode } &emsp; </td>
								<td> &emsp; ${r.tDifficulty } &emsp; </td>
								<td> &emsp; <fmt:formatDate value="${r.tTmst }" pattern="HH:mm:ss" /> &emsp; </td>
								<td>
									<c:if test="${0 eq r.tStatus }">
										등록대기 (<span class="modi">정보수정</span>)
									</c:if>
									<c:if test="${1 eq r.tStatus }">
										보류(<span class="modi">정보수정</span>)
									</c:if>
									<c:if test="${2 eq r.tStatus }">
										등록
									</c:if>
									<c:if test="${3 eq r.tStatus }">
										수정요청(<span class="modi">정보수정</span>)
									</c:if>
								</td>
								<td>${r.tLink }</td>
								<td>${r.tRdate }</td>
							</tr>
						</c:forEach>
					</c:if>
				
			</table>
		</div>
	</section>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>