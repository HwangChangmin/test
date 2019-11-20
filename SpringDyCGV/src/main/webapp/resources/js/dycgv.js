/*************************************
 * 제이쿼리 폼 체크 메소드
 *************************************/
$(document).ready(function(){
	/** 로그인 폼 체크 **/
	$("button#btnLogin").click(function(){
		if($("input#id").val() == ""){
			alert("아이디를 입력해 주세요.");
			$("input#id").focus();
		}else if($("input#pass").val() == "") {
			alert("패스워드를 입력해 주세요.");
			$("input#pass").focus();
		}else {
			loginForm.submit();
		}
	});
	
	/**	회원가입 폼 체크 **/
	$("#btnJoin").click(function(){
		if($("#jid").val() == "") {
			alert("아이디를 입력해 주세요");
			$("#jid").focus();
		}else if($("#jname").val() == "") {
			alert("이름을 입력해 주세요");
			$("#jname").focus();
		}else if($(".gender:checked").length == 0) {
			alert("성별을 선택해 주세요");
		}else if($("#jpass").val() == "") {
			alert("암호를 입력해 주세요");
			$("#jpass").focus();
		}else if($("#jcpass").val() == "") {
			alert("암호확인을 입력해 주세요");
			$("#jcpass").focus();
		}else if($("#jemailId").val() == "") {
			alert("이메일을 정확히 입력해 주세요");
			$("#jemailId").focus();
		}else if($("#jemailDomain").val() == "") {
			alert("이메일을 정확히 입력해 주세요");
			$("#jemailDomain").focus();
		}else if($("#jaddr").val() == "") {
			alert("주소를 입력해 주세요");
			$("#jaddr").focus();
		}else if($(".phone_comp:checked").length == 0) {
			alert("통신사를 선택해 주세요");
		}else if($("#jpn2").val() == "") {
			alert("휴대폰 번호를 정확히 입력해 주세요");
			$("#jpn2").focus();
		}else if($("#jpn3").val() == "") {
			alert("휴대폰 번호를 정확히 입력해 주세요");
			$("#jpn3").focus();
		}else if($(".hobby:checked").length == 0) {
			alert("관심사를 선택해 주세요");
		}else {
			joinForm.submit();
			alert("회원가입 완료");
		}	
	});
	
	/** 패스워드&패스워드 확인 체크 **/
	$("#jcpass").focusout(function(){
		if($("#jpass").val()==""){
			$("#passCheckResult").css("display","block").css("color","red").css("margin-left","120px").css("font-size","10pt");
			$("#jpass").focus();
			$("#passCheckResult").text("암호를 입력해 주세요.");
		}else{
			if($("#jcpass").val() != ""){
				if($("#jpass").val() == $("#jcpass").val()){
					$("#passCheckResult").css("display","block").css("color","blue").css("margin-left","120px").css("font-size","10pt");
					$("#passCheckResult").text("암호가 동일합니다.");
				}else{
					$("#passCheckResult").css("display","block").css("color","red").css("margin-left","120px").css("font-size","10pt");
					$("#passCheckResult").text("암호가 불일치합니다. 다시 입력해 주세요.");
					$("#jcpass").val("");
					$("#jpass").val("");
					$("#jpass").focus();
				}
			}else{
				$("#passCheckResult").css("display","block").css("color","red").css("margin-left","120px").css("font-size","10pt");
				$("#passCheckResult").text("암호확인을 입력해 주세요.");
			}
		}
	});
	
	/** email 도메인 선택 **/
	$("#email3").change(function(){
		if($("#email3").val() != "선택하세요"){
			$("#jemailDomain").val($("#email3").val());
		}else{
			$("#jemailDomain").val("");
		}
	});
	
	/** 게시판 제목 validation 체크 **/
	$("#btnRecBoard").click(function(){
		if($("#btitle").val() == ""){
			alert("제목을 입력해 주세요");
			$("#btitle").focus();
		}else{
			alert("등록완료");
			boardForm.submit();	
		}
	});
	
	/** 공지사항 제목 validation 체크 **/
	$("#btnRecNotice").click(function(){
		if($("#ntitle").val() == ""){
			alert("제목을 입력해 주세요");
			$("#ntitle").focus();
		}else{
			alert("등록완료");
			noticeForm.submit();	
		}
	});

	
});



/**
*	로그인 폼 체크
*/
function loginFormCheck() {
	var uid = document.getElementById("id");
	var upass = document.getElementById("pass");
	
	if(uid.value == ""){
		alert("아이디를 입력해 주세요.");
		uid.focus();
	}else if(upass.value == ""){
		alert("패스워드를 입력해 주세요.");
		upass.focus();
	}else{
		loginForm.submit();
		alert("로그인 성공");
	}
}

