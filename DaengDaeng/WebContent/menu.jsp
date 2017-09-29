<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>menu</title>
<script src="https://code.jquery.com/jquery-1.9.0.min.js"
	integrity="sha256-f6DVw/U4x2+HjgEqw5BZf67Kq/5vudRZuRkljnbF344="
	crossorigin="anonymous"></script>
<script>
	// html dom 이 다 로딩된 후 실행된다.
	$(function() {
		$('.menu > a').click(function() {
			$(this).next().toggleClass('hide');
		});
		$('.menu_toggle').click(function() {
			$(this).addClass('disN');
			$('.view_menu').removeClass('disN');
			$('.aside_menu').removeClass('bg_none');
		});

		$('.close').click(function() {
			$('.menu_toggle').removeClass('disN');
			$('.view_menu').addClass('disN');
			$('.aside_menu').addClass('bg_none');
		});
	});
</script>
</head>
<body>
	<div class="aside_menu bg_none">
		<div class="menu_toggle"></div>
		<div class="view_menu disN">
			<div class="close"></div>
			<ul class="menu_area">
				<li class="menu"><a>실종신고</a>
					<ul class="hide">
						<li><a href="/DaengDaeng/board/lost/owner/owner_list.do">강아지
								찾아요</a></li>
						<li><a href="/DaengDaeng/board/lost/finder/finder_list.do">주인 찾아요</a></li>
					</ul></li>
				<li class="menu"><a>자유게시판</a>
					<ul class="hide">
						<li><a href="/DaengDaeng/board/qna/qna_list.do">펫 Q&A</a></li>
						<li><a href="/DaengDaeng/board/map/map_list.do">펫과 함께</a></li>
						<li><a href="/DaengDaeng/board/free/free_list.do">게시판</a></li>
					</ul></li>
				<li class="menu"><a href="/DaengDaeng/club/club_list.do">소모임</a></li>
				<li class="menu"><a>마이페이지</a>
					<ul class="hide">
						<li><a href="/DaengDaeng/member/mypage/myPageForm.do">내 정보</a></li>
						<li><a href="/DaengDaeng/member/mypage/myschedule.do">Calendar</a></li>
					</ul>
				</li>
				<li class="menu"><a href="/DaengDaeng/admin_main.do">관리자 페이지</a></li>
				<li class="menu"><a href="/DaengDaeng/main.do">메인으로</a></li>
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
		<a href="javascript:;" class="login_btn"> 
		<c:choose>
				<c:when test="${memberDTO==null}">
					<img src="/DaengDaeng/images/join_plus.png">
				</c:when>
				<c:when test="${memberDTO.mem_type=='관리자'}">
					<img src="/DaengDaeng/images/mng_set.png">
				</c:when>
				<c:otherwise>
					<img src="/DaengDaeng/images/join_cpl.png">
				</c:otherwise>
			</c:choose>
		</a>
		<div class="menu_loginarea disN">
			<ul class="login_box">
				<c:choose>
					<c:when test="${memberDTO==null}">
						<li><a href="/DaengDaeng/member/join/member_choiceForm.do">회원가입</a></li>
						<li><a href="/DaengDaeng/member/login/member_loginForm.do">로그인</a></li>
					</c:when>
					<c:when test="${memberDTO.mem_type=='관리자'}">
						<li>${memberDTO.mem_name}</li>
						<li><a href="/DaengDaeng/admin_main.do">관리페이지</a></li>
						<li><a href="/DaengDaeng/message/message_receive.do">쪽지함</a></li>
						<li><a href="/DaengDaeng/member/login/member_logout.do">로그아웃</a></li>
					</c:when>
					<c:otherwise>
						<li>${memberDTO.mem_name}</li><!-- 
						<li><a href="/DaengDaeng/member/mypage/myPageForm.do">마이페이지</a></li>
						<li><a href="/DaengDaeng/club/myclub_list.do">내 소모임</a></li> -->
						<li><a href="/DaengDaeng/message/message_receive.do">쪽지함</a></li>
						<li><a href="/DaengDaeng/member/login/member_logout.do">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</body>
</html>