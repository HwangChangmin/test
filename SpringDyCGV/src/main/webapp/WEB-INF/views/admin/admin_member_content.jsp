<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dycgv.dao.JoinDAO,com.dycgv.vo.MemberVO,com.dycgv.service.*" %>
<%
/* 	JoinCheckService service = new JoinCheckService();
	MemberVO vo = service.getResultMember(request.getParameter("id")); */
	MemberVO vo = (MemberVO)request.getAttribute("vo");
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
					<h1>회원관리</h1>
					<table class="admin_member_content">
						<tr>
							<th>이름</th>
							<td>${vo.name }</td>
							<th>연락처</th>
							<td>${vo.phone_number}</td>
							<th>가입날짜</th>
							<td>${vo.jdate}</td>
							<th>회원등급</th>
							<td>${vo.jgrade}</td>
						</tr>
						<tr>
							<th>주소</th>
							<td colspan=5 style=text-align:left;padding-left:5px>
								${vo.addr }
							</td>
							<th>이메일</th>
							<td colspan=5 style=text-align:left;padding-left:5px>
								${vo.email }
							</td>
							<th>관심분야</th>
							<td colspan=5 style=text-align:left;padding-left:5px>
								${vo.getDy_hobby()}
							</td>
							<th>자기소개</th>
							<td colspan=5 style=text-align:left;padding-left:5px>
								${vo.intro }
							</td>
						</tr>
						<tr>
							<td colspan=6>
								<a href="admin_member.do"><button type="button">목록으로</button></a>
								<a href="admin.do"><button type="button">관리자홈</button></a>
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