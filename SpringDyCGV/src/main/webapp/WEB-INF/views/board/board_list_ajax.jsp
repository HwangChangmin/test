<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="http://localhost:9090/dycgv/css/dycgv.css">
	<script src="http://localhost:9090/dycgv/js/jquery-3.4.1.min.js"></script>
	<script>
		$(document).ready(function(){
			$("table.notice_list").append("<tr><th>번호</th>"+"<th>제목</th>"
					+"<th>등록일</th>"+"<th>조회수</th>"		
				+"</tr>");
			$.ajax({
				 url: "board_list_ajax_process.jsp",
				 success: function(data){
					 var jsonObj = JSON.parse(data); //Json타입으로 변경

					 for(i=0;i<jsonObj.list.length;i++){
						 var str = "<tr>"
					 			+"<td>"+jsonObj.list[i].rno+"</td>"
					 			+"<td><a href='board_content.jsp?bid="+jsonObj.list[i].bid+"'>"+jsonObj.list[i].btitle+"</a></td>"
					 			+"<td>"+jsonObj.list[i].bdate+"</td>"+"<td>"+jsonObj.list[i].bhits+"</td>"
						 $("table.notice_list").append(str);
					 }
					 $("table.notice_list").append("<tr><td colspan=4>[이전] 1 2 3 4 5 [다음]</td></tr>");
			}});
			
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
					<h1>게시판</h1>
					<div class="board_list_btn1">
						<a href="board_write.jsp"><button type="button">글쓰기</button></a>
					</div>
					<table class="notice_list">
					</table>
				</div>
			</section>
		</div>
	
	<!-- footer 추가 -->
	<jsp:include page="../footer.jsp"></jsp:include>
	
	</div>
</body>
</html>