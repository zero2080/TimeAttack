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
		$('#regiOk').click(function(){
			var tnum ='${param.tNumber}'
			var id = '${member.mid}';
			var nm = '${member.mnickName}';
			var plat = $('input[name="regi_pf"]:checked').val();
			var dif = $('input[name="regi_diff"]:checked').val();
			var md = $('input[name="regi_md"]:checked').val();
			
			var hh = $('input[name="regi_hh"]').val();
			var mm = $('input[name="regi_mm"]').val();
			var ss = $('input[name="regi_ss"]').val();
			var ttmst = hh+":"+mm+":"+ss;
			var link = $('input[name="regi_link"]').val();
			
			var req = 'mId='+id+'&mNickName='+nm+'&tNumber='+tnum+'&tPlatform='+plat+'&tDifficulty='+dif+'&tMode='+md+'&tTmst='+ttmst+'&tLink='+link;
			
			if(plat==null){
				alert('Platform을 선택하세요.');
				return;
			}
			if(md==null){
				alert('Mode를 선택하세요.');
				return;
			}
			if(dif==null){
				alert('Difficulty를 선택하세요.');
				return;
			}
			
			if(hh=='' || mm=='' || ss==''){
				alert('Running Time을 잘못입력했습니다.');
				return;
			}
			if(link==''){
				alert('Video링크를 입력하세요.');
				return;
			}
			 $.ajax({
					url:'${conPath}/recordModifyPro.do',
					type:'get',
					dataType:'html',
					data:req,
					success:function(data){
						window.close();
					}
			}); 
		});
	});
</script>
</head>
<body>
	<table>
			<caption><h3>Record Modify</h3></caption>
			<tr>
				<th>Platform</th>
				<td>
					<c:forEach var="pf" items="${platform }">
						<input type="radio" name="regi_pf" value="${pf }">${pf }
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th>Mode</th>
				<td>
					<c:forEach var="md" items="${modes }">
						<input type="radio" name="regi_md" value="${md }">${md }
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th>Difficulty</th>
				<td>
					<c:forEach var="diff" items="${diffs }">
						<input type="radio" name="regi_diff" value="${diff }">${diff }
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th>Running Time</th>
				<td>
					<input type="text" name="regi_hh" size="2" placeholder="hh"> : <input type="text" name="regi_mm" size="2" placeholder="mm"> : <input type="text" name="regi_ss" size="2" placeholder="ss">
				</td>
			</tr>
			<tr>
				<th>Video Link</th>
				<td><input type="text" name="regi_link"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" id="regiOk" value="등록" onclick="regist()">
					<input type="button" id="regiCC" value="닫기" onclick="close()">
				</td>
			</tr>
		</table>
		<br><br>
</body>
</html>