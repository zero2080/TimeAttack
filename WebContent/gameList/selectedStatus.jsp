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
	table td{
		border-right:1px solid black;
	}
</style>
<script>
$('.confirm').click(function (){
	if(tStatus!=""){
		var tnum = $(this).attr('name');
		var tt='#'+tnum;
		$.ajax({
			url:'${conPath}/confirm.do',
			type:'get',
			dataType:'html',
			data:'tStatus=2&tNumber='+tnum,
			success:function(data){
				$(tt).html(data);
			}
		});
	}
});
$('.hold').click(function (){
	if(tStatus!=""){
		var tnum = $(this).attr('name');
		var tt='#'+tnum;
		$.ajax({
			url:'${conPath}/confirm.do',
			type:'get',
			dataType:'html',
			data:'tStatus=1&tNumber='+tnum,
			success:function(data){
				$(tt).html(data);
			}
		});
	}
});
$('.rewrite').click(function (){
	if(tStatus!=""){
		var tnum = $(this).attr('name');
		var tt='#'+tnum;
		$.ajax({
			url:'${conPath}/confirm.do',
			type:'get',
			dataType:'html',
			data:'tStatus=3&tNumber='+tnum,
			success:function(data){
				$(tt).html(data);
			}
		});
	}
});
</script>
</head>
<body>
<b id="b"></b>
<table>
	<tr>
		<th>No</th>
		<th>Release</th>
		<th>ID(Nick Name)</th>
		<th>Game Number</th>
		<th>Running Time</th>
		<th>Link</th>
		<th>
			<select id="tStatus">
			    <option value="0">등록대기</option>
			    <option value="1">보류</option>
			    <option value="2">등록</option>
			    <option value="3">수정요청</option>
			</select>
		</th>
		<th>Confirm</th>
	</tr>
	<c:if test="${empty tStatus }">
		<tr>
			<td colspan="8">조회결과 없음</td>
		</tr>
	</c:if>
	<c:if test="${not empty tStatus }">
		<c:forEach var="tst" items="${tStatus }">
			<tr>
				<td>${tst.tNumber }</td>
				<td>${tst.tRdate }</td>
				<td>${tst.mId }(${tst.mNickName })</td>
				<td>${tst.gNumber }</td>
				<td><fmt:formatDate value="${tst.tTmst }" pattern="kk:mm:ss"/></td>
				<td><a href="${tst.tLink }" target="_blank">보기</a></td>
				<td>
					<c:if test="${0 eq tst.tStatus }">
						등록대기
					</c:if>
					<c:if test="${1 eq tst.tStatus }">
						보류
					</c:if>
					<c:if test="${2 eq tst.tStatus }">
						등록
					</c:if>
					<c:if test="${3 eq tst.tStatus }">
						수정요청
					</c:if>
				</td>
				<td id="${tst.tNumber }">
					<c:if test="${0 eq tst.tStatus }">
						<button class="confirm" name="${tst.tNumber }">승인</button>
						<button class="hold" name="${tst.tNumber }">보류</button>
						<button class="rewrite" name="${tst.tNumber }">수정요청</button>
					</c:if>
					<c:if test="${1 eq tst.tStatus }">
						<button class="confirm" name="${tst.tNumber }">승인</button>
						<button class="rewrite" name="${tst.tNumber }">수정요청</button>
					</c:if>
					<c:if test="${2 eq tst.tStatus }">
						<button class="rewrite" name="${tst.tNumber }">수정요청</button>
					</c:if>
					<c:if test="${3 eq tst.tStatus }">
						<button class="confirm" name="${tst.tNumber }">승인</button>
						<button class="hold" name="${tst.tNumber }">보류</button>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</c:if>
	</table>
</body>
</html>