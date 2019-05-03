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
	#rl_wrap{
		margin:0 auto;
		text-align:center;
	}
	#rl_wrap table{
		margin:0 auto;
		width:800px;
	}
	table th{
		background-color:#eee;
		border-bottom:1px dashed black;
	}
	table td{
		border-right:1px solid black;
	}
	#paging{
		border:1px solid black;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
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
		$('#search').click(function(){
			var tStatus = document.getElementById('tStatus').value;
			if(tStatus!=""){
				$.ajax({
					url:'${conPath}/selectedStatus.do',
					type:'get',
					dataType:'html',
					data:'tStatus='+tStatus,
					success:function(data){
						$('#tb').html(data);
					}
				});
			}
		});
		
	});
	
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
		<section>
		<div id="rl_wrap">
			<input type="button" id="search" value="조회">
			<table id="tb">
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
						    <option value="2">등록승인</option>
						    <option value="3">수정요청</option>
						</select>
					</th>
					<th>Confirm</th>
				</tr>
				<c:if test="${empty list }">
					<tr>
						<td colspan="8">등록신청이 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${not empty list }">
					<c:forEach var="record" items="${list}">
						<tr>
							<td>${record.tNumber }</td>
							<td>${record.tRdate }</td>
							<td>${record.mId }(${record.mNickName })</td>
							<td>${record.gNumber }</td>
							<td><fmt:formatDate value="${record.tTmst }" pattern="kk:mm:ss"/></td>
							<td><a href="${record.tLink }" target="_blank">보기</a></td>
							<td>
								<c:if test="${0 eq record.tStatus }">
									등록대기
								</c:if>
								<c:if test="${1 eq record.tStatus }">
									보류
								</c:if>
								<c:if test="${2 eq record.tStatus }">
									등록
								</c:if>
								<c:if test="${3 eq record.tStatus }">
									수정요청
								</c:if>
							</td>
							<td id="${record.tNumber }">
								<button class="confirm" name="${record.tNumber }">승인</button>
								<button class="hold" name="${record.tNumber }">보류</button>
								<button class="rewrite" name="${record.tNumber }">수정요청</button>
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