<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dycgv.dao.NoticeDAO,com.dycgv.vo.NoticeVO" %>
<%
/* 	NoticeDAO dao = new NoticeDAO();
	NoticeVO vo = dao.getNoticeContent(Integer.parseInt(request.getParameter("nid"))); */
%>
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
					<h1>공지사항관리</h1>
					<form action="admin_notice_update_proc.do" method="post" class="update">
							<!-- enctype="multipart/form-data"> -->
						<table class="board_update">
							<tr>
								<th>제목</th>
								<td><input type="text" name="ntitle" value="${vo.ntitle }"></td>
							</tr>
							<tr>
								<th>내용</th>
								<td><textarea name="ncontent">${vo.ncontent }</textarea></td>
							</tr>
							<tr>
								<th>파일</th>
								<td><input type="file" name="nfile">${vo.nfile }</td>
							</tr>
							<tr>
								<td colspan=2>
									<!-- 링크는 쿼리스트링 - FORM을 사용할 때는 submit버튼을 a(링크)에 넣으면 안됨 -->
									<button type="submit">수정완료</button>
									<button type="reset">취소하기</button>
									<a href="admin_notice.do"><button type="button">목록으로</button></a>
									<a href="admin_notice_content.do?nid=${nid }"><button type="button">이전페이지</button></a>
									<input type="hidden" name="nid"  value="${nid }">
									<input type="hidden" name="rpage" value="${rpage}">
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