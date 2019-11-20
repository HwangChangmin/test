<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dycgv.dao.NoticeDAO,com.dycgv.vo.NoticeVO,com.dycgv.service.*" %>
<%
/* 	int nid = Integer.parseInt(request.getParameter("nid"));
	NoticeDAO dao = new NoticeDAO();
	NoticeVO vo = dao.getNoticeContent(nid);
	
	NoticeCheckService notice = new NoticeCheckService();
	
	if(vo.getNtitle() != "" && vo.getNtitle() != null){
		notice.getResultUpdateNhits(nid);
		if(vo.getNcontent() != "" && vo.getNcontent() != null){
			vo.setNcontent(vo.getNcontent().replace("\r\n","<br>"));
		}else{
			vo.setNcontent("");
		}
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
<body>
	<div>		
	<!-- hearder 추가 -->
	<jsp:include page="../header.jsp"></jsp:include>
	
		<div id="content">
			<section>
				<img src="http://localhost:9090/dycgv/images/section1_img01.jpg"/>
				<div>
					<h1>공지사항관리</h1>
					<table class="notice_content">
						<tr>
							<th>제목</th>
							<td>${vo.ntitle }</td>
							<th>등록일</th>
							<td>${vo.ndate }</td>
							<th>조회수</th>
							<td>${vo.nhits }</td>
						</tr>
						<tr>
							<th>내용</th>
							<td colspan=5>
								<p>${vo.ncontent }</p>
								<%if(vo.getNsfile()!=null&&vo.getNsfile()!=""){ %>
								<img src="http://localhost:9090/dycgv/upload/<%=vo.getNsfile() %>"/>
								<%} %>
							</td>
						</tr>
						<tr>
							<td colspan=6>
								<a href="admin_notice_update.do?nid=${nid }"><button type="button">수정하기</button></a>
								<a href="admin_notice_delete.do?nid=${nid }"><button type="button">삭제하기</button></a>
								<a href="admin_notice.do"><button type="button">목록으로</button></a>
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