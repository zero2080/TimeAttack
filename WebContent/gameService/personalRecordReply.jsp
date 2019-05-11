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
	#re_paging tr p{
		display:inline-block;
		font-size:12px;
		cursor:pointer;
		width:20px;
		height:20px;
		font-weight:bold;
		backfround-color:#aaa;
		margin:0;
	}
	#re_paging tr p b{
		color:#fff;
	}
	#reply table .re_con{
		background-color:#eee;
	}
	.re_date{
		width:80px;
	}
</style>
<script>
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
</script>
</head>
<body>
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
</body>
</html>