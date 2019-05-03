function submitChk(){
	if(document.getElementById('idConfirmMsg').val()!='사용가능한 ID입니다.'){
		alert('ID를 확인해 주세요');
		return;
	}
	joinFrm.submit();
}