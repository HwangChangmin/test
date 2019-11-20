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
</head>
<body>
	<div>		
	<!-- hearder 추가 -->
	<jsp:include page="../header.jsp"></jsp:include>
	
		<div id="content">
			<section>
				<img src="http://localhost:9090/dycgv/images/section1_img01.jpg"/>
				<div>
					<h1>로그인</h1>
					<form action="login_proc.do" method="post" class="login" name="loginForm">
						<ul>
							<li>
								<label>아이디</label>
								<input type="text" name="id" id="id">
							</li>
							<li>
								<label>패스워드</label>
								<input type="password" name="pass" id="pass">
							</li>
							<li>
								<button type="button" id="btnLogin">로그인</button>
								<button type="reset">다시쓰기</button>
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