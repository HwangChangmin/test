<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <% String bid = request.getParameter("bid"); %> --%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CGV에 오신것을 환영합니다</title>
	<link rel="stylesheet" type="text/css" href="http://localhost:9090/dycgv/css/dycgv.css">
</head>
<body>
	<div>		
	<!-- hearder 추가 -->
	<jsp:include page="../header.jsp"></jsp:include>
	
		<div id="content">
			<section>
				<img src="http://localhost:9090/dycgv/images/section1_img01.jpg"/>
				<div>
					<h1>게시판</h1>
					<table class="board_delete">
						<tr>
							<td>정말로 삭제하시겠습니까?</td>
						</tr>
						<tr>
							<td>
								<a href="board_delete_proc.do?bid=${bid}"><button type="button">삭제완료</button></a>
								<a href="board.do"><button type="button">목록으로</button></a>
								<a href="board_content.do?bid=${bid}"><button type="button">이전페이지</button></a>
							</td>
						</tr>
					</table>
				</div>
			</section>
		</div>
	
	<!-- footer 추가 -->
	<jsp:include page="../footer.jsp"></jsp:include>
	
	</div>
</body>
</html>