<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><title>반려견 커뮤니티</title>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css"> 
<script src="https://code.jquery.com/jquery-1.9.0.min.js"
	integrity="sha256-f6DVw/U4x2+HjgEqw5BZf67Kq/5vudRZuRkljnbF344="
	crossorigin="anonymous"></script>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="menu.jsp"></jsp:include>
		<div id="contents">
			<div class="text">
				<h3>
					사람한테 받는 위로와<br> 그들한테 받는 위로는 달라요.<br> 그들은 우리한테 이유를 묻지 않아요<br>
					그냥 당신이기 때문에 좋아해요.
				</h3>
				<p class="right">강형욱 훈련사</p>
			</div>
			<ul class="circle_imgbox">
				<li class="circle_off"></li>
				<li class="circle_off"></li>
				<li class="circle_off"></li>
				<li class="circle_off"></li>
			</ul>
		</div> 
		
		<jsp:include page="footer.jsp"></jsp:include>

	</div>

</body>
</html>