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
					<h1>공지사항관리</h1>
					<form action="admin_notice_write_proc.do" method="post" class="write" name="noticeForm">
							<!-- enctype="multipart/form-data"> -->
						<table class="admin_notice_write">
							<tr>
								<th>제목</th>
								<td><input type="text" name="ntitle" id="ntitle"></td>
							</tr>
							<tr>
								<th>내용</th>
								<td><textarea name="ncontent"></textarea></td>
							</tr>
							<tr>
								<th>파일</th>
								<td><input type="file" name="nfile"></td>
							</tr>
							<tr>
								<td colspan=2>
									<a href="#"><button type="button" id="btnRecNotice">등록하기</button></a>
									<a href="#"><button type="reset">다시쓰기</button></a>
									<a href="admin_notice.do"><button type="button">목록으로</button></a>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</section>
		</div>
	
	<!-- footer 추가 -->
	<jsp:include page="../footer.jsp"></jsp:include>
	
	</div>
</body>
</html>