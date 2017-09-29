<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>반려견 커뮤니티</title>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
<link href="/DaengDaeng/css/club_member.css" rel="stylesheet" type="text/css">

<script
  src="https://code.jquery.com/jquery-1.9.0.min.js"
  integrity="sha256-f6DVw/U4x2+HjgEqw5BZf67Kq/5vudRZuRkljnbF344="
  crossorigin="anonymous"></script>

<script type="text/javascript">

function join_confirm(){
if (confirm("소모임에 가입하시겠습니까?") == true){    //확인
	location.href="/DaengDaeng/club/detail/club_join.do";
}else{   
    return;
}
}
function out_confirm(){
	if (confirm("탈퇴하시겠습니까?") == true){    //확인
		location.href="/DaengDaeng/club/detail/member_out.do";
	}else{   
	    return;
	}
	}
function admin_Out(memcode){
	if (confirm("해당멤버를 내보내시겠습니까?") == true){    //확인
		location.href="/DaengDaeng/club/detail/admin_out.do?memcode="+memcode;
	}else{   
	    return;
	}
	}
</script>
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
							<div class="clubboard_photo">
								<img src="${clubinfo.ci_filepath}" style="width:150px;height:160px;">							
							</div>
							<li class="clubboard_name"><label>소모임이름 : </label>
								<h3>${clubinfo.ci_name}</h3></li>
							<li class="clubboard_leader"><label>개설자 : </label>
								<h3>${clubinfo.leader_name}</h3></li>
							<div class="btn_club_board">
								<c:if test="${joinData==null}">
									<button onclick="join_confirm();">가입하기</button>
								</c:if>
								<c:if test="${leader==true}">
									<button onclick="javascript:location.href='/DaengDaeng/club/detail/club_member.do'">관리하기</button>
								</c:if>
								<c:if test="${leader==false}">
									<button onclick="out_confirm();">탈퇴하기</button>
								</c:if>
							</div>
						</ul>
					</div>
					<div class="clubboard_right">
						<div class="btn_club_board_menu">
							<button onclick="javascript:location.href='/DaengDaeng/club/detail/club_board.do'" id="totallist">전체글</button><!-- 
							<button onclick="javascript:location.href='/DaengDaeng/club/detail/club_picture.do'" id="photo">사진</button> -->
							<button onclick="javascript:location.href='/DaengDaeng/club/detail/club_schedule.do'" id="schedule">일정</button>
							<button onclick="javascript:location.href='/DaengDaeng/club/detail/club_member.do'" id="schedule">멤버</button>
						</div>
						<div class="clubboard_email">
							<table width="700" cellpadding="0" cellspacing="0" align="center">
								<c:if test="${joinData!=null}">
									<thead style="background-color: #EE8300; color: #fff;">
											<tr height="35">
												<th align="center" width="40">멤버코드</th>
												<th align="center" width="30">멤버이메일</th>
												<th align="center" width="150">멤버닉네임</th>
												<c:if test="${leader==true}"><th>내보내기</th></c:if>
											</tr>
										</thead>
									<c:forEach var="member" items="${memList}">
										<tr height="35">
												<th align="center" width="30">${member.mem_code}</th>
												<th align="center" width="30">${member.mem_email}</th>
												<th align="center" width="150">${member.mem_name}</th>
												<c:if test="${leader==true}">
													<th><button onclick="admin_Out(${member.mem_code});">내보내기</button></th>
												</c:if>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${joinData==null}">
									<tr><td>권한이 없습니다.</td></tr>
								</c:if>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>

	
		<jsp:include page="/footer.jsp"></jsp:include>

	</div>

</body>
</html>



