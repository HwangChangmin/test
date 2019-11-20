<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.dycgv.vo.BoardVO,com.dycgv.service.*"%>
<%-- <%@ page import="com.oreilly.servlet.*,com.oreilly.servlet.multipart.*,java.io.*" %>
<%
	String save_path = request.getServletContext().getRealPath("/upload");
	int max_size = 1024*1024*5; /* 5MB */
	MultipartRequest multi = 
		new MultipartRequest(request,save_path,max_size,"utf-8",new DefaultFileRenamePolicy());
		
	File file = new File(save_path+"/"+multi.getFilesystemName("bfile"));
	
	//BoardVO 객체에 데이터 넣기
	BoardCheckService checkservice = new BoardCheckService();
	BoardVO vo = new BoardVO();
	vo.setBid(Integer.parseInt(multi.getParameter("bid")));
	vo.setBtitle(multi.getParameter("btitle"));
	vo.setBcontent(multi.getParameter("bcontent"));
	boolean result = false;
	
	if(file.exists()){
		//새로운 파일 업데이트
		vo.setBfile(multi.getOriginalFileName("bfile"));
		vo.setBsfile(multi.getFilesystemName("bfile"));
		result = checkservice.getBoardUpdateResult(vo); //전체데이터 업데이트
	}else{
		result = checkservice.getBoardUpdateNofile(vo); //타이틀,내용 업데이트
	}
	
	
	
	
	if(result){
		response.sendRedirect("http://localhost:9090/dycgv/board/board_content.jsp?bid="+vo.getBid());
	}else{
		response.sendRedirect("../errorPage.jsp");
	}
%> --%>