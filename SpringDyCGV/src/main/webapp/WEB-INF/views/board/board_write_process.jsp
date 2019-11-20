<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.dycgv.vo.BoardVO,com.dycgv.service.*"%>
<%-- <%@ page import="com.oreilly.servlet.*,com.oreilly.servlet.multipart.*,java.io.*"%>
<%
/* MultipartRequest(javax.servlet.http.HttpServletRequest request,java.lang.String saveDirectory,
					int maxPostSize,java.lang.String encoding, FileRenamePolicy policy) */
	String save_path = request.getServletContext().getRealPath("/upload");
	int max_size = 1024*1024*5; /* 5MB */
	MultipartRequest multi = 
		new MultipartRequest(request,save_path,max_size,"utf-8",new DefaultFileRenamePolicy());
	
	File file = new File(save_path+"/"+multi.getFilesystemName("bfile"));
	boolean result = false;
	
	//BoardVO 객체에 데이터 넣기
	BoardVO vo = new BoardVO();
	vo.setBtitle(multi.getParameter("btitle"));
	vo.setBcontent(multi.getParameter("bcontent"));
	
	if(file.exists()){
		vo.setBfile(multi.getOriginalFileName("bfile"));
		vo.setBsfile(multi.getFilesystemName("bfile"));
	
	}else{
		vo.setBfile(multi.getOriginalFileName(""));
		vo.setBsfile(multi.getFilesystemName(""));
	}
	
	//service 객체에 전송
	BoardCheckService checkservice = new BoardCheckService();
	result = checkservice.getBoardWriteResult(vo);
	
	if(result){
		response.sendRedirect("http://localhost:9090/dycgv/board/board_list.jsp");
	}else{
		response.sendRedirect("../errorPage.jsp");
	}
%> --%>

 <!-- 스트림 타입으로 넘어와서 이 코드로는 받을수 없음 -->
<%-- <% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="vo" class="com.dycgv.vo.BoardVO" />
<jsp:setProperty name="vo" property="*" /> --%>

<%-- <%
	BoardCheckService checkservice = new BoardCheckService();
	boolean result = checkservice.getBoardWriteResult(vo);
	
	if(result){
		response.sendRedirect("http://localhost:9090/dycgv/board/board_list.jsp");
	}else{
		response.sendRedirect("../erroPage.jsp");
	}
%> --%>