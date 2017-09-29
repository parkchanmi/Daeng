<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
<link href="/DaengDaeng/css/myPageForm.css" rel="stylesheet" type="text/css">
	
<title>회원가입</title>
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
	<div id="wrapper">
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
				<ul class="login_box" style="margin-bottom:0px">
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
		<!--로그인 main -->
		<div id="contents_mypage">
			<div class="mypageform">
				<form name="choiceForm"
					action="/DaengDaeng/member/login/member_choiceForm.do"
					class="mem_choiceform">
					<dl
						onClick="javascript:location.href='/DaengDaeng/member/join/member_normal.do'">
						<dt>
							<img src="/DaengDaeng/images/join_plus.png" width="45px"
								, height="54px">
						</dt>
						<dd class="mem_choice">
							<h3>일반</h3>
							<!-- <input type="button" value="일반" onClick="javascript:location.href='/DaengDaeng/member/join/member_normal.do';"> -->
						</dd>
					</dl>
					<dl
						onClick="javascript:location.href='/DaengDaeng/member/join/member_dogowner.do'">
						<dt>
							<img src="/DaengDaeng/images/join_plus.png" width="45px"
								, height="54px">
						</dt>
						<dd class="mem_choice">
							<h3>견주</h3>
							<!-- <input type="button" value="견주" onClick="javascript:location.href='/DaengDaeng/member/join/member_dogowner.do';"> -->
						</dd>
					</dl>
					<dl
						onClick="javascript:location.href='/DaengDaeng/member/join/member_expert.do'">
						<dt>
							<img src="/DaengDaeng/images/join_plus.png" width="45px"
								, height="54px">
						</dt>
						<dd class="mem_choice">
							<h3>전문가</h3>
							<!-- <input type="button" value="전문가" onClick="javascript:location.href='/DaengDaeng/member/join/member_expert.do';"> -->
						</dd>
					</dl>
				</form>
			</div>
		</div>
	
		<jsp:include page="/footer.jsp"></jsp:include>
	</div>
</body>
</html>