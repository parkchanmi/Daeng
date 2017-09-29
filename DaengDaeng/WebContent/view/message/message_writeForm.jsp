<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>반려견 커뮤니티</title>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
<link href="/DaengDaeng/css/msg_write.css" rel="stylesheet" type="text/css">
<script
  src="https://code.jquery.com/jquery-1.9.0.min.js"
  integrity="sha256-f6DVw/U4x2+HjgEqw5BZf67Kq/5vudRZuRkljnbF344="
  crossorigin="anonymous"></script>

<script type="text/javascript">
function checkIt(){ //유효체크
	var check = eval("document.write_message"); //객체

	if(!check.m_receive.value){
		alert("받는사람을 입력하세요");
		return false;
	}
	if(!check.m_title.value){
		alert("제목을 입력하세요");
		return false;
	}
	if(!check.m_contents.value){
		alert("내용을 입력하세요");
		return false;
	}
	if(check.find_receiver.value=="n"){
		alert("받는 사람을 찾아주세요!");
		return false;
	}
	if(check.find_receiver.value=="f"){
		alert("존재하지 않는 회원입니다.");
		return false;
	}
	

	return true;
}



function change(obj) {
	document.write_message.find_receiver.value="n";
	return;
}
function popupOpen(){
	var receiver = document.write_message.m_receive.value;
	var popUrl = "/DaengDaeng/message/find_receiver.do?m_receive="+receiver;	//팝업창에 출력될 페이지 URL

	var popOption = "width=370, height=360, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)

		window.open(popUrl,"",popOption);

	}

</script>

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
						<li><a href="/DaengDaeng/board/lost/owner/owner_list.do">강아지
								찾아요</a></li>
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
	<div id="contents_msg_write">
		<div class="msg_write">
			<div class="msg_write_title">
				<h2>Write</h2>
			</div>
			
			<div class="btn_msg_write">
				<button onclick="javascript:location.href='/DaengDaeng/message/message_receive.do'" id="msg_resc">받은 쪽지</button>
				<button onclick="javascript:location.href='/DaengDaeng/message/message_send.do'" id="msg_send">보낸 쪽지</button>
				<button onclick="javascript:location.href='/DaengDaeng/message/message_writeForm.do'" id="msg_write">쪽지 쓰기</button>
			</div>
			
			<div class="msg_write_list">
				<form name="write_message" action="/DaengDaeng/message/message_writePro.do"  onSubmit="return checkIt()" >
					<input type="hidden" name="find_receiver" value="n"/>
					<ul>
							<li class="msg_write_send">
								<label>받는 사람</label>
								<input type="text" name="m_receive" size="10" maxlength="50" onChange="change(this);"/>
								<input type="button" onclick="javascript:popupOpen();" value="찾기"/>
							</li>
							<li class="msg_write_texttit">
								<label>제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목</label> 
								<input type="text" name="m_title" size="20" maxlength="50"/>
							</li>
							<li class="msg_write_text">
								<label>내&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;용</label>
								<textarea name="m_contents" rows="13" cols="20"></textarea>	
							</li>
					</ul>
					<div class="btn_msg_send">
						<input type="submit" value="전 송">
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="/footer.jsp"></jsp:include>
	</div>

</body>
</html>



