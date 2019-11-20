<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page import="com.dycgv.dao.*,com.dycgv.vo.*,java.util.*,com.google.gson.*" %>
<%
	String bid = request.getParameter("bid");
	//1. DB 연동
	BoardReplyDAO dao = new BoardReplyDAO();
	ArrayList<BoardReplyVO> list = dao.getBoardReplyList(bid);
	
	//2. JSON 객체 생성 및 전송
	Gson gson = new Gson();
	JsonArray jlist = new JsonArray();
	JsonObject jdata = new JsonObject();

	for(BoardReplyVO vo:list){
		JsonObject obj = new JsonObject();
		obj.addProperty("rid", vo.getRid());
		obj.addProperty("rhits", vo.getRhits());
		obj.addProperty("bid", vo.getBid());
		obj.addProperty("rcontent", vo.getRcontent());
		obj.addProperty("rdate", vo.getRdate());

		jlist.add(obj);
	}
	
	jdata.add("list", jlist);
	
	out.write(gson.toJson(jdata));
%> --%>