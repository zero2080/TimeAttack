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
	#gl_wrap table{
		margin:0 auto;
		text-align:center;
	}
	#gimg img{
		height:40px;
	}
	.paging{
		text-align:center;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('#newGame').click(function (){
			var popOption = "width=600, height=360, resizable=no, scrollbars=no, status=no;";
			var nG = window.open('${conPath}/newGameView.do',"",popOption);
		});
		$('.record').click(function (){
			var popOption = "width=950, height=360, resizable=no, scrollbars=no, status=no;";
			var tl = window.open('${conPath}/recordList.do?gnumber='+$(this).attr('name'),"",popOption);
		});
	});
	
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<section>
		<div id="gl_wrap">
			<table>
				<tr><td>
				<button id="newGame">신규등록</button>
				</td></tr>
			</table>
			<table border=1>
				<tr>
					<th>No</th>
					<th>Image</th>
					<th>Title</th>
					<th>Corp</th>
					<th>Release</th>
					<th>Regist</th>
					<th>기록보기</th>
				</tr>
				<c:if test="${empty games }">
					<tr>
						<td colspan="7">등록된 게임이 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${not empty games }">
					<c:forEach var="game" items="${games}">
						<tr>
							<td>${game.gNumber }</td>
							<td id="gimg"><img src = "${conPath }/gameImg/${game.gImg}"></td>
							<td>${game.gTitle }</td>
							<td>${game.gCorp }</td>
							<td>${game.gDate }</td>
							<td>${game.gRdate }</td>
							<td><button class="record" name="${game.gNumber }">기록</button></td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			<div class="paging">
				<c:if test="${startPage > BLOCKSIZE }"	>
					<a href="${conPath }/gameManagemnet.do?pageNum=${startPage-1 }">이전</a>
				</c:if>
				<c:forEach var = "i" begin="${startPage }" end = "${endPage }">
					<c:if test="${i==pageNum }">
						[ <b>${i }</b> ]
					</c:if>
					<c:if test="${i!=pageNum }">
						[<a href="${conPath }/gameManagemnet.do?pageNum=${i}">${i }</a>]
					</c:if>
				</c:forEach>
				<c:if test="${endPage < pageCnt }">
					<a href="${conPath }/gameManagemnet.do?pageNum=${endPage+1 }"> 다음</a>
				</c:if>
			</div>
		</div>
	</section>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>