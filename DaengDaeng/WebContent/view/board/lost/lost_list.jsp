<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>실종게시판</title>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
<script
  src="https://code.jquery.com/jquery-1.9.0.min.js"
  integrity="sha256-f6DVw/U4x2+HjgEqw5BZf67Kq/5vudRZuRkljnbF344="
  crossorigin="anonymous"></script>
<script>
// html dom 이 다 로딩된 후 실행된다.
$(function(){
	$('.menu > a').click(function(){
		$(this).next().toggleClass('hide');
	});
    $('.menu_toggle').click(function(){  
    	$(this).addClass('disN');
    	$('.view_menu').removeClass('disN');
    	$('.aside_menu').removeClass('bg_none');
    });
    
    $('.close').click(function(){
    	$('.menu_toggle').removeClass('disN');
    	$('.view_menu').addClass('disN');
    	$('.aside_menu').addClass('bg_none');    	
    });
});
</script>
</head>
<body>
	<div id="wrapper">
		<input type="hidden" id="click_flag">
		<div class="aside_menu bg_none">
			<div class="menu_toggle"></div>
			<div class="view_menu disN">
				<div class="close"></div>
				<ul class="menu_area">
					<li class="menu"><a>실종신고</a>
						<ul class="hide">
							<li><a href="/DaengDaeng/board/lost/owner/owner_list.do">강아지 찾아요</a></li>
							<li><a href="#">주인 찾아요</a></li>
						</ul></li>
					<li class="menu"><a>자유게시판</a>
						<ul class="hide">
							<li><a href="/DaengDaeng/board/qna/qna_list.do">펫 Q&A</a></li>
							<li><a href="#">펫과 함께</a></li>
							<li><a href="#">게시판</a></li>
						</ul></li>
					<li class="menu"><a href="/DaengDaeng/club/club_list.do">소모임</a></li>
					<li class="menu"><a>고객센터</a>
						<ul class="hide">
							<li><a href="/DaengDaeng/fileform.do">게시판</a></li>
						</ul></li>
					<li class="menu"><a href="/DaengDaeng/main.do">메인으로</a>
					
					</li>
				</ul>
			</div>
		</div>
		<script>
			$(function() {
				$('.login_btn').click(function() {
					//$('.menu_loginarea').toggleClass('disN');
					$(this).next().toggleClass('disN');
				});

			});
		</script>
		<div class="hd_right">
			<a href="javascript:;" class="login_btn"><img
				src="/DaengDaeng/images/join_plus.png"></a>
			<div class="menu_loginarea disN">
				<ul class="login_box">
				
				<c:choose>
    				<c:when test="${memberDTO==null}">
       	 				<li><a href="/DaengDaeng/member/join/member_choiceForm.do">회원가입</a></li>
						<li><a href="/DaengDaeng/member/login/member_loginForm.do">로그인</a></li>
    				</c:when>
    				<c:when test="${memberDTO.mem_type=='관리자'}">
    					<li>${memberDTO.mem_name}</li>
       	 				<li><a href="#">관리페이지</a></li>
       	 				<li><a href="/DaengDaeng/message/message_receive.do">쪽지함</a></li>
						<li><a href="/DaengDaeng/member/login/member_logout.do">로그아웃</a></li>
    				</c:when>
    				<c:otherwise>
    					<li>${memberDTO.mem_name}</li>
       	 				<li><a href="/DaengDaeng/member/mypage/myPageForm.do">마이페이지</a></li>
       	 				<li><a href="/DaengDaeng/club/myclub_list.do">내 소모임</a></li>
       	 				<li><a href="/DaengDaeng/message/message_receive.do">쪽지함</a></li>
						<li><a href="/DaengDaeng/member/login/member_logout.do">로그아웃</a></li>
    				</c:otherwise>
				</c:choose>
				</ul>
			</div>
		</div>
		<div id="contents">
	<center>
		<table width="1000">
			<tr>
				<select name="area1">
					<option value="서울특별시" selected>서울특별시</option>
					<option value="인천광역시">인천광역시</option>
					<option value="경기도">경기도</option>
					<option value="강원도">강원도</option>
					<option value="경상북도">경상북도</option>
					<option value="경상남도">경상남도</option>
					<option value="광주광역시">광주광역시</option>
					<option value="대구광역시">대구광역시</option>
					<option value="대전광역시">대전광역시</option>
					<option value="부산광역시">부산광역시</option>
					<option value="울산광역시">울산광역시</option>
					<option value="전라북도">전라북도</option>
					<option value="전라남도">전라남도</option>
					<option value="제주특별시자치도">제주특별시자치도</option>
					<option value="충청북도">충청북도</option>
					<option value="충청남도">충청남도</option>
				</select>
				<select name="area2">
				<option value="">시/군/구</option>
				</select>
				<td align="right"><a href="">글쓰기</a></td>
			</tr>
		</table>
		</center>
</body>
</html>