/**
*	회원가입 폼 체크
*/
function joinFormCheck() {
	var jid = document.getElementById("jid");
	var jname = document.getElementById("jname");
	var jgenderList = document.getElementsByName("gender");
	var jpass = document.getElementById("jpass");
	var jcpass = document.getElementById("jcpass");
	var jemailId = document.getElementById("jemailId");
	var jemailDomain = document.getElementById("jemailDomain");
	var jaddr = document.getElementById("jaddr");
	var jphone_compList = document.getElementsByName("phone_comp");
	var jpn2 = document.getElementById("jpn2");
	var jpn3 = document.getElementById("jpn3");
	var jhobbyList = document.getElementsByName("hobby");

	
	var genderCheck = func_check(jgenderList);
	var phone_compCheck = func_check(jphone_compList);
	var hobbyCheck = func_check(jhobbyList);
	
	if(jid.value == "") {
		alert("아이디를 입력해 주세요");
		jid.focus();
	}else if(jname.value == "") {
		alert("이름을 입력해 주세요");
		jname.focus();
	}else if(genderCheck == 0) {
		alert("성별을 선택해 주세요");
	}else if(jpass.value == "") {
		alert("암호를 입력해 주세요");
		jpass.focus();
	}else if(jcpass.value == "") {
		alert("암호확인을 입력해 주세요");
		jcpass.focus();
	}else if(jemailId.value == "") {
		alert("이메일을 정확히 입력해 주세요");
		jemailId.focus();
	}else if(jemailDomain.value == "") {
		alert("이메일을 정확히 입력해 주세요");
		jemailDomain.focus();
	}else if(jaddr.value == "") {
		alert("주소를 입력해 주세요");
		jaddr.focus();
	}else if(phone_compCheck == 0) {
		alert("통신사를 선택해 주세요");
	}else if(jpn2.value == "") {
		alert("휴대폰 번호를 정확히 입력해 주세요");
		jpn2.focus();
	}else if(jpn3.value == "") {
		alert("휴대폰 번호를 정확히 입력해 주세요");
		jpn3.focus();
	}else if(hobbyCheck == 0) {
		alert("관심사를 선택해 주세요");
	}else {
		joinForm.submit();
		alert("회원가입 완료");
	}
}

/**
*	체크박스 validation 체크
*/
function func_check(checkList) {
	var checkCount=0;
	for(i=0;i<checkList.length;i++){
		if(checkList[i].checked){
			checkCount++;
			i=checkList.length;
		}
	}
	return checkCount;
}


/* 패스워드&패스워드 확인 체크 */
function passCheck() {
	jpass = document.getElementById("jpass");
	jcpass = document.getElementById("jcpass");
	msg = document.getElementById("passCheckResult");
	
	if(jpass.value == ""){
		msg.style = "display:block;color:red;margin-left:120px;font-size:10pt;";
		jpass.focus();
		msg.innerHTML="암호를 입력해 주세요.";
		return false;
	}else{
		if(jcpass.value != ""){
			if(jpass.value == jcpass.value){
				msg.style = "display:block;color:blue;margin-left:120px;font-size:10pt;";
				msg.innerHTML="암호가 동일합니다";
			}else {
				msg.style.display = "block";
				msg.style.color = "red";
				msg.style.marginLeft = "120px";
				msg.style.fontSize ="10pt";
				msg.innerHTML="암호가 불일치합니다. 다시 입력해 주세요.";
				jcpass.value="";
				jpass.value="";
				jpass.focus();
			}				
		}
	}			
}

/* email 도메인 선택 */
function sdomain() {
	email3 = document.getElementById("email3");
	jemailDomain = document.getElementById("jemailDomain");
	
	if(email3.value != "선택하세요"){
		jemailDomain.value = email3.value;
	}else{
		jemailDomain.value = "";
	}
}

/* 게시판 제목 validation 체크 */
function boardFormCheck() {
	btitle = document.getElementById("btitle");
	
	if(btitle.value == ""){
		alert("제목을 입력해 주세요");
		btitle.focus();
	}else{
		alert("등록완료");
		boardForm.submit();
		
	}
}

/* 공지사항 제목 validation 체크 */
function noticeFormCheck() {
	ntitle = document.getElementById("ntitle");
	
	if(ntitle.value == ""){
		alert("제목을 입력해 주세요");
		ntitle.focus();
	}else{
		alert("등록완료");
		noticeForm.submit();				
	}
}