<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action ="${conPath }/gameRegi.do" method="post" enctype="multipart/form-data">
		<table border=1>
			<caption>Game Enrollment</caption>
			<tr>
				<th>Title</th>
				<td><input type="text" name="gTitle"></td>
				<th>작성자</th>
				<td><input type="text" name="aid" value="${admin.aid }" readonly="readonly"></td>
			</tr>
			<tr>
				<th>PlatForm</th>
				<td colspan="3">
					<input type="checkbox" name="gPlatform" value="PC">PC
					<input type="checkbox" name="gPlatform" value="PS3">PS3
					<input type="checkbox" name="gPlatform" value="PS4">PS4
					<input type="checkbox" name="gPlatform" value="XBOX 360">XBOX 360
					<input type="checkbox" name="gPlatform" value="XBOX One">XBOX One
					<input type="checkbox" name="gPlatform" value="Switch">Switch
					<input type="checkbox" name="gPlatform" value="Etc">Etc
				</td>
			</tr>
			<tr>
				<th>장르1</th>
				<td><input type="text" name="gGenre"></td>
				<th>장르2</th>
				<td><input type="text" name="gGenre2"></td>
			</tr>
			<tr>
				<th>Image</th>
				<td colspan="3"><input type="file" name="gImg"></td>
			</tr>
			<tr>
				<th>난이도</th>
				<td colspan="3"><input type="text" name="gDifficulty"></td>
			</tr>
			<tr>
				<th>제작사</th>
				<td colspan="3"><input type="text" name="gCorp"></td>
			</tr>
			<tr>
				<th>MODE</th>
				<td colspan="3"><input type="text" name="gMode"></td>
			</tr>
			<tr>
				<th>Release</th>
				<td colspan="3"><input type="date" name="gDate"></td>
			</tr>
			<tr>
				<td colspan="5">
					<input type="submit">
					<input type="reset">
					<input type="button" value="Close" onclick="close()">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>