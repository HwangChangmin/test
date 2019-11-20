<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CGV에 오신것을 환영합니다</title>
	<link rel="stylesheet" type="text/css" href="http://localhost:9090/dycgv/css/dycgv.css">
	
	<link rel="stylesheet" type="text/css" href="http://localhost:9090/dycgv/css/index_carousel.css">
	
	<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"> -->
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	
	<script type="text/javascript" src="jquery.scrollfollow.js"></script>	
</head>
<body>
	<div>		
	<!-- hearder 추가 -->
	<jsp:include page="header.jsp"></jsp:include>
	
		<div id="content">
			<section>
				<img src="http://localhost:9090/dycgv/images/section1_img01.jpg"/>
				<!-- <h1>캐러셀 추가 부분</h1>
				<img src="http://localhost:9090/dycgv/images/section1_img02.jpg"/> -->
				<article>
					<div id="demo" class="carousel slide" data-ride="carousel">
											
						<!-- Indicators -->
						<ul class="carousel-indicators">
						    <li data-target="#demo" data-slide-to="0" class="active"></li>
						    <li data-target="#demo" data-slide-to="1"></li>
						    <li data-target="#demo" data-slide-to="2"></li>
						    <li data-target="#demo" data-slide-to="3"></li>
						</ul>
						  
						<!-- The slideshow -->
						<div class="carousel-inner">
							<div class="carousel-item active">
						    	<img src="http://localhost:9090/dycgv/images/15689608448190.jpg" alt="Los Angeles" width="980" height="450">
						  	</div>
						  	<div class="carousel-item">
						    	<img src="http://localhost:9090/dycgv/images/mainbig_new_3.jpg" alt="Chicago" width="980" height="450">
						  	</div>
						  	<div class="carousel-item">
						    	<img src="http://localhost:9090/dycgv/images/section1_img02.jpg" alt="New York" width="980" height="450">
						  	</div>
						  	<div class="carousel-item">
						    	<img src="http://localhost:9090/dycgv/images/section1_img02.jpg" alt="New York" width="980" height="450">
						  	</div>
						</div>
						
						<!-- Left and right controls -->
						<a class="carousel-control-prev" href="#demo" data-slide="prev">
						 	<span class="carousel-control-prev-icon"></span>
						</a>
						<a class="carousel-control-next" href="#demo" data-slide="next">
							<span class="carousel-control-next-icon"></span>
						</a>
					</div>
					
				</article>
			</section>
				
			<section>
				<img src="http://localhost:9090/dycgv/images/h3_movie_selection.gif"/>
				<div>
					<iframe width="700" height="388" src="https://www.youtube.com/embed/1OJZSa5258A" 
					frameborder="0" allow="accelerometer; autoplay; encrypted-media; 
					gyroscope; picture-in-picture" allowfullscreen></iframe>
					<img src="http://localhost:9090/dycgv/images/movie_image.jpg" width="278" height="388"/>
				</div>
			</section>
				
			<section>
				<img src="http://localhost:9090/dycgv/images/h3_event.gif"/>
				<article>
					<img src="http://localhost:9090/dycgv/images/section3_img01.jpg" />
					<img src="http://localhost:9090/dycgv/images/section3_img02.jpg" />
					<img src="http://localhost:9090/dycgv/images/section3_img03.jpg" />
					<img src="http://localhost:9090/dycgv/images/section3_img04.jpg" />
				</article>
				
				<article>
					<img src="http://localhost:9090/dycgv/images/section3_img05.jpg" />
					<img src="http://localhost:9090/dycgv/images/section3_img06.jpg" />
					<img src="http://localhost:9090/dycgv/images/section3_img07.jpg" />
				</article>
			</section>
		</div>
	
	<!-- footer 추가 -->
	<jsp:include page="footer.jsp"></jsp:include>
	
	</div>
</body>
</html>