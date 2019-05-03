<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	#content,#fb_re{
		width:800px;
		margin:0 auto;
	}
	#fbContent{
		height:600px;
	}
	#fb_re th{
		width:80px;
	}
	#fr_DateIp{
		width:50px;
	}	
</style>
<script>
	function reply(){
		var fbNumber = ${fbDto.fbNumber};
		var frContent = document.getElementById('frCon').value;
		var pageNum = ${pageNum};
		location.href='${conPath}/rePly.do?fbNumber='+fbNumber+'&frContent='+frContent+'&pageNum='+pageNum;
	}
	function comment(){
		var fbNumber = ${fbDto.fbNumber};
		var pageNum = ${pageNum};
		location.href='${conPath}/fbWriteView.do?fbNumber='+fbNumber+'&pageNum='+pageNum;
	}
	function reWrite(){
		var fbNumber = ${fbDto.fbNumber};
		var pageNum = ${pageNum};
		location.href='${conPath}/fbReWriteView.do?fbNumber='+fbNumber+'&pageNum='+pageNum;
	}
	function del(){
		var fbNumber = ${fbDto.fbNumber};
		var pageNum = ${pageNum};
		location.href='${conPath}/fb_delete.do?fbNumber='+fbNumber+'&pageNum='+pageNum;
	}
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_wrap">
		<table id="content" border=1>
			<tr>
				<th>No</th><td>${fbDto.fbNumber }</td>
				<th>작성자</th><td>${fbDto.mId }</td>
				<th>NickName</th><td>${fbDto.mNickName }</td>
			</tr>
			<tr>
				<th>글제목</th>
				<td colspan="5">${fbDto.fbTitle }</td>
			</tr>
			
			<tr>
				<th colspan="6">글내용</th>
			</tr>
			<tr>
				<td colspan="6" id="fbContent">${fbDto.fbContent }</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><a href="${conPath }/fileUpload/${fbDto.fbFile }">${fbDto.fbFile }</a></td>
				<td colspan="4" id="button">
					<button onclick="comment()">답글달기</button>
					<c:if test="${fbDto.mId eq member.mid }">
						<button onclick="reWrite()">수정하기</button>
						<button onclick="del()">삭제하기</button>
					</c:if>
					<c:if test="${not empty admin }">
						<button onclick="del()">삭제하기</button>
					</c:if>
				</td>
			</tr>
		</table>
		<table id="fb_re" border=1>
			<tr>
				<th>작성자</th>
				<td>댓글</td>
				<td>작성일(IP)</td>
			</tr>
			<c:if test="${totCnt != null }">
				<c:forEach var="dto" items="${fbreply }">
					<tr>
						<th>${dto.mId }<br>
							(${dto.mNickName })</th>
						<td class="left">${dto.frContent }</td>
						<td id="fr_DateIp">
							<fmt:formatDate value="${dto.frDate }" pattern="yyyy/MM/dd"/><br>
							(${dto.frIp })
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${not empty member }">
				<tr>
					<th>${member.mid }<br>
						(${member.mnickName })</th>
					<td><textarea rows="3" cols="80" id="frCon"></textarea></td> 
					<td><button onclick="reply()">댓글달기</button></td>
				</tr>
			</c:if>
		</table>
	</div>	
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>