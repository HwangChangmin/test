<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CGV에 오신것을 환영합니다</title>
	<link rel="stylesheet" type="text/css" href="http://localhost:9090/dycgv/css/dycgv.css">
	<script src="http://localhost:9090/dycgv/js/jquery-3.4.1.min.js"></script>
	<script src="http://localhost:9090/dycgv/js/dycgv.js"></script>
	
	<script>
		$(document).ready(function(){
			$("#btnCheckId").click(function(){		
				if($("#jid").val()==""){			
					$("#checkIdResult").text("아이디를 입력해 주세요.")
					.css("display","block").css("margin","5px 0 0 130px")
					.css("color","red").css("font-size","10pt");
					$("#jid").focus();
				}else{
					 $.ajax({
						 url: "id_check_process.do?jid="+$("#jid").val(), 
						 success: function(result){
						    if(result==0){
						    	 $("#checkIdResult").text("사용 가능한 아이디입니다.")
						    	 .css("display","block").css("margin","5px 0 0 130px")
						    	 .css("color","blue").css("font-size","10pt");
						    	
						    }else{
						    	$("#checkIdResult").text("이미 사용중인 아이디입니다. 다시 입력해주세요.")
						    	.css("display","block").css("margin","5px 0 0 130px")
						    	.css("color","red").css("font-size","10pt");
						    	$("#jid").val("").focus();
						    }
						 }});
				}
			});
		});
	</script>
</head>
<body>
	<div>		
	<!-- hearder 추가 -->
	<jsp:include page="../header.jsp"></jsp:include>
	
		<div id="content">
			<section>
				<img src="http://localhost:9090/dycgv/images/section1_img01.jpg"/>
				<div>
					<h1>회원가입</h1>
					<form action="join_proc.do" method="post" class="join" name=joinForm>
						<ul>
							<li>
								<label>아이디</label>
								<input type="text" name="id" id="jid">
								<button type="button" id="btnCheckId">중복확인</button>
							</li>
							<li>
								<span id="checkIdResult"></span>
							</li>
							<li>
								<label>이름</label>
								<input type="text" name="name" id="jname">
							</li>
							<li>
								<label>성별</label>
								<input type="radio" name="gender" class="gender" value="m">남자
								<input type="radio" name="gender" class="gender" value="f">여자
							</li>
							<li>
								<label>암호</label>
								<input type="password" name="pass" id="jpass">
							</li>
							<li>
								<label>암호확인</label>
								<input type="password" name="cpass" id="jcpass"><!-- onblur="passCheck()" --> <!-- 또는 onfocusout을 쓴다 -->
								<span id="passCheckResult"></span>
							</li>
							<li>
								<label>이메일</label>
								<input type="text" name="email1" id="jemailId">
								@ <input type="text" name="email2" id="jemailDomain">
								<select name="email3" onchange="sdomain()" id="email3">
									<option>선택하세요</option>
									<option>naver.com</option>
									<option>gmail.com</option>
									<option>daum.net</option>
								</select>
							</li>
							<li>
								<label>주소</label>
								<input type="text" name="addr" id="jaddr">
								<button type="button">주소찾기</button>
							</li>
							<li>
								<label>휴대폰</label>
								<div>
									<input type="radio" name="phone_comp" class="phone_comp" value="SKT">SKT
									<input type="radio" name="phone_comp" class="phone_comp" value="KT">KT
									<input type="radio" name="phone_comp" class="phone_comp" value="LGU+">LGU+
								</div>	
								<div>
									<select name="phone_number1">
										<option>010</option>
										<option>011</option>
										<option>017</option>						
									</select>
									- <input type="text" name="phone_number2" id="jpn2">
									- <input type="text" name="phone_number3" id="jpn3">
								</div>			
							</li>
							<li>
								<label>관심</label>
								<input type="checkbox" name="hobby" class="hobby" value="쇼핑">쇼핑
								<input type="checkbox" name="hobby" class="hobby" value="게임">게임
								<input type="checkbox" name="hobby" class="hobby" value="여행">여행
								<input type="checkbox" name="hobby" class="hobby" value="기타">기타
							</li>
							<li>
								<label>자기소개</label>
								<textarea name="intro" id="jintro"></textarea>
							</li>
							<li>
								<button type="button" id="btnJoin">회원가입</button>
								<button type="reset">다시입력</button>
							</li>			
						</ul>
					</form>
				</div>
			</section>
		</div>
	
		<!-- footer 추가 -->
		<jsp:include page="../footer.jsp"></jsp:include>
	
	</div>
</body>
</html>