<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>강아지 찾기</title>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
<link href="/DaengDaeng/css/myPageForm.css" rel="stylesheet"
	type="text/css">
<link href="/DaengDaeng/css/board_owner_write.css" rel="stylesheet"
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

	function setCategory2() {
		form = document.search;
		if (document.search.lost_loc1.value == "서울특별시") {
			form.lost_loc2.length = 1;
			form.lost_loc2.options[1] = new Option("중구");
			form.lost_loc2.options[1].value = "중구";
			form.lost_loc2.options[2] = new Option("용산구");
			form.lost_loc2.options[2].value = "용산구";
			form.lost_loc2.options[3] = new Option("중구");
			form.lost_loc2.options[3].value = "중구";
			form.lost_loc2.options[4] = new Option("성동구");
			form.lost_loc2.options[4].value = "성동구";
			form.lost_loc2.options[5] = new Option("광진구");
			form.lost_loc2.options[5].value = "광진구";
			form.lost_loc2.options[6] = new Option("성북구");
			form.lost_loc2.options[6].value = "성북구";
			form.lost_loc2.options[7] = new Option("강북구");
			form.lost_loc2.options[7].value = "강북구";
			form.lost_loc2.options[8] = new Option("도봉구");
			form.lost_loc2.options[8].value = "도봉구";
		}
		if (document.search.lost_loc2.value == "인천광역시") {
			form.lost_loc2.length = 1;
			form.lost_loc2.options[1] = new Option("중구");
			form.lost_loc2.options[1].value = "중구";
			form.lost_loc2.options[2] = new Option("동구");
			form.lost_loc2.options[2].value = "동구";
			form.lost_loc2.options[3] = new Option("남구");
			form.lost_loc2.options[3].value = "남구";
			form.lost_loc2.options[4] = new Option("연수구");
			form.lost_loc2.options[4].value = "연수구";
			form.lost_loc2.options[5] = new Option("남동구");
			form.lost_loc2.options[5].value = "남동구";
			form.lost_loc2.options[6] = new Option("부평구");
			form.lost_loc2.options[6].value = "부평구";
			form.lost_loc2.options[7] = new Option("계양구");
			form.lost_loc2.options[7].value = "계양구";
			form.lost_loc2.options[8] = new Option("서구");
			form.lost_loc2.options[8].value = "서구";
			form.lost_loc2.options[9] = new Option("강화군");
			form.lost_loc2.options[9].value = "강화군";
		}
		if (document.search.lost_loc2.value == "광주광역시") {
			form.lost_loc2.length = 1;
			form.lost_loc2.options[1] = new Option("동구");
			form.lost_loc2.options[1].value = "동구";
			form.lost_loc2.options[2] = new Option("남구");
			form.lost_loc2.options[2].value = "남구";
			form.lost_loc2.options[3] = new Option("서구");
			form.lost_loc2.options[3].value = "서구";
			form.lost_loc2.options[4] = new Option("북구");
			form.lost_loc2.options[4].value = "북구";
			form.lost_loc2.options[5] = new Option("광산구");
			form.lost_loc2.options[5].value = "광산구";
		}
		if (document.search.lost_loc2.value == "대전광역시") {
			form.lost_loc2.length = 1;
			form.lost_loc2.options[1] = new Option("동구");
			form.lost_loc2.options[1].value = "동구";
			form.lost_loc2.options[2] = new Option("중구");
			form.lost_loc2.options[2].value = "중구";
			form.lost_loc2.options[3] = new Option("서구");
			form.lost_loc2.options[3].value = "서구";
			form.lost_loc2.options[4] = new Option("유성구");
			form.lost_loc2.options[4].value = "유성구";
			form.lost_loc2.options[5] = new Option("대덕구");
			form.lost_loc2.options[5].value = "대덕구";
		}
		if (document.search.lost_loc2.value == "울산광역시") {
			form.lost_loc2.length = 1;
			form.lost_loc2.options[1] = new Option("동구");
			form.lost_loc2.options[1].value = "동구";
			form.lost_loc2.options[2] = new Option("중구");
			form.lost_loc2.options[2].value = "중구";
			form.lost_loc2.options[3] = new Option("남구");
			form.lost_loc2.options[3].value = "남구";
			form.lost_loc2.options[4] = new Option("북구");
			form.lost_loc2.options[4].value = "북구";
			form.lost_loc2.options[5] = new Option("울주군");
			form.lost_loc2.options[5].value = "울주군";
		}
		if (document.search.lost_loc2.value == "경기도") {
			form.lost_loc2.length = 1;
			form.lost_loc2.options[1] = new Option("수원시");
			form.lost_loc2.options[1].value = "수원시";
			form.lost_loc2.options[2] = new Option("장안구");
			form.lost_loc2.options[2].value = "장안구";
			form.lost_loc2.options[3] = new Option("권선구");
			form.lost_loc2.options[3].value = "권선구";
			form.lost_loc2.options[4] = new Option("팔달구");
			form.lost_loc2.options[4].value = "팔달구";
			form.lost_loc2.options[5] = new Option("영통구");
			form.lost_loc2.options[5].value = "영통구";
			form.lost_loc2.options[6] = new Option("성남시");
			form.lost_loc2.options[6].value = "성남시";
			form.lost_loc2.options[7] = new Option("수정구");
			form.lost_loc2.options[7].value = "수정구";
			form.lost_loc2.options[8] = new Option("중원구");
			form.lost_loc2.options[8].value = "중원구";
			form.lost_loc2.options[9] = new Option("분당구");
			form.lost_loc2.options[9].value = "분당구";
		}
		if (document.search.lost_loc2.value == "강원도") {
			form.lost_loc2.length = 1;
			form.lost_loc2.options[1] = new Option("춘천시");
			form.lost_loc2.options[1].value = "춘천시";
			form.lost_loc2.options[2] = new Option("원주시");
			form.lost_loc2.options[2].value = "원주시";
			form.lost_loc2.options[3] = new Option("강릉시");
			form.lost_loc2.options[3].value = "강릉시";
			form.lost_loc2.options[4] = new Option("동해시");
			form.lost_loc2.options[4].value = "동해시";
			form.lost_loc2.options[5] = new Option("태백시");
			form.lost_loc2.options[5].value = "태백시";
		}

		if (document.search.lost_loc2.value == "충청북도") {
			form.lost_loc2.length = 1;
			form.lost_loc2.options[1] = new Option("청주시");
			form.lost_loc2.options[1].value = "청주시";
			form.lost_loc2.options[2] = new Option("충주시");
			form.lost_loc2.options[2].value = "충주시";
			form.lost_loc2.options[3] = new Option("제천시");
			form.lost_loc2.options[3].value = "제천시";
			form.lost_loc2.options[4] = new Option("보은군");
			form.lost_loc2.options[4].value = "보은군";
			form.lost_loc2.options[5] = new Option("괴산군");
			form.lost_loc2.options[5].value = "괴산군";
		}

		if (document.search.lost_loc2.value == "충청남도") {
			form.lost_loc2.length = 1;
			form.lost_loc2.options[1] = new Option("당진시");
			form.lost_loc2.options[1].value = "당진시";
			form.lost_loc2.options[2] = new Option("천안시");
			form.lost_loc2.options[2].value = "천안시";
			form.lost_loc2.options[3] = new Option("동남구");
			form.lost_loc2.options[3].value = "동남구";
			form.lost_loc2.options[4] = new Option("서북구");
			form.lost_loc2.options[4].value = "서북구";
			form.lost_loc2.options[5] = new Option("공주시");
			form.lost_loc2.options[5].value = "공주시";
			form.lost_loc2.options[4] = new Option("보령시");
			form.lost_loc2.options[4].value = "보령시";
			form.lost_loc2.options[5] = new Option("아산시");
			form.lost_loc2.options[5].value = "아산시";
		}
		if (document.search.lost_loc2.value == "전라북도") {
			form.lost_loc2.length = 1;
			form.lost_loc2.options[1] = new Option("전주시");
			form.lost_loc2.options[1].value = "전주시";
			form.lost_loc2.options[2] = new Option("군산시");
			form.lost_loc2.options[2].value = "군산시";
			form.lost_loc2.options[3] = new Option("익산시");
			form.lost_loc2.options[3].value = "익산시";
			form.lost_loc2.options[4] = new Option("정읍시");
			form.lost_loc2.options[4].value = "정읍시";
			form.lost_loc2.options[5] = new Option("남원시");
			form.lost_loc2.options[5].value = "남원시";
		}
		if (document.search.lost_loc2.value == "전라남도") {
			form.lost_loc2.length = 1;
			form.lost_loc2.options[1] = new Option("여수시");
			form.lost_loc2.options[1].value = "여수시";
			form.lost_loc2.options[2] = new Option("순천시");
			form.lost_loc2.options[2].value = "순천시";
			form.lost_loc2.options[3] = new Option("나주시");
			form.lost_loc2.options[3].value = "나주시";
			form.lost_loc2.options[4] = new Option("광양시");
			form.lost_loc2.options[4].value = "광양시";
		}

		if (document.search.lost_loc2.value == "경상북도") {
			form.lost_loc2.length = 1;
			form.lost_loc2.options[1] = new Option("포항시");
			form.lost_loc2.options[1].value = "포항시";
			form.lost_loc2.options[2] = new Option("경주시");
			form.lost_loc2.options[2].value = "경주시";
			form.lost_loc2.options[3] = new Option("김천시");
			form.lost_loc2.options[3].value = "김천시";
			form.lost_loc2.options[4] = new Option("안동시");
			form.lost_loc2.options[4].value = "안동시";
		}

		if (document.search.lost_loc2.value == "경상남도") {
			form.lost_loc2.length = 1;
			form.lost_loc2.options[1] = new Option("창원시");
			form.lost_loc2.options[1].value = "창원시";
			form.lost_loc2.options[2] = new Option("통영시");
			form.lost_loc2.options[2].value = "통영시";
			form.lost_loc2.options[3] = new Option("사천시");
			form.lost_loc2.options[3].value = "사천시";
			form.lost_loc2.options[4] = new Option("김해시");
			form.lost_loc2.options[4].value = "김해시";
		}

		if (document.search.lost_loc2.value == "부산광역시") {
			form.lost_loc2.length = 1;
			form.lost_loc2.options[1] = new Option("동래구");
			form.lost_loc2.options[1].value = "동래구";
			form.lost_loc2.options[2] = new Option("남구");
			form.lost_loc2.options[2].value = "남구";
			form.lost_loc2.options[3] = new Option("북구");
			form.lost_loc2.options[3].value = "북구";
			form.lost_loc2.options[4] = new Option("기장군");
			form.lost_loc2.options[4].value = "기장군";
		}

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
		<div id="contents_write">
			<div class="board_write">
				<div class="write_title">
					<h2>글쓰기</h2>
				</div>
				<div class="write_area">
					<form method="post" name="writeform" class="writeform_board"
						action="/DaengDaeng/board/lost/owner/owner_writePro.do"
						onsubmit="summer();">
						<input type="hidden" name="lost_type" value="강아지 찾기">
						<ul>
							<li class="write_info"><label>지 역</label>
									<select name="lost_loc1">
										<option value="">지역선택</option>
										<option value="서울특별시">서울특별시</option>
										<option value="인천광역시">인천광역시</option>
										<option value="경기도">경기도</option>
										<option value="충청북도">충청북도</option>
										<option value="충청남도">충청남도</option>
										<option value="전라북도">전라북도</option>
										<option value="전라남도">전라남도</option>
										<option value="경상북도">경상북도</option>
										<option value="경상남도">경상남도</option>
									</select>
							</li>
							<li class="write_writer"><label>작성자</label>
								<h3>${memberDTO.mem_name}</h3></li>
							
						</ul>

						<div class="write_box">
							<div class="write_box">
								<div id="summernote">
								*강아지 정보 이름 :<br>
								견종 : <br>
								색상 : <br>
								나이/체중 : &nbsp;&nbsp;/<br>
								실종일시 : <br>
								실종장소 : <br>
								특징 :
								</div>
								<input name="lost_contents" id="content" type="hidden">
							</div>
							<div class="btn_write">
								<input type="submit" value="글쓰기"> <input type="reset"
									value="다시작성"> <input type="button" value="목록보기"
									OnClick="window.location='/DaengDaeng/board/lost/owner/owner_list.do'">
							</div>
						</div>


					</form>
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
</body>
</html>