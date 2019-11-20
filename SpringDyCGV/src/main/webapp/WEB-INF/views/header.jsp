<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/* include한 header에서도 index로 넘어온 값을 받아올 수 있다 */
	//boolean param = Boolean.valueOf(request.getParameter("result"));
	String loginAddr = "",loginMenu = "",succe_msg="",mycgvMenu="";
	String sid = (String)session.getAttribute("sid");
	String sname = (String)session.getAttribute("sname");
	
/* 	if(sid!=null&&sid!=""){
		loginAddr = "logout.jsp";
		loginMenu = "로그아웃";
		succe_msg=sid+" 님 환영합니다";
	}else{
		loginAddr = "login.jsp";
		loginMenu = "로그인";
	} */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<div>
			<nav> <!-- 메뉴 -->
				<ul>
					<%if(sid != null){ 
						if(sid.equals("admin")){
					%>		
						<li>${sessionScope.svo.name }님 환영합니다.</li>
						<li><a href="http://localhost:9090/dycgv/logout.do">로그아웃</a></li>
						<li><a href="http://localhost:9090/dycgv/mycgv.do">MyCGV</a></li>
						<li><a href="http://localhost:9090/dycgv/notice.do">공지사항</a></li>
						<li><a href="http://localhost:9090/dycgv/board.do">게시판</a></li>
						<li><a href="http://localhost:9090/dycgv/admin.do">Admin</a></li>
						<li><a href="#">ENGLISH TICKETING</a></li>
						<%}else { %>
						<li>${sessionScope.svo.name }님 환영합니다.</li>
						<li><a href="http://localhost:9090/dycgv/logout.do">로그아웃</a></li>
						<li><a href="http://localhost:9090/dycgv/mycgv.do">MyCGV</a></li>
						<li><a href="http://localhost:9090/dycgv/notice.do">공지사항</a></li>
						<li><a href="http://localhost:9090/dycgv/board.do">게시판</a></li>
						<li><a href="#">ENGLISH TICKETING</a></li>
						<%} %>
					<%}else { %>
						<li><a href="http://localhost:9090/dycgv/login.do">로그인</a></li>
						<li><a href="http://localhost:9090/dycgv/join.do">회원가입</a></li>
						<li><a href="http://localhost:9090/dycgv/mycgv.do">MyCGV</a></li>
						<li><a href="http://localhost:9090/dycgv/notice.do">공지사항</a></li>
						<li><a href="http://localhost:9090/dycgv/board.do">게시판</a></li>		
						<li><a href="#">ENGLISH TICKETING</a></li>
					<%} %>
				</ul>
			</nav>
			<div>
				<a href="http://localhost:9090/dycgv/index.do">
					<img src="http://localhost:9090/dycgv/images/h1_cgv.png"/>
				</a>
				<div>
					<img src="http://localhost:9090/dycgv/images/h2_cultureplex.png"/>
					<nav>
						<ul>
							<li><a href="#">영화</a></li>
							<li><a href="#">예매</a></li>
							<li><a href="#">극장</a></li>
							<li><a href="#">이벤트&컬처</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</header>
</body>
</html>