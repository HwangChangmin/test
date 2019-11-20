<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dycgv.dao.NoticeDAO,com.dycgv.vo.NoticeVO,com.dycgv.service.*,java.util.ArrayList" %>
<%
	ArrayList<NoticeVO> list = (ArrayList<NoticeVO>)request.getAttribute("list");
%>
<%-- <%
	String rpage = request.getParameter("page");	
	NoticeCheckService notice = new NoticeCheckService();
	
	//페이징 처리 - startCount, endCount 구하기
	int startCount = 0;
	int endCount = 0;
	int pageSize = 5;	//한페이지당 게시물 수
	int reqPage = 1;	//요청페이지	
	int pageCount = 1;	//전체 페이지 수
	int dbCount = notice.execTotalCount();	//DB에서 가져온 전체 행수
	
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

	
	ArrayList<NoticeVO> list = notice.getResultList(startCount, endCount);
%> --%>
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
	           $(location).attr('href', "http://localhost:9090/dycgv/notice.do?rpage="+e.page);         
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
					<h1>공지사항</h1>
					<table class="notice_list">
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>등록일</th>
							<th>조회수</th>
						</tr>
 						<%for(int i=0;i<list.size();i++){ %>
						<tr>
							<td><%= list.get(i).getRowNum() %></td>
							<td><a href="notice_content.do?nid=<%=list.get(i).getNid()%>"><%=list.get(i).getNtitle()%></a></td>
							<td><%= list.get(i).getNdate() %></td>
							<td><%= list.get(i).getNhits() %></td>
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