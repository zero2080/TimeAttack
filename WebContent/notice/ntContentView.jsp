<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#button{
		text-align:right;
	}
	#content_wrap{
		min-height:600px;
		width:800px;
		margin:0 auto;
	}
	#content{
		height:600px;
		width:100%;
	}
	#nContent{
		height:500px;
	}
</style>
<script>
	function del(){
		var nNumber = ${dto.nNumber};
		var pageNum = ${pageNum};
		location.href='${conPath}/nt_delete.do?nNumber='+nNumber+'&pageNum='+pageNum;
	}
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_wrap">
		<table id="content" border=1>
			<tr>
				<th>No</th>
				<td>${dto.nNumber }</td>
				<th>작성자</th>
				<td>${dto.aId }</td>
			</tr>
			<tr>
				<th>글제목</th>
				<td colspan="3">${dto.nTitle }</td>
			</tr>
			
			<tr>
				<th colspan="4">글내용</th>
			</tr>
			<tr>
				<td colspan="4" id="nContent">${dto.nContent }</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><a href="${conPath }/fileUpload/${dto.nFile }">${dto.nFile }</a></td>
				<td colspan="2" id="button">
					<c:if test="${dto.aId eq admin.aid }">
						<button onclick="reWrite()">수정하기</button>
						<button onclick="del()">삭제하기</button>
					</c:if>
				</td>
			</tr>
		</table>
	</div>	
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>