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
	#fb_wrap{
		min-height:600px;
	}
	#fb_wrap #freeboard{
		margin:0 auto;
	}
	#freeboard tr{
		cursor:pointer;
	}
	#freeboard tr:hover{
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
	.fbNum,.fbRC{
		width:50px;
		text-align:center;
	}
	.mId{
		width:80px;
		text-align:center;
	}
	.fbContent{
		width:150px;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('tr').click(function(){
			var fbNumber = Number($(this).children(0).eq(0).text());// 0번째 td안에 있는 text
			if(!isNaN(fbNumber)){
				location.href='${conPath}/content_view.do?fbNumber='+fbNumber+'&pageNum=${pageNum}';
			}
		});
	});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
		<div id="fb_wrap">
			<div id="button">
			<c:if test="${not empty member }">
				<button onclick="location.href='${conPath}/fbWriteView.do'">글쓰기</button>
			</c:if>
			</div><br><br>
			<table id="freeboard">
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
						<tr><td class="fbNum">${dto.fbNumber }</td>
							<td class="fbContent">
								<c:forEach var="i" begin="1" end="${dto.fbLv }">
										<c:if test="${i==dto.fbLv }">
											└
										</c:if>
										<c:if test="${i!=dto.fbLv }">
											&nbsp; &nbsp; &nbsp;
										</c:if>
								</c:forEach>
								&ensp; ${dto.fbTitle }</td>
							<td class="mId">${dto.mId }</td>
							<td class="fbRC">${dto.fbReadCount }</td>
							<td class="fbRdate"><fmt:formatDate value="${dto.fbRdate }" pattern="yyyy/MM/dd"/></td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			<div class="paging">
				<c:if test="${startPage > BLOCKSIZE }"	>
					<a href="${conPath }/freeBoard.do?pageNum=${startPage-1 }">이전</a>
				</c:if>
				<c:forEach var = "i" begin="${startPage }" end = "${endPage }">
					<c:if test="${i==pageNum }">
						[ <b>${i }</b> ]
					</c:if>
					<c:if test="${i!=pageNum }">
						[<a href="${conPath }/freeBoard.do?pageNum=${i}">${i }</a>]
					</c:if>
				</c:forEach>
				<c:if test="${endPage < pageCnt }">
					<a href="${conPath }/freeBoard.do?pageNum=${endPage+1 }"> 다음</a>
				</c:if>
			</div>
		</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>