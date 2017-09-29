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
<script>
	function selectChange(e){
		//alert(e.value);
		//var e2 = e.mem_type.value;
		if(e.value=="전문가"){
			var con = document.getElementById("mem_job");
		    con.style.display = 'block';
		    document.getElementById("mem_job2").style.display = 'none';
		}else{
			var con = document.getElementById("mem_job");
		    con.style.display = 'none';
		    document.getElementById("mem_job2").style.display = 'block';
		}
		
	}
		
		
	

</script>

<form method="post" action="/DaengDaeng/admin/member/admin_memberupdatePro.do?mem_code=${member.mem_code}" name="userinput">
<table border=1>
	<tr>
	  <td colspan="3">관리자 권한 부여</td>
	</tr>
	<tr>  
	  <td>권한</td>
	  <td colspan="2">
	  <c:if test="${ad_grant.ad_mem_ok==1}">
	  <input type="checkbox" name="ad_mem_ok" value="1" ondblclick="this.checked=false" selected>회원관리
	  </c:if>
	    <c:if test="${ad_grant.ad_mem_ok==0||ad_grant==null}">
   		 <input type="checkbox" name="ad_mem_ok" value="0" ondblclick="this.checked=false">회원관리
	  </c:if> 
	 
	  <c:if test="${ad_grant.ad_board_ok==1}">
	  <input type="checkbox" name="ad_mem_ok" value="1" ondblclick="this.checked=false" selected>게시글관리
	  </c:if>
	    <c:if test="${ad_grant.ad_board_ok==0||ad_grant==null}">
   		 <input type="checkbox" name="ad_mem_ok" value="0" ondblclick="this.checked=false">게시글관리
	  </c:if> 
	  
	  <c:if test="${ad_grant.ad_lost_ok==1}">
	  <input type="checkbox" name="ad_mem_ok" value="1" ondblclick="this.checked=false" selected>실종관리
	  </c:if>
	    <c:if test="${ad_grant.ad_lost_ok==0||ad_grant==null}">
   		 <input type="checkbox" name="ad_mem_ok" value="0" ondblclick="this.checked=false">실종관리
	  </c:if> 
	  
	  <c:if test="${ad_grant.ad_club_ok==1}">
	  <input type="checkbox" name="ad_mem_ok" value="1" ondblclick="this.checked=false" selected>소모임관리
	  </c:if>
	    <c:if test="${ad_grant.ad_club_ok==0||ad_grant==null}">
   		 <input type="checkbox" name="ad_mem_ok" value="0" ondblclick="this.checked=false">소모임관리
	  </c:if> 
	  </td>
	</tr>
	
	<tr>
	  <td colspan="3">기본정보</td>
	</tr>
	
	<tr>
	  <td>등록상태</td>
	  <td colspan="2">
	  <c:if test="${member.mem_black=='정상'}">
	  	<input type="radio" name="mem_black" value="정상" checked>정상
	  	<input type="radio" name="mem_black" value="블랙">블랙
	  </c:if>
   	   <c:if test="${member.mem_black=='블랙'}">
	  	<input type="radio" name="mem_black" value="정상">정상
	  	<input type="radio" name="mem_black" value="블랙" checked>블랙
	  </c:if>
	  </td>
	</tr>
	
	<tr> 
	  <td>회원유형</td>
	  <td>
	    <select name="mem_type" id="mem_type" onChange="selectChange(this)">
	     <option value="선택">선택</option>
     	 <option value="일반">일반회원</option>
     	 <option value="견주">견주</option>
     	 <option value="전문가" >전문가</option>
	    </select>
	  </td>
	 
	  <td id="mem_job" style="display:none;">전문분야
	    <select name="mem_job">
     	 <option  value="동물병원의" >동물병원의</option>
     	 <option  value="훈련사" >훈련사</option>
     	 <option  value="미용사" >미용사</option>
     	 <option  value="기타" >기타</option>
	    </select>
	  </td>
	  <td id="mem_job2">
	  &nbsp;
	  </td>

	</tr>
	
	<tr>
	  <td>닉네임</td><td colspan="2">${member.mem_name}</td>
    </tr>
	
	<tr>
	  <td>E-mail</td><td colspan="2">${member.mem_email}</td>
	</tr>
	
	<tr>
	  <td>성별 / 연령대</td><td colspan="2">${member.mem_gender} / ${member.mem_age}</td>
	</tr>
	
	<tr>
	  <td>지역</td><td colspan="2">${member.mem_loc}</td>
	</tr>
	
	<tr>
	  <td>유입경로</td><td colspan="2">${member.mem_visit}</td>
	</tr>
	
	
	<tr>
      <td colspan="3">
          <input type="submit" value="수  정" >
          <input type="reset" value="다시입력">	
      </td>
    </tr> 
	
	
	
</table>
</form>

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