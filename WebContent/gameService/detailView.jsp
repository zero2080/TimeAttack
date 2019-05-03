<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#info_wrap{
		margin:0 auto;
		text-align:center;
	}
	#info_wrap img{
		width:100%;
	}
	#info_wrap table{
		margin:0 auto;
		width:800px;
		border-top:1px solid #eee;
	}
	#info_wrap table th{
		background-color:#eee;
		border-top:1px solid #eee;
		border-bottom:1px solid black;
	}
	#info_wrap table td{
		border-bottom:1px solid black;
	}
	#info_table{
		margin:0 auto;
	}
	#regiBtn{
		display:inline-block;
		width:170px;
		height:50px;	
		line-height:50px;
		overflow:hidden;
		text-shadow:1px 1px 10px;
		font-weight:bold;
		cursor:pointer;
	}
	#select{
		width:100%;
		margin:0 auto;
		overflow:hidden;
	}
	#record td{
		font-family:sans-sarif;
		font-size:0.8em;
		font-weight:bold;
		color:#101010;
	} 
	#record .rk{
		width:50px;
	}
	#record .pl{
		width:90px;
	}
	#record .df{
		width:140px;
	}
	#record .md{
		width:81px;
	}
	#record .pf{
		width:124px;
	}
	#record .rt{
		width:110px;
	}
	#record .vd{
		width:37px;
	}
	#record .rd{
		width:168px;
	}
	#recordBody tr{
		cursor:pointer;
	}
	#regiReco{
		display:none;
		position:absolute;
		top :80%;
		left:30%;
		background-color:#eee;
		border-radius:20px;
		border:3px dashed #090909;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('#ok').click(function(){
			var tDifficulty = [];
			var tMode = [];
			var tPlatform = [];
			var gNumber = ${game.gNumber};
		    $("input[name='diff']:checked").each(function(i) {
		    	tDifficulty.push('\''+$(this).val()+'\'');
		    });
		    
		    $("input[name='md']:checked").each(function(i) {
		    	tMode.push('\''+$(this).val()+'\'');
		    });
		    
		    $("input[name='platform']:checked").each(function(i) {
		    	tPlatform.push('\''+$(this).val()+'\'');
		    });
		    
		    var request = 'gNumber='+gNumber+'&tDifficulty='+tDifficulty+'&tMode='+tMode+'&tPlatform='+tPlatform;
		  	if(tDifficulty[0]==null){
		  		alert('Difficulty : 하나 이상 선택하세요');
		  		return;
		  	}
		  	if(tMode[0]==null){
		  		alert('Mode : 하나 이상 선택하세요');
		  		return;
		  	}
		  	if(tPlatform[0]==null){
		  		alert('Platform : 하나 이상 선택하세요');
		  		return;
		  	}
		    $.ajax({
				url:'${conPath}/gameRecordView.do',
				type:'get',
				dataType:'html',
				data:request,
				success:function(data){
					$('#recordBody').html(data);
				}
			}); 
		});
		$('#regiBtn').click(function(){
			$('#regiReco').css('display','block');
			
		});
		
		$('#regiOk').click(function(){
			var id = '${member.mid}';
			var nm = '${member.mnickName}';
			var gnb = '${game.gNumber}';
			var plat = $('input[name="regi_pf"]:checked').val();
			var dif = $('input[name="regi_diff"]:checked').val();
			var md = $('input[name="regi_md"]:checked').val();
			
			var hh = $('input[name="regi_hh"]').val();
			var mm = $('input[name="regi_mm"]').val();
			var ss = $('input[name="regi_ss"]').val();
			var ttmst = hh+":"+mm+":"+ss;
			var link = $('input[name="regi_link"]').val();
			
			var req = 'mId='+id+'&mNickName='+nm+'&gNumber='+gnb+'&tPlatform='+plat+'&tDifficulty='+dif+'&tMode='+md+'&tTmst='+ttmst+'&tLink='+link;
			
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
					url:'${conPath}/recordRegistration.do',
					type:'get',
					dataType:'html',
					data:req,
					success:function(data){
						$('#regiReco').html(data);
					}
			}); 
		});
		$('#regiCC').click(function(){
			$('#regiReco').css('display','none');
		});
		$('.rcDetail').click(function(){
			var tNumber =$(this).attr('id');
			var popUrl = "${conPath}/personRecordView.do?tNumber="+tNumber;
			var popOption = "width=600, height=800, resizable=no, scrollbars=no, status=no;";
				window.open(popUrl,"",popOption);
		});
	});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<section>
		<div id="info_wrap">
			<div id="image">
				<img src='${conPath}/gameImg/${game.gImg}'>
			</div>
			<div id="info_table">
				<table>
					<caption><h2>${game.gTitle }</h2></caption>
					<tr>
						<th>Platform</th>
						<td colspan="3">${game.gPlatform }</td>
					</tr>
					<tr>
						<th>장르</th><td>${game.gGenre }</td>
						<th>장르2</th><td>${game.gGenre2 }</td>
					</tr>
					<tr>
						<th>난이도</th>
						<td colspan="3">${game.gDifficulty }</td>
					</tr>
					<tr>
						<th>모드</th>
						<td colspan="3">${game.gMode }</td>
					</tr>
					<tr>
						<th>제작사</th><td>${game.gCorp }</td>
						<th>출시일</th><td>${game.gDate }</td>
					</tr>
				</table>
				<c:if test="${not empty member }">
					<div id="regiBtn">
						<img style="width:30px;margin-top:10px;" src="${conPath }/img/icon_regi.png" alt="record registration">기록 등록하기
					</div>
					<div id="regiReco">
						<jsp:include page="regiRecordView.jsp"/>
					</div>
				</c:if>
			</div>
			<div id="select">
				<hr>
				<table id="sel_tb">
					<tr>
						<th>Difficulty</th>
						<td>
							<c:forEach var="diff" items="${diffs }">
								<input type="checkbox" name="diff" value="${diff }" checked="checked">${diff }
							</c:forEach>
						</td>
						<td rowspan="3"><button id="ok">OK</button></td>
					</tr>
					<tr>
						<th>Mode</th>
						<td>
							<c:forEach var="md" items="${modes }">
								<input type="checkbox" name="md" value="${md }" checked="checked">${md }
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th>Platform</th>
						<td>
							<c:forEach var="pf" items="${platform }">
								<input type="checkbox" name="platform" value="${pf }" checked="checked">${pf }
							</c:forEach>
						</td>
					</tr>
				</table>
				<hr>
			</div>
			<div id="record">
				<table>	
					<tr>
						<th class="rk">Rank</th>
						<th class="pl">Player</th>
						<th class="df">Difficulty</th>
						<th class="md">Mode</th>
						<th class="pf">Platform</th>
						<th class="rt">Running Time</th>
						<th class="vd"><img style="width:20px;" src="${conPath }/img/icon_video.png" alt="video icon"></th>
						<th class="rd">등록일</th>
					</tr>
				</table>
				<div id="recordBody">
					<table>
						<c:if test="${empty records }">
							<tr>
								<td colspan="4">등록된 기록이 없습니다.</td>
							</tr>
						</c:if>
						<c:set var="i" value="1"/>
						<c:if test="${not empty records }">
							<c:forEach var="record" items="${records }">
								<tr class="rcDetail" id="${record.tNumber }">
									<td class="rk"><c:out value="${i}"/></td>
									<td class="pl">${record.mNickName }</td>
									<td class="df">${record.tDifficulty }</td>
									<td class="md">${record.tMode }</td>
									<td class="pf">${record.tPlatform }</td>
									<td class="rt"><b><fmt:formatDate value="${record.tTmst }" pattern="HH:mm:ss" /></b></td>
									<td class="vd"><a href="${record.tLink }" target="_blank"><img style="width:20px;" src="${conPath }/img/icon_video.png" alt="video icon" ></a></td>
									<td class="rd">${record.tRdate }</td>
								</tr>
								<c:set var="i" value="${i+1 }"/>
							</c:forEach>
						</c:if>
					</table>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>