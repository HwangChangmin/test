<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dycgv.dao.*,com.dycgv.vo.*,com.dycgv.service.*,java.util.*" %>
<%	
	String rcontent = request.getParameter("rcontent");
	String bid = request.getParameter("bid");
	
 	//DB연동 - BoardReplyDAO 객체 생성 및 
 	BoardReplyDAO dao = new BoardReplyDAO();
 	BoardReplyVO vo = new BoardReplyVO();
	vo.setBid(Integer.parseInt(bid));
	vo.setRcontent(rcontent);
 		
	int result = dao.getBoardReplyResult(vo);

	out.write(String.valueOf(result));
%>