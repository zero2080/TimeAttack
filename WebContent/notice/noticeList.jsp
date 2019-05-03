<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#nt_wrap{
		min-height:600px;
	}
	#nt_wrap #notice{
		margin:0 auto;
	}
	#notice tr{
		cursor:pointer;
	}
	#notice tr:hover{
		background-color:#eee;
		transition:0.25s;
	}
	#button{
		float:right;
		position:relative;
		right:370px;
	}
	.paging{
		text-align:center;
	}
	.ntNum,.ntRC{
		width:50px;
		text-align:center;
	}
	.aId{
		width:80px;
		text-align:center;
	}
	.ntContent{
		width:150px;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('tr').click(function(){
			var nNumber = Number($(this).children(0).eq(0).text());// 0번째 td안에 있는 text
			if(!isNaN(nNumber)){
				location.href='${conPath}/nContent_view.do?nNumber='+nNumber+'&pageNum=${pageNum}';
			}
		});
	});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
		<div id="nt_wrap">
			<div id="button">
			<c:if test="${not empty admin }">
				<button onclick="location.href='${conPath}/nWriteView.do'">글쓰기</button>
			</c:if>
			</div><br><br>
			<table id="notice">
				<tr style="font-size:0.7em;">
					<th>No</th><th style="width:300px;">글제목</th><th>작성자</th><th>조회수</th><th>작성일</th>
				</tr>
				<c:if test="${totCnt eq 0 }">
					<tr>
					<td colspan="5">글이 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${totCnt != null }">
					<c:forEach var="dto" items="${list }">
						<tr><td class="ntNum">${dto.nNumber }</td>
							<td class="ntContent">
								<c:forEach var="i" begin="1" end="${dto.nLv }">
										<c:if test="${i==dto.nLv }">
											└
										</c:if>
										<c:if test="${i!=dto.nLv }">
											&nbsp; &nbsp; &nbsp;
										</c:if>
								</c:forEach>
								&ensp; ${dto.nTitle }</td>
							<td class="aId">${dto.aId }</td>
							<td class="ntRC">${dto.nReadcount }</td>
							<td class="ntRdate"><fmt:formatDate value="${dto.nRdate }" pattern="yyyy/MM/dd"/></td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			<div class="paging">
				<c:if test="${startPage > BLOCKSIZE }"	>
					<a href="${conPath }/notice.do?pageNum=${startPage-1 }">이전</a>
				</c:if>
				<c:forEach var = "i" begin="${startPage }" end = "${endPage }">
					<c:if test="${i==pageNum }">
						[ <b>${i }</b> ]
					</c:if>
					<c:if test="${i!=pageNum }">
						[<a href="${conPath }/notice.do?pageNum=${i}">${i }</a>]
					</c:if>
				</c:forEach>
				<c:if test="${endPage < pageCnt }">
					<a href="${conPath }/notice.do?pageNum=${endPage+1 }"> 다음</a>
				</c:if>
			</div>
		</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>