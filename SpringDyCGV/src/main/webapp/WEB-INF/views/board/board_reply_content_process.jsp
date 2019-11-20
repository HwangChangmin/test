<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dycgv.dao.*,com.dycgv.vo.*,com.dycgv.service.*,java.util.*" %>
<%	
	String rid = request.getParameter("rid");
	
 	//DB연동 - BoardReplyDAO 객체 생성 및 
 	BoardReplyDAO dao = new BoardReplyDAO();
 		
	String rcontent = dao.getBoardReplyContent(rid);

	out.write(rcontent);
%>