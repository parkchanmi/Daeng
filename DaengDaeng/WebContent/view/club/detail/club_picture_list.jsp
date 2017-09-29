<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>반려견 커뮤니티</title>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
<link href="/DaengDaeng/css/club_picture_list.css" rel="stylesheet" type="text/css">

<script src="https://code.jquery.com/jquery-1.9.0.min.js"
	integrity="sha256-f6DVw/U4x2+HjgEqw5BZf67Kq/5vudRZuRkljnbF344="
	crossorigin="anonymous"></script>

</head>
<body>
	<div id="wrapper">
		<jsp:include page="/menu.jsp"></jsp:include>
		<!--로그인 main -->
		<div id="contents_clubboard">
			<div class="clubboard_form">
				<div class="clubboard_title">
					<h2>소모임 정보</h2>
				</div>
				<div class="clubboard">
					<div class="clubboard_left">
						<ul>
							<div class="clubboard_photo"></div>
							<li class="clubboard_name"><label>소모임이름 : </label>
								<h3>${clubinfo.ci_name}</h3></li>
							<li class="clubboard_leader"><label>개설자 : </label>
								<h3>${clubinfo.ci_leader}</h3></li>
							<div class="btn_club_board">
								<c:if test="${joinData==null}">
									<button onclick="join_confirm();">가입하기</button>
								</c:if>
								<c:if test="${leader==true}">
									<button onclick="javascript:location.href='#'">관리하기</button>
								</c:if>
								<c:if test="${leader==false}">
									<button onclick="out_confirm();">탈퇴하기</button>
								</c:if>
							</div>
						</ul>
					</div>
					<div class="clubboard_right">
						<div class="btn_club_board_menu">
							<button
								onclick="javascript:location.href='/DaengDaeng/club/detail/club_board.do'"
								id="totallist">전체글</button>
							<button
								onclick="javascript:location.href='/DaengDaeng/club/detail/club_picture.do'"
								id="photo">사진</button>
							<button
								onclick="javascript:location.href='/DaengDaeng/club/detail/club_schedule.do'"
								id="schedule">일정</button>
							<button
								onclick="javascript:location.href='/DaengDaeng/club/detail/club_member.do'"
								id="schedule">멤버</button>
						</div>

						<table width="800" cellpadding="0" cellspacing="0" align="center">
							<thead style="background-color: #EE8300; color: #fff;">
								<tr height="35">
									<td>사진</td>
									<td>사진</td>
								</tr>
							</thead>
							<tr>
								<td>사진</td>
								<td>사진</td>
							</tr>
							<tr>
								<td>사진</td>
								<td>사진</td>
							</tr>

						</table>
					</div>
				</div>
			</div>
		</div>

	
		<jsp:include page="/footer.jsp"></jsp:include>
	</div>

</body>
</html>



