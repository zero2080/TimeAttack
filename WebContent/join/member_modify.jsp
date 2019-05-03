<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/join.css" rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$('#mpwChk').keyup(function(){
		var mpw=$('input[name="mpw"]').val();
		var mpwChk=$('input[name="mpwChk"]').val();
		if(mpw!=mpwChk){
			$('#pwConfirm').html('비밀번호를 확인해주세요').css('color','red');
		}else{
			$('#pwConfirm').html('');
		}
	});
	$('#submit').click(function(){
		var formData = $(".box").serialize();
		$.ajax({
			type:"POST",
			url:"modifyPro.do",
			data:formData,
			success:function(data){
				location.href="${conPath}/main.do";
			}
		})
	});
});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<section>
		<div id="join_frm">
			<form class="box">
				<table>
					<tr><th>ID</th>
						<td><input type="text" name="mid" value="${member.mid }" readonly="readonly">
						</td>
					</tr>
					<tr><th>Pass Word</th>
						<td><input type="password" name="mpw" required="required"></td>
					</tr>
					<tr><th>Pass Word Check</th>
						<td><input type="password" name="mpwChk" id="mpwChk" required="required"><span id="pwConfirm"></span></td>
					</tr>
					<tr><th>Name</th>
						<td><input type="text" name="mname" value="${member.mname }" required="required"></td>
					</tr>
					<tr><th>Nick Name</th>
						<td><input type="text" name="mnickname" value="${member.mnickName }" required="required">
							<input type="button" value="중복확인" id="nickNameChk"><span id="mnicknameConfirmMsg"></span>
						</td>
					</tr>
					<tr><th>E - Mail</th>
						<td><input type="email" name="memail" value="${member.memail }" required="required"></td>
					</tr>
					<tr>
					<td colspan="2"><input type="button" value="수정" id="submit">
									<input type="button" value="Back" onclick="history.back()"></td>
					</tr>
				</table>
			</form>
		</div>
	</section>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>