<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.dycgv.vo.BoardVO,com.dycgv.service.*"%>
<%
	BoardCheckService checkservice = new BoardCheckService();
	boolean result = checkservice.getBoardDeleteResult(request.getParameter("bid"));
	
	if(result){
		response.sendRedirect("http://localhost:9090/dycgv/board/board_list.jsp");
	}else{
		response.sendRedirect("../erroPage.jsp");
	}
%>