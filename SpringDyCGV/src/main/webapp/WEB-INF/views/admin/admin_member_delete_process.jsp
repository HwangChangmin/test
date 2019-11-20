<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dycgv.dao.*,com.dycgv.vo.*" %>
<%
	String mid = request.getParameter("mid");
	
	JoinDAO dao = new JoinDAO();
	int result = dao.getResultDelete(mid);

	out.write(result);
%>