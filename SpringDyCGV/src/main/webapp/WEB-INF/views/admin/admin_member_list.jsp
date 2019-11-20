<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dycgv.dao.JoinDAO,com.dycgv.vo.MemberVO,com.dycgv.service.*,java.util.ArrayList" %>
<%	
/* 	String rpage = request.getParameter("page");	
	JoinCheckService service = new JoinCheckService();
	
	//페이징 처리 - startCount, endCount 구하기
	int startCount = 0;
	int endCount = 0;
	int pageSize = 5;	//한페이지당 게시물 수
	int reqPage = 1;	//요청페이지	
	int pageCount = 1;	//전체 페이지 수
	int dbCount = service.execTotalCount();	//DB에서 가져온 전체 행수
	
	//총 페이지 수 계산
	if(dbCount % pageSize == 0){
		pageCount = dbCount/pageSize;
	}else{
		pageCount = dbCount/pageSize+1;
	}
	
	//요청 페이지 계산
	if(rpage != null){
		reqPage = Integer.parseInt(rpage);
		startCount = (reqPage-1) * pageSize+1;
			endCount = reqPage *pageSize;
	}else{
		startCount = 1;
		endCount = 5;
	}
	
	ArrayList<MemberVO> list = service.getResultList(startCount, endCount); */
	ArrayList<MemberVO> list = (ArrayList<MemberVO>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CGV에 오신것을 환영합니다</title>
	<link rel="stylesheet" type="text/css" href="http://localhost:9090/dycgv/css/dycgv.css">
	<link rel="stylesheet" type="text/css" href="http://localhost:9090/dycgv/css/am-pagination.css">
	<script src="http://localhost:9090/dycgv/js/jquery-3.4.1.min.js"></script>
	<script src="http://localhost:9090/dycgv/js/am-pagination.js"></script>
	<script>
	$(document).ready(function(){
		
		var pager = jQuery('#ampaginationsm').pagination({
		
		    maxSize: 7,	    		// max page size
		    totals: '${dbCount}',	// total pages	
		    page: '${rpage}',		// initial page		
		    pageSize: '${pageSize}',			// max number items per page
		
		    // custom labels		
		    lastText: '&raquo;&raquo;', 		
		    firstText: '&laquo;&laquo;',		
		    prevText: '&laquo;',		
		    nextText: '&raquo;',
				     
		    btnSize:'sm'	// 'sm'  or 'lg'		
		});
		
		jQuery('#ampaginationsm').on('am.pagination.change',function(e){
			   jQuery('.showlabelsm').text('The selected page no: '+e.page);
	           $(location).attr('href', "http://localhost:9090/dycgv/admin_member.do?rpage="+e.page);         
	    });
		
		$("button").click(function(){
			var mid = $(this).attr("id");

			$.ajax({
				url:"admin_member_delete_proc.do?mid="+mid,
				success:function(result){
					if(result != 0){
						alert(mid+" 님의 정보가 삭제되었습니다.");
						location.reload();
					}else{
						alert("삭제 예정이 아닙니다.");
					}
				}
			});
		});
		
		
 	});
	</script>
</head>
<body>
	<div>		
	<!-- hearder 추가 -->
	<jsp:include page="../header.jsp"></jsp:include>
	
		<div id="content">
			<section>
				<img src="http://localhost:9090/dycgv/images/section1_img01.jpg"/>
				<div>
					<h1>회원관리</h1>
					<a href="admin_notice.do"><span>공지사항관리</span></a>
					<a href="admin_member.do"><span>회원관리</span></a>
					<a href="#"><span>영화리스트관리</span></a>
					<table class="notice_list">
						<tr>
							<th>번호</th>
							<th>이름</th>
							<th>연락처</th>
							<th>가입날짜</th>
							<th>등급</th>
							<th>회원탈퇴</th>
						</tr>
						<%for(int i=0;i<list.size();i++){%>
						<tr>
							<td><%=list.get(i).getRno() %></td>
							<td><a href="admin_member_content.do?id=<%=list.get(i).getId()%>"><%=list.get(i).getName() %></a></td>
							<td><%=list.get(i).getPhone_number() %></td>
							<td><%=list.get(i).getJdate() %></td>
							<td><%=list.get(i).getJgrade() %></td>
							<%if(list.get(i).getJstatus()==0){%>
							<td><button type="button">삭제미정</button></td>
							<%}else{ %>
							<td><button type="button" style="background:tomato;color:white;" id="<%=list.get(i).getId()%>">삭제</button></td>
							<%} %>
						</tr>
						<%} %>
						<tr>
							<!-- <td colspan=4>[이전] 1 2 3 4 5 [다음]</td> -->
							<%-- 페이지 네비게이션 시작 --%>						
							<td colspan=4><div id="ampaginationsm"></div></td>
							<%-- 페이지 네비게이션 종료  --%>
						</tr>
					</table>
				</div>
			</section>
		</div>
	
	<!-- footer 추가 -->
	<jsp:include page="../footer.jsp"></jsp:include>
	
	</div>
</body>
</html>