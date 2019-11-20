<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.dycgv.vo.JoinVO,com.dycgv.service.*,com.dycgv.dao.*"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="vo" class="com.dycgv.vo.JoinVO"></jsp:useBean>
<jsp:setProperty name="vo" property="*"></jsp:setProperty>
<%
	JoinDAO dao = new JoinDAO();
	int result =dao.getResultCheckId(request.getParameter("jid"));
	out.write(String.valueOf(result));
%>
