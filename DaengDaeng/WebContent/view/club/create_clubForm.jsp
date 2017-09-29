<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>반려견 커뮤니티</title>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
<link href="/DaengDaeng/css/club_list_make.css" rel="stylesheet"
	type="text/css">
<script src="https://code.jquery.com/jquery-1.9.0.min.js"
	integrity="sha256-f6DVw/U4x2+HjgEqw5BZf67Kq/5vudRZuRkljnbF344="
	crossorigin="anonymous"></script>
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.7/summernote.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.7/summernote.js"></script>
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
<script>
	$(document).ready(function() {
		$('#summernote').summernote({ // summernote를 사용하기 위한 선언
			width : 800,
			height : 400,
			callbacks : {
				onImageUpload : function(files, editor, welEditable) {
					console.log(files);
					console.log(files[0]);
					var opt = {};
					//for (var i = files.length - 1; i >= 0; i--) {
					//   files[i]; //해당파일들을 ajax로 한번씩 FormData로담아서 보내거나 다양하게 처리하시믄됩니다.   
					sendFile(files[0], editor, welEditable);

					//var url = "http://localhost:8081/test/upload/"
					//$('#summernote').summernote('insertImage',url+ files[i].name,files[i].name);

					//}

				}
			}
		});

	});
	function summer() {
		var markStr = $('#summernote').summernote('code');
		$("#content").val(markStr);
	}

	function sendFile(file, editor, welEditable) {
		data = new FormData();
		data.append("uploadFile", file);
		//var loading = $('<img alt="loading" src="/DaengDaeng/images/ajax-loader.gif" />').appendTo(document.body).hide();

		$.ajax({
			data : data,
			type : "POST",
			url : "/DaengDaeng/fileupload.jsp",
			cache : false,
			contentType : false,
			processData : false,

			success : function(data) {
				setTimeout(function() {
					alert(data.name);
					var url = "http://localhost:8081/DaengDaeng/upload/";
					$('#summernote').summernote(
							'insertImage',
							'http://localhost:8081/DaengDaeng/upload/'
									+ data.name, data.name);
				}, 3000);
				//   alert(data.name);
				//   var url = "http://localhost:8081/DaengDaeng/upload/";
				//   $('#summernote').summernote('insertImage','http://localhost:8081/DaengDaeng/upload/'+data.name,data.name);

			}
		});
	}
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
							<li><a href="/DaengDaeng/board/lost/owner/owner_list.do">강아지
									찾아요</a></li>
							<li><a href="#">주인 찾아요</a></li>
						</ul></li>
					<li class="menu"><a>자유게시판</a>
						<ul class="hide">
							<li><a href="/DaengDaeng/board/qna/qna_list.do">펫 Q&A</a></li>
							<li><a href="/DaengDaeng/board/map/map_list.do">펫과 함께</a></li>
							<li><a href="#">게시판</a></li>
						</ul></li>
					<li class="menu"><a href="/DaengDaeng/club/club_list.do">소모임</a></li>
					<li class="menu"><a>마이페이지</a>
						<ul class="hide">
							<li><a href="/DaengDaeng/member/mypage/myPageForm.do">내
									정보</a></li>
							<li><a href="/DaengDaeng/member/mypage/myschedule.do">Calendar</a></li>
						</ul></li>
					<li class="menu"><a href="/DaengDaeng/admin_main.do">관리자
							페이지</a></li>
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
			<a href="javascript:;" class="login_btn"> <c:choose>
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
							<li>${memberDTO.mem_name}</li>
							<!-- 
						<li><a href="/DaengDaeng/member/mypage/myPageForm.do">마이페이지</a></li>
						<li><a href="/DaengDaeng/club/myclub_list.do">내 소모임</a></li> -->
							<li><a href="/DaengDaeng/message/message_receive.do">쪽지함</a></li>
							<li><a href="/DaengDaeng/member/login/member_logout.do">로그아웃</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
		<div id="contents_clublist_make">
			<div class="clublist_form">
				<div class="clublist_title">
					<h2>소모임 만들기</h2>
				</div>
				<div class="make_club">
					<form action="/DaengDaeng/club/create_clubPro.do"
						name="make_club_form" method="post" enctype="multipart/form-data"
						onsubmit="summer();">
						<ul>
							<li class="make_club_file"><label>파일첨부</label> <input
								type="file" name="ci_filecode"></li>
							<li class="make_club_select"><label>분류</label> <select
								name="ci_type">
									<option value="문화">문화</option>
									<option value="영화">영화</option>
									<option value="음식">음식</option>
									<option value="여행">여행</option>
									<option value="독서">독서</option>
							</select></li>
							<li class="make_club_name"><label>소모임이름</label>
								<h3>
									<input type="text" name="ci_name">
								</h3></li>
						</ul>
						<div class="club_write_box">
							<div class="club_write_contents">
								<div id="summernote"></div>
								<input name="ci_intro" id="content" type="hidden">
							</div>
							<div class="btn_write_club">
								<input type="submit" value="만들기"> <input type="button"
									value="취소"
									onclick="javascript:location.href='/DaengDaeng/club/club_list.do'">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

		<div class="lost_dogs_slide">
			<div class="lost_dogs_title">
				<h2>실종된 댕댕이들</h2>
				<p class="arrow">></p>
			</div>
			<ul>
				<li class="lost_dogs_img"><a href="#"><img
						src="/DaengDaeng/images/dog_01.png"></a><span class="bar">|</span></li>
				<li class="lost_dogs_img"><a href="#"><img
						src="/DaengDaeng/images/dog_03.png"></a><span class="bar">|</span></li>
				<li class="lost_dogs_img"><a href="#"><img
						src="/DaengDaeng/images/dog_04.png"></a><span class="bar">|</span></li>
				<li class="lost_dogs_img"><a href="#"><img
						src="/DaengDaeng/images/dog_02.png"></a><span class="bar">|</span></li>
			</ul>
		</div>
		<div id="footer">
			<p class="footer_left">Daeng Daeng | 사업자등록번호 : 851-87-00622 | 서울
				강남 제2014-01호</p>
			<p class="footer_right">Copyright &copy; 1998-2017 DangDang
				Information Educational Institute All Right Reserved</p>
		</div>


	</div>

</body>
</html>



