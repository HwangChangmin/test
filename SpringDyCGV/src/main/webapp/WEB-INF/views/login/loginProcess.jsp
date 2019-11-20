<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.dycgv.vo.*,com.dycgv.service.*"%>
<%
	//LoginVO 객체 생성 및 데이터 입력
	LoginVO vo = new LoginVO();
	vo.setId(request.getParameter("id"));
	vo.setPass(request.getParameter("pass"));
	
	//LoginCheck 객체 생성 및 메소드 호출
	//com.dycgv.service.LoginCheckService.getLoginResult(vo);
	LoginCheckService checkservice = new LoginCheckService();
	SessionVO svo = checkservice.getLoginResult(vo);
	
	if(svo.isResult()){
		//세션 객체에 아이디 넣기
		session.setAttribute("sid",vo.getId());		
		session.setAttribute("sname",svo.getName());	
		response.sendRedirect("http://localhost:9090/dycgv/index.jsp");
	}else{
		response.sendRedirect("http://localhost:9090/dycgv/login/login.jsp");
	}
%>