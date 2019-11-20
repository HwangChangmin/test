<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.dycgv.vo.NoticeVO,com.dycgv.service.*"%>
<%-- <%@ page import="com.oreilly.servlet.*,com.oreilly.servlet.multipart.*,java.io.*" %>
<%	
	String save_path = request.getServletContext().getRealPath("/upload");
	int max_size = 1024*1024*5; /* 5MB */
	MultipartRequest multi = 
		new MultipartRequest(request,save_path,max_size,"utf-8",new DefaultFileRenamePolicy());
	
	File file = new File(save_path+"/"+multi.getFilesystemName("nfile"));
	boolean result = false;
	
	//NoticeVO 객체에 데이터 넣기
	NoticeVO vo = new NoticeVO();
	vo.setNtitle(multi.getParameter("ntitle"));
	vo.setNcontent(multi.getParameter("ncontent"));
	
	if(file.exists()){
		vo.setNfile(multi.getOriginalFileName("nfile"));
		vo.setNsfile(multi.getFilesystemName("nfile"));
	
	}else{
		vo.setNfile(multi.getOriginalFileName(""));
		vo.setNsfile(multi.getFilesystemName(""));
	}
	
	NoticeCheckService checkservice = new NoticeCheckService();
	result = checkservice.getNoticeWriteResult(vo);
	
	if(result){
		response.sendRedirect("http://localhost:9090/dycgv/admin/admin_notice_list.jsp");
	}else{
		response.sendRedirect("../erroPage.jsp");
	}
%> --%>