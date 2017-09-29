<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<title>반려견 커뮤니티 - 관리자 페이지</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style>
html, body, h1, h2, h3, h4, h5 {
	font-family: "Raleway", sans-serif
}
</style>
<body class="w3-light-grey">

	<!-- Top container -->
	<div class="w3-bar w3-top w3-black w3-large" style="z-index: 4">
		<button
			class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey"
			onclick="w3_open();">
			<i class="fa fa-bars"></i>Menu
		</button>
		<span class="w3-bar-item w3-left"><i class="fa fa-paw fa-2x"
			aria-hidden="true"></i></span>
	</div>

	<!-- Sidebar/menu -->
	<nav class="w3-sidebar w3-collapse w3-white w3-animate-left"
		style="z-index: 3; width: 300px;" id="mySidebar">
		<br>
		<div class="w3-container">
			<h5>관리자</h5>
		</div>
		<div class="w3-bar-block">
			<a href="#"
				class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black"
				onclick="w3_close()" title="close menu"><i
				class="fa fa-remove fa-fw"></i>  Close Menu</a> 
				<a href="/DaengDaeng/admin/stats/admin_statuser_visit.do"
				class="w3-bar-item w3-button w3-padding w3-blue">
				<i class="fa fa-pie-chart fa-fw"></i>통계</a> 
				<a href="/DaengDaeng/admin/member/admin_memberlistForm.do"
				class="w3-bar-item w3-button w3-padding"><i class="fa fa-user-circle-o fa-fw"></i>회원관리</a> 
				<a href="/DaengDaeng/admin/lost/admin_lostlistForm.do" class="w3-bar-item w3-button w3-padding">
				<i class="fa fa-bell fa-fw"></i>실종신고</a>
			<script>
				// Accordion 
				function myAccFunc() {
					var x = document.getElementById("demoAcc");
					if (x.className.indexOf("w3-show") == -1) {
						x.className += " w3-show";
					} else {
						x.className = x.className.replace(" w3-show", "");
					}
				}
			</script>
			<a onclick="myAccFunc()" href="javascript:void(0)"
				class="w3-button w3-block w3-white w3-left-align" id="myBtn"> <i
				class="fa fa-pencil fa-fw"></i>게시글관리</i>
			</a>
			<div id="demoAcc"
				class="w3-bar-block w3-hide w3-padding-large w3-medium">
				<a href="/DaengDaeng/admin/board/admin_boardlistForm.do" class="w3-bar-item w3-button">글 관리</a> 
				<a href="/DaengDaeng/admin/board/admin_reviewlistForm.do"
					class="w3-bar-item w3-button">댓글 관리</a>
			</div>
			<script>
				// Accordion 
				function myAccFunc01() {
					var x = document.getElementById("demoAcc01");
					if (x.className.indexOf("w3-show") == -1) {
						x.className += " w3-show";
					} else {
						x.className = x.className.replace(" w3-show", "");
					}
				}
			</script>
			
		</div>
	</nav>


	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"
		id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 300px; margin-top: 43px;">

		<!-- Header -->
		<header class="w3-container" style="padding-top: 22px">
			<!-- <h5><b><i class="fa fa-dashboard"></i>통계</b></h5> -->
		</header>

		<div class="w3-row-padding">
			
		
<table width="1000" border="1" align="center" >
<form action="/DaengDaeng/admin/board/admin_boardlistForm.do">	
	<tr>
	<td colspan=6>게시판유형
      <select name="board_type" ><!-- onChange="getSelectValue(this.form);" -->
      <option value="전체">전체</option>
      <option value="Q&A">QnA게시판</option>
      <option value="자유">자유게시판</option>
      </select>
      <input type="submit"  value="선택" >
	</td>
	</tr>
</form>
<tr><td>게시판번호</td><td>게시판 종류</td><td>Email</td><td>제목</td><td>등록일</td><td>삭제</td></tr>
   
	<c:forEach var="boardlist" items="${board_list}" varStatus="status"> 
	
	
	
	 <tr>
	    <td>${boardlist.board_num}</td>
	 	<td>${boardlist.board_type}</td>
	 	<td>${mem_list[status.index].mem_email}</td>
	 	<td> 
	 	<c:if test="${boardlist.board_type=='Q&A'}">
	 	<a href="/DaengDaeng/board/qna/qna_content.do?num=${boardlist.board_num}">
	 	</c:if>
	 	<c:if test="${boardlist.board_type=='자유'}">
	 	<a href="/DaengDaeng/board/free/free_content.do?num=${boardlist.board_num}">
	 	</c:if>
	 	${boardlist.board_title}</a></td>
	 	<td>${boardlist.board_date}</td>
	 	<td><button onclick="javascript:location.href='/DaengDaeng/admin/board/admin_boardlistPro.do?board_num=${boardlist.board_num}'">삭제</button>
	 	
	 </tr>
	 </c:forEach>
	  
	
	


</table>

			
				</div>
		
		
	<!-- Footer -->
	<footer class="w3-container w3-padding-16 w3-light-grey">
		<h4>FOOTER</h4>
		<p>
			Powered by <a href="https://www.w3schools.com/w3css/default.asp"
				target="_blank">w3.css</a>
		</p>
	</footer>
	</div>


	<script>
		// Get the Sidebar
		var mySidebar = document.getElementById("mySidebar");

		// Get the DIV with overlay effect
		var overlayBg = document.getElementById("myOverlay");

		// Toggle between showing and hiding the sidebar, and add overlay effect
		function w3_open() {
			if (mySidebar.style.display === 'block') {
				mySidebar.style.display = 'none';
				overlayBg.style.display = "none";
			} else {
				mySidebar.style.display = 'block';
				overlayBg.style.display = "block";
			}
		}

		// Close the sidebar with the close button
		function w3_close() {
			mySidebar.style.display = "none";
			overlayBg.style.display = "none";
		}
	</script>

</body>
</html>

