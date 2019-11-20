<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dycgv.dao.*,com.dycgv.vo.*,com.dycgv.service.*,java.util.*" %>
<%
	/* int bid = Integer.parseInt(request.getParameter("bid"));
	String rpage = request.getParameter("page");
	if(rpage=="" || rpage==null){
		rpage = "1";
	}
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
	}else{
		response.sendRedirect("../errorPage.jsp");
	}
	
 	BoardReplyDAO rdao = new BoardReplyDAO();
	ArrayList<BoardReplyVO> rlist = rdao.getBoardReplyList(bid); */
	
	BoardVO vo = (BoardVO)request.getAttribute("vo");
	ArrayList<BoardReplyVO> rlist = (ArrayList<BoardReplyVO>)request.getAttribute("rlist");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CGV에 오신것을 환영합니다</title>
	<link rel="stylesheet" type="text/css" href="http://localhost:9090/dycgv/css/dycgv.css">
	<script src="http://localhost:9090/dycgv/js/jquery-3.4.1.min.js"></script>
 	<script>
		$(document).ready(function(){			
			$("#btnReply").click(function(){
				var rep_title = $("#btnReply").text();
				var rep_content = $("#b_rep").val();

				if(rep_title == "댓글쓰기"){
					var str_rep = "<tr id='b_tr'><td colspan='6'> "
								+"<textarea id='b_rep'></textarea></td></tr>";
								
					$("tr:last-child").before(str_rep);
					$("#b_rep").css("width","95%").css("height","100px")
					.css("border","1px solid #ccc").css("border-radius","4px")
					.css("padding","10px");
					
					$("#btnReply").text("댓글완료");
				}else{

					if(rep_content == ""){
						alert("댓글을 작성하세요.");
						$("#b_rep").focus();
					}else{
						if(rep_content.length>200){
							alert("200글자 이내로 작성해 주세요");
							$("#b_rep").focus();
						}else{
							//Ajax를 통한 DB연동
							$.ajax({
								url:"board_reply_write_proc.do?rcontent="+rep_content+"&bid=${bid}",
								type:"POST",
								async:false,
								success:function(result){
									if(result=="1"){
										alert("댓글 등록 성공");
										$("tr#b_tr").css("display","none");
										$("#btnReply").text("댓글쓰기");
										location.reload();
									}else{
										alert("댓글 등록 실패");
									}
								}
							});	
							

						}
					}
				}
			});
			
			$("span.rep_s1").click(function(){
				//ajax를 이용하여 댓글 내용 불러오기
				
				var rid = $(this).attr("id");
				$.ajax({
					url:"board_reply_content_process.do?rid="+rid,							
					success:function(result){
						$(".rep_d3").text(result);
					}
				});	
				
				var li_addr = "li#"+rid;
				var str = "<div class='rep_d1'>"
							+"<div class='rep_d2'>x</div><div class='rep_d3'>댓글 내용 보기</div>"
						+"</div>";
				$(".rep_d1").css("display","none");
				$(li_addr).after(str);
				
				$(".rep_d2").click(function(){
					$(".rep_d1").css("display","none");
				});
			});
			
		});
	</script>
	<style>
		span.rep_s1:hover {
			text-decoration: underline;
			cursor:pointer;
		}
		div.rep_d1 {
			border:1px solid #ccc;
			padding:10px;
			text-align:left;
		}
		div.rep_d2 {
			float:right;
			cursor:pointer;
			width:10px;
			padding:2px 5px 5px 5px;
			background:blue;
			color:white;
		}
		div.rep_d3 {
			display:inline-block;
			text-align:left;
			word-break:break-all;
		}
	</style>
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
					<table class="board_content">
						<tr>
							<th>제목</th>
							<td>${vo.btitle }</td>
							<th>등록일</th>
							<td>${vo.bdate }</td>
							<th>조회수</th>
							<td>${vo.bhits }</td>
						</tr>
						<tr>
							<th>내용</th>
							<td colspan=5>
								<p><%=vo.getBcontent() %></p>
								<br>
								<%if(vo.getBsfile()!=null&&vo.getBsfile()!=""){ %>
								<img src="http://localhost:9090/dycgv/upload/<%=vo.getBsfile() %>"/>
								<%} %>
							</td>
						</tr>
						<tr>
							<td colspan=6>
								<div style="text-align:left;">댓글목록[<%=vo.getRcount() %>]</div>
								<ul style="width:600px;display:inline-block;">
									<%for(BoardReplyVO rvo : rlist){ %>
									<li id="<%=rvo.getRid() %>" style="padding-right:0px;">
										<span class="rep_s1" id="<%=rvo.getRid() %>" style="display:inline-block;float:left;width:70%;
													height:18px;overflow:hidden;text-align:left;"><%=rvo.getRcontent() %></span>									
										<span style="display:inline-block;float:right;width:30%;height:18px;"><%=rvo.getRdate() %></span>
									</li>
									<%} %>
								</ul>
							</td>
						</tr>
<!-- 						<tr id="board_reply_write_tr">
							<td colspan=6>
								<textarea name="board_reply_write" id="board_reply_write"></textarea>
							</td>
						</tr> -->
						<tr>
							<td colspan=6>
								<button type="button" id="btnReply">댓글쓰기</button>
								<a href="board_update.do?bid=${bid}"><button type="button">수정하기</button></a>
								<a href="board_delete.do?bid=${bid}"><button type="button">삭제하기</button></a>
								<a href="board.do?page=${bpage}"><button type="button">목록으로</button></a>
							</td>
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