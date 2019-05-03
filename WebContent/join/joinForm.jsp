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
var idChecked = 0;
	$(document).ready(function(){
		$('#idChk').click(function(){
			var mid=$('input[name="mid"]').val();
			if(mid!=""){
				$.ajax({
					url:'${conPath}/idConfirm.do',
					type:'get',
					dataType:'html',
					data:'mid='+mid,
					success:function(data){
						$('#idConfirmMsg').html(data).css('color','red');
					}
				});
			}else{
				$('#idConfirmMsg').html('아이디를 입력 하세요').css('color','red');
			}
		});
		$('#mpwChk').keyup(function(){
			var mpw=$('input[name="mpw"]').val();
			var mpwChk=$('input[name="mpwChk"]').val();
			if(mpw!=mpwChk){
				$('#pwConfirm').html('비밀번호를 확인해주세요').css('color','red');
			}else{
				$('#pwConfirm').html('');
			}
		});
		$('#nickNameChk').click(function(){
			var mnickname=$('input[name="mnickname"]').val();
			if(mnickname!=""){
				$.ajax({
					url:'${conPath}/mnicknameConfirm.do',
					type:'get',
					dataType:'html',
					data:'mnickname='+mnickname,
					success:function(data){
						$('#mnicknameConfirmMsg').html(data).css('color','red');
						
					}
				});
			}else{
				$('#mnicknameConfirmMsg').html('닉네임을 입력 하세요').css('color','red');
			}
		});
		$('#submit').click(function(){
			var chk = Number($('input[id="nchk"]').val())+Number($('input[id="ichk"]').val());
			if(chk==1){
				alert('닉네임을 확인해 주세요');
				return;
			}else if(chk==2){
				alert('아이디를 확인해 주세요');
				return;
			}else if(isNaN(chk) || chk==0){
				alert('아이디와 닉네임을 확인해 주세요');
				return;
			}
			var formData = $("#join_Frm").serialize();
			$.ajax({
				type:"POST",
				url:"joinPro.do",
				data:formData,
				success:function(data){
					location.href="${conPath}/join/welcome.jsp";
				}
			})
		});
	});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="join_frm">
		<form class ="box" method="post">
			<table>
				<tr><th>ID</th>
					<td><input type="text" name="mid" required="required"></td>
					<td><input type="button" value="ID check" id="idChk"></td>
					<td><span id="idConfirmMsg"></span></td>
				</tr>
				<tr><th>Pass Word</th>
					<td><input type="password" name="mpw" required="required"></td>
				</tr>
				<tr><th>Pass Word Check</th>
					<td><input type="password" name="mpwChk" id="mpwChk" required="required"></td>
					<td colspan="2"><span id="pwConfirm"></span></td>
				</tr>
				<tr><th>Name</th>
					<td><input type="text" name="mname" required="required"></td>
					
				</tr>
				<tr><th>Nick Name</th>
					<td><input type="text" name="mnickname" required="required"></td>
					<td><input type="button" value="중복확인" id="nickNameChk"></td>
					<td><span id="mnicknameConfirmMsg"></span></td>
				</tr>
				<tr><th>E - Mail</th>
					<td><input type="email" name="memail" required="required"></td>
				</tr>
				<tr>
				<td colspan="3"><input type="submit" value="Join" id="submit">
								<input type="reset" value="Reset">
								<input type="button" value="Back" onclick="history.back()"></td>
				</tr>
			</table>
		</form>	
		</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>