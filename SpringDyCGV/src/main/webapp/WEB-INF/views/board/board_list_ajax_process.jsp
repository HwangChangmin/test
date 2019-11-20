<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page import="com.dycgv.dao.*,com.dycgv.vo.*,java.util.*,com.google.gson.*" %>
<%
	//1. DB 연동
	BoardDAO dao = new BoardDAO();
	ArrayList<BoardVO> list = dao.getBoardList();
	
	//2. JSON 객체 생성 및 전송
	Gson gson = new Gson();
	JsonArray jlist = new JsonArray();
	JsonObject jdata = new JsonObject();

	for(BoardVO vo:list){
		JsonObject obj = new JsonObject();
		obj.addProperty("rno", vo.getRno());
		obj.addProperty("bid", vo.getBid());
		obj.addProperty("btitle", vo.getBtitle());
		obj.addProperty("bcontent", vo.getBcontent());
		obj.addProperty("bdate", vo.getBdate());
		obj.addProperty("bhits", vo.getBhits());
		obj.addProperty("bfile", vo.getBfile());
		obj.addProperty("bsfile", vo.getBsfile());
		
		jlist.add(obj);
	}
	
	jdata.add("list", jlist);
	
	out.write(gson.toJson(jdata));
%> --%>