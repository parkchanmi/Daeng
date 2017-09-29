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

	
	function check(){

		var writeform = eval("document.writeform");
		
		if(!writeform.mem_pw.value){
			alert("비밀번호를  입력하세요");
			return false;
		}
		if(writeform.mem_pw.value != writeform.mem_pw2.value){
			alert("비밀번호를 동일하게 입력하세요");
			return false;
		}
		if(!writeform.mem_hp.value){
			alert("핸드폰번호를 입력하세요");
			return false;
		}
		if(!writeform.mem_loc.value){
			alert("지역을 선택하세요");
			return false;
		}
		if(!writeform.mem_type.value=='선택'){
			alert("회원등급을 선택하세요");
			return false;
		}

		
		
		
	}
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
					<form method="post" name="writeform"
						action="/DaengDaeng/member/mypage/mpinfo_updatePro.do" onsubmit="return check();">
						<ul>
							<li><label>등급</label> <select name="mem_type">
									<option value="선택">등급선택</option>
									<option value="일반">일반회원</option>
									<option value="견주">견주</option>
									<option value="전문가">전문가</option>
							</select></li>
							<li><label>이메일 주소</label>
								<h3>${memberDTO.mem_email}</h3></li>
							<li><label>P/W</label> <input type="password" name="mem_pw"
								size="15" maxlength="15" id="mem_pw"/></li>
							<li><label>P/W 재확인</label> <input type="password"
								name="mem_pw02" size="15" maxlength="15" id="mem_pw2" /></li>
							<li><label>닉네임</label> <input type="name" name="mem_name"
								size="15" maxlength="15" value="${memberDTO.mem_name}" disabled/></li>
								<li><label>성별 : ${memberDTO.mem_gender} / 연령대 : ${memberDTO.mem_age}</label> 
							<li><label>H.P</label> <input type="name" name="mem_hp"
								size="15" maxlength="15" /></li>
							<li><label>주소</label> 
							<select name="mem_loc">
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
						</ul>
						<input type="submit" value="수정" class="btn_area02">
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
	</div>
</body>
</html>