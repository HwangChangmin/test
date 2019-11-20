<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dycgv.dao.BoardDAO,com.dycgv.vo.BoardVO,com.dycgv.service.*" %>
<%
/* 	int bid = Integer.parseInt(request.getParameter("bid"));
	BoardDAO dao = new BoardDAO();
	BoardVO vo = dao.getBoardContent(bid);
	
	BoardCheckService board = new BoardCheckService();
	
	if(vo.getBtitle() != "" && vo.getBtitle() != null){
		board.getResultUpdateBhits(bid);
		if(vo.getBcontent() != "" && vo.getBcontent() != null){
			vo.setBcontent(vo.getBcontent().replace("\r\n","<br>"));
		}else{
			vo.setBcontent("");
		}
		 if(vo.getBfile() == "" || vo.getBfile() == null){
			vo.setBfile("");
		} 
	}else{
		response.sendRedirect("../errorPage.jsp");
	} */
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CGV에 오신것을 환영합니다</title>
	<link rel="stylesheet" type="text/css" href="http://localhost:9090/dycgv/css/dycgv.css">
	<script src="http://localhost:9090/MyJSP/jquery/jquery-3.4.1.min.js"></script>
	<style>
		#bfile_loc {
			display:block;
			position:relative;
			width:120px;
			top:-29px;
			left:83px;
			background:white;
			padding:3px;
			height:18px;
		}
	</style>
	<script>
		$(document).ready(function(){
			//파일 선택
			$("#bfile").change(function(){
				//FileReader
				if(window.FileReader){
					//alert($(this)[0].files[0].name);
					$("#bfile_loc").text("").text($(this)[0].files[0].name);
				}
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
					<h1>게시판</h1>
					<form action="board_update_proc.do" method="post" class="update">
							<!-- enctype="multipart/form-data"> -->
						<table class="board_update">
							<tr>
								<th>제목</th>
								<td><input type="text" name="btitle" value="${vo.btitle}"></td>
							</tr>
							<tr>
								<th>내용</th>
								<td><textarea name="bcontent">${vo.bcontent }</textarea></td>
							</tr>
							<tr>
								<th>파일</th>
								<td>
									<input type="file" name="bfile" id="bfile">
									<%BoardVO vo = (BoardVO)request.getAttribute("vo"); %>
									<%if(vo.getBfile() !="" && vo.getBfile() != null){ %>
									<span id="bfile_loc"><%=vo.getBfile()%></span>
									<%}else{ %>
									<span id="bfile_loc"></span>
									<%} %>
								</td>
							</tr>
							<tr>
								<td colspan=2>
									<button type="submit">수정완료</button>
									<button type="reset">취소하기</button>
									<a href="board.do"><button type="button">목록으로</button></a>
									<a href="board_content.do?bid=${bid}"><button type="button">이전페이지</button></a>
									<input type="hidden" name="bid" value="${bid}">
									<input type="hidden" name="bpage" value="${bpage}">
								</td>
							</tr>
						</table>
					</form>
				</div>
			</section>
		</div>
	
	<!-- footer 추가 -->
	<jsp:include page="../footer.jsp"></jsp:include>
	
	</div>
</body>
</html>