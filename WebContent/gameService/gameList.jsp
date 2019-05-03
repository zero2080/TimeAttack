<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('#search').click(function (){
			var gTitle = document.getElementById('gTitle').value;
			if(gTitle==""){
				gTitle=" ";
				$.ajax({
					url:'${conPath}/gameList.do',
					type:'get',
					dataType:'html',
					data:'gTitle='+gTitle,
					success:function(data){
						$('body').html(data);
					}
				});
			}else if(gTitle!=""){
				$.ajax({
					url:'${conPath}/gameList.do',
					type:'get',
					dataType:'html',
					data:'gTitle='+gTitle,
					success:function(data){
						$('body').html(data);
					}
				});
			}
		});
		
		$('.gm').click(function (){
			location.href='${conPath}/detailGame.do?gNumber='+$(this).children(0).eq(0).text();
		});
	});
</script>
<style>
	#sc{
		margin:0 auto;
		text-align:center;
	}
	#gl_wrap table{
		margin:0 auto;
		text-align:center;
		width:800px;
	}
	table th{
		background-color:#eee;
		border-bottom:1px dashed black;
	}
	table td{
		border-right:2px solid #999;
		cursor:pointer;
	}
	#gimg img{
		height:40px;
	}
	.paging{
		text-align:center;
	}
</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<section>
		<div id="sc">
			<input type="text" id="gTitle"><button id="search">검색</button>
		</div>
		<div id="gl_wrap">
			<table>
				<tr>
					<th>No</th>
					<th>Image</th>
					<th>Title</th>
					<th>Corp</th>
					<th>Release</th>
				</tr>
				<c:if test="${empty games }">
					<tr>
						<td colspan="5">등록된 게임이 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${not empty games }">
					<c:forEach var="game" items="${games }">
						<tr class="gm">
							<td>${game.gNumber }</td>
							<td id="gimg"><img src = "${conPath }/gameImg/${game.gImg}"></td>
							<td>${game.gTitle }</td>
							<td>${game.gCorp }</td>
							<td>${game.gDate }</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			<div class="paging">
				<c:if test="${startPage > BLOCKSIZE }"	>
					<a href="${conPath }/gameList.do?pageNum=${startPage-1 }">이전</a>
				</c:if>
				<c:forEach var = "i" begin="${startPage }" end = "${endPage }">
					<c:if test="${i==pageNum }">
						[ <b>${i }</b> ]
					</c:if>
					<c:if test="${i!=pageNum }">
						[<a href="${conPath }/gameList.do?pageNum=${i}">${i }</a>]
					</c:if>
				</c:forEach>
				<c:if test="${endPage < pageCnt }">
					<a href="${conPath }/gameList.do?pageNum=${endPage+1 }"> 다음</a>
				</c:if>
			</div>
		</div>
	</section>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>