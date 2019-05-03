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
table{
	width:570px;
	text-align:center;
}
#wrap table th{
	border-right: 3px solid #aaa;
}
#wrap table td{
	border-bottom:1px solid #aaa;
}
#reply table {
	font-weight:bold;
	font-size:0.8em;
}
#reply table td{
	border-bottom:1px solid #aaa;
}
#reply table .re_con{
	background-color:#eee;
}
.re_date{
	width:80px;
}
#re_paging tr p{
	display:inline-block;
	font-size:12px;
	cursor:pointer;
	width:20px;
	height:20px;
	font-weight:bold;
	background-color:#aaa;
	font-weight:bold;
	margin:0;
}
#re_paging tr p b{
	color:#fff;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('#rpBtn').click(function(){
			var tNumber = ${param.tNumber};
			var trContent = document.getElementById('trCon').value;
			var mId = '${member.mid}';
			var request = 'tNumber='+tNumber+'&trContent='+trContent+'&mId='+mId;
			$.ajax({
				url:'${conPath}/ttrePly.do',
				type:'get',
				dataType:'html',
				data:request,
				success:function(data){
					$('#reply').html(data);
				}
			}); 
			document.getElementById('trCon').value='';
		});
		$('.paging').click(function(){
			var tNumber = ${param.tNumber};
			var mId = '${member.mid}';
			var pageNum = $(this).attr('id');
			var request = 'tNumber='+tNumber+'&pageNum='+pageNum;
			$.ajax({
				url:'${conPath}/ttrePly.do',
				type:'get',
				dataType:'html',
				data:request,
				success:function(data){
					$('#reply').html(data);
				}
			}); 
		});
	});
</script>
</head>
<body>
	<div id="wrap">
		<table>
			<tr><th>ID(Nick Name)</th>
				<td colspan="3">${record.mId }(${record.mNickName })</td>
			</tr>
			<tr><th>Platform</th>
				<td>${record.tPlatform }</td>
				<th>Difficulty</th>
				<td>${record.tDifficulty }</td>
			</tr>
			<tr><th>Mode</th>
				<td>${record.tMode }</td>
				<th>Running Time</th>
				<td><h3><fmt:formatDate value="${record.tTmst }" pattern="HH:mm:ss" /></h3></td>
			</tr>
			<tr><th colspan="4">Video</th>
			</tr>
			<tr>
				<td colspan="4">
				<iframe width="560" height="315" src="https://www.youtube.com/embed/${record.tLink }" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
				</td>
			</tr>
		</table>
		<div id="reply">
			<table>
				<c:if test="${empty replys }">
					<tr>
						<td colspan="4">
							작성된 댓글이 없습니다.
						</td>
					</tr>
				</c:if>
				<c:if test = "${not empty replys }">
					<c:forEach var="reply" items="${replys }">
						<tr>
							<td class="re_id">${reply.mId }</td>
							<td class="re_con">${reply.trContent }</td>
							<td class="re_date">${reply.trDate }<br>(${reply.trIp })</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			<table id="re_paging">
				<tr>
					<td>
					<c:if test="${startPage > BLOCKSIZE }"	>
						<p class="paging" id="${startPage-1 }">이전</p>
					</c:if>
					<c:forEach var = "i" begin="${startPage }" end = "${endPage }">
						<c:if test="${i==pageNum }">
							<p><b>${i }</b></p>
						</c:if>
						<c:if test="${i!=pageNum }">
							<p class="paging" id="${i}">${i }</p>
						</c:if>
					</c:forEach>
					<c:if test="${endPage < pageCnt }">
						<p class="paging" id="${endPage+1 }"> 다음</p>
					</c:if>
					</td>
				</tr>
			</table>
		</div>
		<c:if test="${not empty member }">
			<table>
				<tr><th>${member.mid }<br>(${member.mnickName })</th>
					<td><textarea id="trCon" name="trContent" cols="43" rows="2"></textarea>
					<td><button id="rpBtn" onclick="reply()">댓글달기</button></td>
				</tr>
			</table>
		</c:if>
		
	</div>
</body>
</html>