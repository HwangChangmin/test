<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.dycgv.vo.NoticeVO,com.dycgv.service.*"%>
<%-- <%@ page import="com.oreilly.servlet.*,com.oreilly.servlet.multipart.*,java.io.*" %>
<%

	String save_path = request.getServletContext().getRealPath("/upload");
	int max_size = 1024*1024*5; /* 5MB */
	MultipartRequest multi = 
		new MultipartRequest(request,save_path,max_size,"utf-8",new DefaultFileRenamePolicy());
		
	File file = new File(save_path+"/"+multi.getFilesystemName("nfile"));
	
	//BoardVO 객체에 데이터 넣기
	NoticeCheckService checkservice = new NoticeCheckService();
	NoticeVO vo = new NoticeVO();
	vo.setNid(Integer.parseInt(multi.getParameter("nid")));
	vo.setNtitle(multi.getParameter("ntitle"));
	vo.setNcontent(multi.getParameter("ncontent"));
	boolean result = false;
	
	if(file.exists()){
		//새로운 파일 업데이트
		vo.setNfile(multi.getOriginalFileName("nfile"));
		vo.setNsfile(multi.getFilesystemName("nfile"));
		result = checkservice.getNoticeUpdateResult(vo); //전체데이터 업데이트
	}else{
		result = checkservice.getNoticeUpdateNofile(vo); //타이틀,내용 업데이트
	}
	
	if(result){
		response.sendRedirect("http://localhost:9090/dycgv/admin/admin_notice_content.jsp?nid="+vo.getNid());
	}else{
		response.sendRedirect("../erroPage.jsp");
	}
%> --%>