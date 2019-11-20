<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.dycgv.vo.NoticeVO,com.dycgv.service.*"%>
<%
	NoticeCheckService checkservice = new NoticeCheckService();
	boolean result = checkservice.getNoticeDeleteResult(request.getParameter("nid"));
	
	if(result){
		response.sendRedirect("http://localhost:9090/dycgv/admin/admin_notice_list.jsp");
	}else{
		response.sendRedirect("../erroPage.jsp");
	}
%>