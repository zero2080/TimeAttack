<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#editSection{
		margin:0 auto;
		width:900px;
		
	}
	#cke_editor{
		margin:0 auto;
		width:800px;
	}
	#editSection th{
		width:100px;
	}
	#editSection td{
		border:3px solid #eee;
	}
	
	#editSection #nTitle{
		width:98%;
		height:20px;
		line-height:20px;
		font-size:15px;		
	}
</style>
<script src="//cdn.ckeditor.com/4.11.3/full/ckeditor.js"></script>

</head>
<body>
<c:if test='${empty admin }'>
	<script>
		alert('로그아웃되었습니다.\n 다시 로그인 후 이용해 주세요.');
		location.href="${conPath }/main.do";
	</script>
</c:if>
	<jsp:include page="../main/header.jsp" />
	<div id="editSection">
		<form action="${conPath }/n_write.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="nNumber" value="${dto.nNumber }">
			<input type="hidden" name="nGroup" value="${dto.nGroup }">
			<input type="hidden" name="nStep" value="${dto.nStep }">
			<input type="hidden" name="nLv" value="${dto.nLv }">
			<input type="hidden" name="aId" value="${admin.aid }">
			<table>
				<caption><h3>공지게시판</h3></caption>
				<tr><th>글제목</th>
					<td><input type="text" name="nTitle" id="nTitle" placeholder="Title"/></td>
					<th>작성자</th><td>${admin.aid }</td>
				</tr>
				<tr><th>파일첨부</th>
					<td colspan="3"><input type="file" name="nFile"></td>
				</tr>
				<tr><th>내용</th>	
					<td colspan="3"><textarea name="nContent" id="editor"></textarea>
					<script>
				        CKEDITOR.replace('nContent');
				    </script>
	    			</td>
				</tr>
				<tr><th colspan="4">
					<input type="submit" value="작성완료">
					</th>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>