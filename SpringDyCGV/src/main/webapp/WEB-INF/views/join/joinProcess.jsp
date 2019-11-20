<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.dycgv.vo.JoinVO,com.dycgv.service.*"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="vo" class="com.dycgv.vo.JoinVO"></jsp:useBean>
<jsp:setProperty name="vo" property="*"></jsp:setProperty>
<%
	//회원가입 실행 객체 생성 및 데이터 전송
	JoinCheckService joinCheck = new JoinCheckService();
	boolean result = joinCheck.getJoinResult(vo);
	
	if(result){
		//로그인 페이지로 이동
		response.sendRedirect("http://localhost:9090/dycgv/login/login.jsp?result="+result);
	}else{
		//에러페이지로 이동
		response.sendRedirect("http://localhost:9090/dycgv/errorPage.jsp");
	}

%>
