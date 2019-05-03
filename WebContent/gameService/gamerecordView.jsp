<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$(document).ready(function(){
	$('.rcDetail').click(function(){
		var tNumber =$(this).attr('id');
		var popUrl = "${conPath}/personRecordView.do?tNumber="+tNumber;
		var popOption = "width=600, height=800, resizable=no, scrollbars=no, status=no;";
			window.open(popUrl,"",popOption);
	});
});
</script>
</head>
<body>
	<table>
		<c:if test="${empty records }">
			<tr>
				<td colspan="4">등록된 기록이 없습니다.</td>
			</tr>
		</c:if>
		<c:set var="i" value="1"/>
		<c:if test="${not empty records }">
			<c:forEach var="record" items="${records }">
				<tr class="rcDetail" id="${record.tNumber }">
					<td class="rk"><c:out value="${i}"/></td>
					<td class="pl">${record.mNickName }</td>
					<td class="df">${record.tDifficulty }</td>
					<td class="md">${record.tMode }</td>
					<td class="pf">${record.tPlatform }</td>
					<td class="rt"><b><fmt:formatDate value="${record.tTmst }" pattern="kk:mm:ss" /></b></td>
					<td class="vd"><a href="${record.tLink }" target="_blank"><img style="width:20px;" src="${conPath }/img/icon_video.png" alt="video icon" ></a></td>
					<td class="rd">${record.tRdate }</td>
				</tr>
				<c:set var="i" value="${i+1 }"/>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>