<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>반려견 커뮤니티</title>
<link href="../../css/main.css" rel="stylesheet" type="text/css">
<link href="../../css/myPageForm.css" rel="stylesheet" type="text/css">
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

	$(document).ready(function() {
		$(".btn_area02").click(function(event) {
			//	비밀번호 유효성 체크
			var pwd = $("#mem_pw").val();
			var pwd1 = $("#mem_pw_confirm").val();

			if (pwd != pwd1) { //	비밀번호 와 비밀번호 확인이 다르다면 ... 
				alert("비밀번호 잘못 입력");
				return false;
			} else {
			}
		});
	});
</script>

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

		<!--비밀번호 확인 -->
		<div id="contents_mypage">
			<div class="mypageform">
				<div class="mypage_title">
					<h2>My Page</h2>
				</div>
				<div class="owner_form">
					<form id="mp_modify_pwForm"
						action="/DaengDaeng/member/mypage/mp_modify_pwpro.do">
						<ul class="mp_modify_pwForm">
							<li><label for="mem_pw">기존 비밀번호 </label> <input
								type="password" id="mem_pw" size="15" maxlength="15" /></li>
							<li><label for="mem_pw_confirm">기존 비밀번호 확인 </label> <input
								type="password" id="mem_pw_confirm" size="15" maxlength="15" />
							</li>
						</ul>
						<input type="submit" value="확인" class="btn_area02" onSubmit="return checkIt()" style="margin-bottom:10px;">
						<input type=button value="되돌아가기" class="btn_area02" onClick="history.back();">
				</div>
			</div>
		</div>


		<div id="footer">
			<p class="footer_left">Daeng Daeng | 사업자등록번호 : 851-87-00622 | 서울
				강남 제2014-01호</p>
			<p class="footer_right">Copyright &copy; 1998-2017 DangDang
				Information Educational Institute All Right Reserved</p>
		</div>
	</div>
	</div>
</body>
</html>