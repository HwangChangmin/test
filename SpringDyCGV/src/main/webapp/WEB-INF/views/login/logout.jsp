<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String sid = (String)session.getAttribute("sid");
	
	if(sid != null) {
		session.invalidate();
		response.sendRedirect("../index.jsp");
	}
%>