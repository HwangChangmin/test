<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CGV에 오신것을 환영합니다.</title>
<link rel="stylesheet" type="text/css"
	href="http://localhost:9090/dycgv/css/dycgv.css">
<!-- jquery-3.4.1.min.js코드가 가장 위로 와야 실행이 된다. -->
<script src="http://localhost:9090/dycgv/js/jquery-3.4.1.min.js"></script>
<script src="http://localhost:9090/dycgv/js/dycgv.js"></script>

</head>
<body>
	<div>
		<!-- header 추가 -->
		<jsp:include page="../header.jsp"></jsp:include>
		<div id="content">
			<section>
				<img src="http://localhost:9090/dycgv/images/section1_img01.jpg" />
				<div>
					<h1>로그인 실패</h1>
					<!-- 로그인 폼 -->
					<img src="http://localhost:9090/dycgv/images/success.jpg">
				</div>
				<a href="http://localhost:9090/dycgv/index.do">
					<button type="button">홈으로</button>
				</a>
				<a href="http://localhost:9090/dycgv/login.do">
					<button type="button">로그인</button>
				</a>
			</section>
		</div>
		<!-- footer 추가 -->
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>
</body>
</html>