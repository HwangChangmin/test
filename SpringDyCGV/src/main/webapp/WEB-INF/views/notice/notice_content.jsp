<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dycgv.dao.NoticeDAO,com.dycgv.vo.NoticeVO,com.dycgv.service.*" %>
<%
/* 	NoticeDAO dao = new NoticeDAO();
	NoticeCheckService check = new NoticeCheckService();
	NoticeVO vo = dao.getNoticeContent(Integer.parseInt(request.getParameter("nid")));
	
	if(vo.getNtitle() != "" && vo.getNtitle() != null){
		vo.setNcontent(vo.getNcontent().replace("\r\n","<br>"));
		check.getResultUpdateNhits(vo.getNid());
	}else{
		response.sendRedirect("../errorPage.jsp");
	} */
	
	NoticeVO vo = (NoticeVO)request.getAttribute("vo");

%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CGV에 오신것을 환영합니다</title>
	<link rel="stylesheet" type="text/css" href="http://localhost:9090/dycgv/css/dycgv.css">
</head>
<script>
</script>
<body>
	<div>		
	<!-- hearder 추가 -->
	<jsp:include page="../header.jsp"></jsp:include>
	
		<div id="content">
			<section>
				<img src="http://localhost:9090/dycgv/images/section1_img01.jpg"/>
				<div>
					<h1>공지사항</h1>
					<table class="notice_content">
						<tr>
							<th>제목</th>
							<td><%=vo.getNtitle() %></td>
							<th>등록일</th>
							<td><%=vo.getNdate()%></td>
							<th>조회수</th>
							<td><%=vo.getNhits()+1%></td>
						</tr>
						<tr>
							<th>내용</th>
							<td colspan=5>
								<p><%=vo.getNcontent() %></p>
								<%if(vo.getNsfile()!=null&&vo.getNsfile()!=""){ %>
								<img src="http://localhost:9090/dycgv/upload/<%=vo.getNsfile() %>"/>
								<%} %>
							</td>
						</tr>
						<tr>
							<td colspan=6>
								<a href="notice.do"><button type="button">목록으로</button></a>
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